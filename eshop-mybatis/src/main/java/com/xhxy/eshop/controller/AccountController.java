package com.xhxy.eshop.controller;

import com.xhxy.eshop.entity.Address;
import com.xhxy.eshop.entity.Order;
import com.xhxy.eshop.entity.User;
import com.xhxy.eshop.service.AddressService;
import com.xhxy.eshop.service.OrderService;
import com.xhxy.eshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/account")
@MultipartConfig(location = "D:\\", fileSizeThreshold = 1024)
public class AccountController {
    @Resource
    private UserService userService;
    @Resource
    private OrderService orderService;
    @Resource
    private AddressService addressService;

    // 账号中心的“主页面”
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Integer id = (Integer) session.getAttribute("userId");// 已登录用户的id
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "account-dashboard";
    }

    // ------ 订单 -> 列表 -------
    public String orderlist(HttpServletRequest request, HttpServletResponse response) {
        Integer id = (Integer) request.getSession().getAttribute("id");// 已登录用户的id

        List<Order> orders = orderService.getByUserId(id);
        request.setAttribute("orders", orders);

        User user = userService.findById(id);
        request.setAttribute("user", user);

        return "account-order-list.jsp";
    }

    // ------ 订单 -> 查看单个 -------
    public String orderView(HttpServletRequest request, HttpServletResponse response) {
        Integer orderId = Integer.parseInt(request.getParameter("id")); //订单Id

        Order order = orderService.findById(orderId);
        request.setAttribute("order", order);

        return "account-order-view.jsp";
    }

    // ------ 收货地址 -> 列表 -------
    @GetMapping("/addresslist")
    public String addresslist(HttpSession session, Model model) {
        Integer id = (Integer) session.getAttribute("userId");// 已登录用户的id
        List<Address> addressList = addressService.findByUserId(id);
        model.addAttribute("addressList", addressList);
        return "account-address-list";
    }

    // ------ 收货地址 -> 编辑 -------
    @GetMapping("editAddress/{id}")
    public String editAddress(@PathVariable Integer id, Model model) {
        Address address = addressService.findById(id);
        model.addAttribute("address", address);
        return "account-address-edit";
    }

    // ------ 收货地址 -> 新建 -------
    @PostMapping("addAddress")
    public String addAddress(Address address,Integer userId){

        User user = userService.findById(userId);
        address.setUser(user);
        addressService.add(address);

        return "redirect:/account/addresslist";    //需要用重定向redirect，否则页面依然是旧数据
    }

    // ------ 收货地址 -> 更改 -------
    @PostMapping("updateAddress")
    public String updateAddress(Address address){
        //
        addressService.update(address);
        // 1.获取请求参数
        // 2.返回表示层
        return "redirect:/account/addresslist"; //需要用重定向redirect，否则页面依然是旧数据
    }

    // ------ 收货地址 -> 删除 -------
    @GetMapping("deleteAddress/{id}")
    public String deleteAddress(@PathVariable Integer id){
        //
        // 1.获取请求参数
         addressService.delete(id);
        // 2.返回表示层
        return "redirect:/account/addresslist"; //需要用重定向redirect，否则页面依然是旧数据
    }

    // ------ 用户信息 -> 查看 -------
    @GetMapping("/viewUser/{userId}")
    public String viewUser(@PathVariable Integer userId,Model model) {
//		Integer id = (Integer)request.getSession().getAttribute("id");// 已登录用户的id
//        Integer id = Integer.parseInt(request.getParameter("id"));

        User user = userService.findById(userId);
        model.addAttribute("user", user);

        return "account-user-view";
    }

    // ------ 用户信息 -> 编辑页面 -------
    @GetMapping("/editUser/{userId}")
    public String editUser(@PathVariable Integer userId,Model model) {
//		Integer id = (Integer)request.getSession().getAttribute("id");// 已登录用户的id
//        Integer id = Integer.parseInt(request.getParameter("id"));

        User user = userService.findById(userId);
        model.addAttribute("user", user);

        return "account-user-edit";
    }
//    用户信息----->  更改操作
    @PostMapping("/updateUser")
    public String updateUser(User user, String newPassword,Model model,HttpServletRequest request) throws IOException, ServletException {
        // 1.获取请求参数
        // 2.要不要替换密码
        String currPassword = userService.findPasswordById(user.getId());
        String password = user.getPassword();
        if (password.isBlank()) {
            password = null;    // 设为null，便于mapper中进行动态SQL处理(条件update)
        } else if (password != null && !password.equals(currPassword)) {    // 输入的原密码不正确
            request.getSession().setAttribute("pswdErrorMessage", "原密码输入错误，请重新输入");
            return "redirect:/account/editUser/" + user.getId();        // 简单返回页面，重新输入
        } else if (password != null && password.equals(currPassword) && newPassword != null && (!newPassword.isEmpty())) {
            password = newPassword;
        }

        // 4.上传头像文件的处理
        Part part = request.getPart("avatar");
        if (part.getSize() == 0) {    // 若没上传头像，则保持原头像不变
//			user.setAvatar(userService.findById(id).getAvatar());	
            user.setAvatar(null);// 设为null，便于mapper中进行动态SQL处理(条件update)
        } else {                // 若上传头像，则
            String filePath = request.getServletContext().getRealPath("/");
            filePath = filePath + "\\member\\" + user.getId();
            File file = new File(filePath);
            if (!file.exists()) {    // 若目录不存在，则创建目录
                file.mkdirs();
            }
            String fileName = part.getSubmittedFileName();
            String avatar = "member\\" + user.getId() + "\\" + fileName;    // 在web根文件夹的相对路径文件
            part.write(filePath + "\\" + fileName);
            user.setAvatar(avatar);
        }

        // 4、让数据层去更新数据
        userService.update(user);

        // 5.返回表示层
        model.addAttribute("user", user);
        request.getSession().setAttribute("userName", user.getUsername());//重设会话中的username属性，更新页面右上角的用户名
        return "redirect:/account/viewUser/" +user.getId();
    }
}
