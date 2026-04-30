<%@page import="com.dao.CartDao"%>
<%@page import="com.bean.Cart"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.WishlistDao"%>
<%@page import="com.bean.wishlist"%>
<%@include file="header.jsp" %>

<%

int pid=Integer.parseInt(request.getParameter("pid"));
int uid=u.getUid();
Cart c = new Cart();
c.setPid(pid);
c.setUid(uid);
CartDao.removeFromCart(pid, uid);

List<Cart> list=CartDao.getCartByUser(uid);
session.setAttribute("cart_count", list.size());
response.sendRedirect("cart.jsp");



%>

