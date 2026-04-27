<%@page import="com.dao.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 

int pid=Integer.parseInt(request.getParameter("pid"));

ProductDao.DeleteProduct(pid);
response.sendRedirect("view-product.jsp");


%>

