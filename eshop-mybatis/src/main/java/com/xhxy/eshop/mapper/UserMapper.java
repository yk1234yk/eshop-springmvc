package com.xhxy.eshop.mapper;

import org.apache.ibatis.annotations.Param;

import com.xhxy.eshop.entity.Admin;
import com.xhxy.eshop.entity.User;

public interface UserMapper {
    // 添加用户（注册）
    public Integer addUser(User user);

    // 登录login
    public Integer login(@Param("username") String username, @Param("password") String password);

    // 查询用户
    public User findById(int id);

    // 修改用户信息
    public Integer update(User user);

    // 用id获取密码
    public String findPasswordById(int id);

    // 查询admin管理员
    public Admin findAdminById(int id);

}
