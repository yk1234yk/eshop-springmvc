<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>个人中心-修改用户信息</title>
	<link rel="shortcut icon" href="assets/images/favicon.png" />
	<link href="../../../assets/css/theme-plugin.css" rel="stylesheet" />
	<link href="../../../assets/css/theme.min.css" rel="stylesheet" />
</head>

<body class="">

<!-- page wrapper start -->

<div class="page-wrapper"> 
  
    <%@ include file="header.jsp" %>	

	<!--hero section start-->
	<section class="bg-light py-6">
	  <div class="container">
	    <div class="row align-items-center">
	      <div class="col-md-6">
	        <h1 class="h2 mb-0">我的账号</h1>
	      </div>
	      <div class="col-md-6 mt-3 mt-md-0">
	        <nav aria-label="breadcrumb">
	          <ol class="breadcrumb justify-content-md-end bg-transparent p-0 m-0">
	            <li class="breadcrumb-item"><a class="link-title" href="index">首页</a></li>
	            <li class="breadcrumb-item active text-primary" aria-current="page">个人中心</li>
	          </ol>
	        </nav>
	      </div>
	    </div>
	    <!-- / .row -->
	  </div>
	  <!-- / .container -->
	</section>
	<!--hero section end--> 
	<!--body content start-->
	<div class="page-content">

	<section class="dashboard-page">
	  <div class="container">
	        <div class="row">
	            <div class="col-lg-3 col-md-4">
	                <div class="dashboard_menu">
	                    <ul class="nav nav-tabs border-0 flex-column" role="tablist">
	                      <li class="nav-item">
	                        <a class="nav-link" href="account?method=dashboard&id=${id}" >
	                        主页面</a>
	                      </li>
	                      <li class="nav-item">
	                        <a class="nav-link" href="account?method=orderlist&id=${id}">
	                        我的订单</a>
	                      </li>
	                      <li class="nav-item">
	                        <a class="nav-link" href="account?method=addresslist&id=${id}">
	                        收货地址</a>
	                      </li>
	                      <li class="nav-item">
	                        <a class="nav-link active" href="account?method=viewUser&id=${id}">
	                        账号信息</a>
	                      </li>
	                      <li class="nav-item">
	                        <a class="nav-link" href="cart?method=view&id=${id}">
	                        购物车</a>
	                      </li>
	                      <li class="nav-item">
	                        <a class="nav-link" href="user?method=logout">
	                        退出</a>
	                      </li>
	                    </ul>
	                </div>
	            </div>
	            <div class="col-lg-9 col-md-8 mt-5 mt-lg-0 mt-md-0">
	                <div class="dashboard_content">
	                    <div class="tab-pane" id="account-detail" role="tabpanel" aria-labelledby="account-detail-tab">
							<div class="card border-0">
	                           <h5 class="font-w-6">修改账号信息</h5>
	                            <div class="card-body">
	                                <form action="account?method=updateUser" method="post" name="enq" enctype="multipart/form-data">
	                                	<input name="id" type="hidden" value="${user.id}">
	                                    <div class="row">
	                                        <div class="form-group col-md-12">
	                                        	<label>用户名 <span class="required">*</span></label>
	                                            <input required class="form-control" name="username" type="text" value="${user.username}">
	                                         </div>
	                                         <div class="form-group col-md-12">
	                                        	<label>用户头像 <span class="required">（不选择，则维持原头像）</span></label>
	                                            <input class="form-control" name="avatar" type="file"/>
	                                            <img src="${user.avatar}">
	                                         </div>
	                                        <div class="form-group col-md-12">
	                                        	<label>手机号 <span class="required">*</span></label>
	                                            <input required class="form-control" name="phone" type="text"  value="${user.phone}">
	                                        </div>
	                                        <div class="form-group col-md-12">
	                                        	<label>Email邮箱 <span class="required">*</span></label>
	                                            <input required class="form-control" name="email" type="email"  value="${user.email}">
	                                        </div>
	                                        <div class="form-group col-md-12">
	                                        	<label>当前密码 <span>(空白，表示维持原有密码)</span></label>
	                                            <input class="form-control" name="password" type="password">
	                                            <div class="help-block with-errors">${pswdErrorMessage}</div>
	                                        </div>
	                                        <div class="form-group col-md-12">
	                                        	<label>新密码 </label>
	                                            <input class="form-control" name="newpassword" type="password">
	                                        </div>
	                                        <div class="form-group col-md-12">
	                                        	<label>再输一次密码 </label>
	                                            <input class="form-control" name="cpassword" type="password">
	                                        </div>
	                                        <div class="col-md-12">
	                                            <button type="submit" class="btn btn-primary" name="submit" value="Submit">保存</button>
	                                        </div>
	                                    </div>
	                                </form>
	                            </div>
	                        </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	</div>
	<!--body content end--> 

	<%@ include file="footer.jsp" %>	  
	</div>
</body>
</html>
