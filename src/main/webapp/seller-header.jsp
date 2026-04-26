<%@page import="com.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    User u = null;
    if(session!=null){
    	if(session.getAttribute("u")!=null){
    		u=(User)session.getAttribute("u");
    	}
    	
    }
    %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <link rel="icon" href="img/favicon.png" type="image/png" />
    <title>Eiser ecommerce</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.css" />
    <link rel="stylesheet" href="vendors/linericon/style.css" />
    <link rel="stylesheet" href="css/font-awesome.min.css" />
    <link rel="stylesheet" href="css/themify-icons.css" />
    <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css" />
    <link rel="stylesheet" href="vendors/lightbox/simpleLightbox.css" />
    <link rel="stylesheet" href="vendors/nice-select/css/nice-select.css" />
    <link rel="stylesheet" href="vendors/animate-css/animate.css" />
    <link rel="stylesheet" href="vendors/jquery-ui/jquery-ui.css" />
    <!-- main css -->
    <link rel="stylesheet" href="css/style.css" />
    <link rel="stylesheet" href="css/responsive.css" />
  </head>

  <body>
    <!--================Header Menu Area =================-->
    <header class="header_area">
      <div class="main_menu">
        <div class="container-xxl" style="padding-left:60px">
          <nav class="navbar navbar-expand-lg navbar-light w-100">
            <!-- Brand and toggle get grouped for better mobile display -->
            <a class="navbar-brand logo_h" href="index.jsp">
              <img src="img/logo.png" alt="" />
            </a>
            <button
              class="navbar-toggler"
              type="button"
              data-toggle="collapse"
              data-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div
              class="collapse navbar-collapse offset w-100"
              id="navbarSupportedContent"
            >
              <div class="row w-100 mr-0">
                <div class="col-lg-9 pr-0">
                  <ul class="nav navbar-nav center_nav pull-right">
                    <li class="nav-item">
                      <a class="nav-link" href="index.jsp">Home</a>
                    </li>
                   
                    
                     
                    
                    <% if(u==null){
                    %>
                      <ul class="nav navbar-nav center_nav pull-right">
                        <li class="nav-item">
                          <a class="nav-link" href="signup.jsp">Sign Up</a>
                        </li>
                         <li class="nav-item">
                          <a class="nav-link" href="login.jsp">Log in</a>
                        </li>
                         <%
                        }
                    else{
                    %>
                    
                    <li class="nav-item">
                          <a class="nav-link" href="contact.jsp">Contact</a>
                        </li>
                         <li class="nav-item">
                          <a class="nav-link" href="seller-add-product.jsp">Add Product</a>
                        </li>
                         <li class="nav-item">
                          <a class="nav-link" href="view-product.jsp">View Product</a>
                        </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="seller-change-password.jsp">Change Password</a>
                      </li>
                      
                    	<li class="nav-item">
                        <a class="nav-link" href="logout.jsp">Log out (<%=u.getFname() %>)</a>
                      </li>
                      
                      <li class="nav-item">
                       <a class="nav-link" href="seller-profile.jsp">
                        <img  href="seller-profile.jsp" src="profile_picture/<%=u.getProfile_picture()%>" style="height:50px; width:50px; border-radius:50%;  ">
                        </a>
                         </li>
                      
                    <%
                    }
                    %>
                        
                        </ul>
                       
                </div>

             
              </div>
            </div>
          </nav>
        </div>
      </div>
    </header>
    <!--================Header Menu Area =================-->

   
</body>

</html>