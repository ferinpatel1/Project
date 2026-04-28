<%@page import="com.dao.WishlistDao"%>
<%@page import="com.bean.wishlist"%>
<%@include file="header.jsp" %>

<%

int pid=Integer.parseInt(request.getParameter("pid"));
int uid=u.getUid();
wishlist w = new wishlist();
w.setPid(pid);
w.setUid(uid);
WishlistDao.removeFromwishlist(pid, uid);
response.sendRedirect("wishlist.jsp");



%>