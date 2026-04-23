<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

session.removeAttribute("u");
session.invalidate();
request.setAttribute("msg", "User logged Out Successfully");
request.getRequestDispatcher("login.jsp").forward(request, response);

%>