<%@page import="com.dao.ProductDao"%>
<%@page import="com.bean.Product"%>
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
Product p = ProductDao.getProduct(pid);
c.setPid(pid);
c.setUid(uid);
c.setProduct_price(p.getProduct_price());
c.setProduct_qty(1);
c.setPayment_status(false);
CartDao.AddToCart(c);

List<Cart> list=CartDao.getCartByUser(uid);
session.setAttribute("cart_count", list.size());
response.sendRedirect("cart.jsp");



%>

