package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.Cart;
import com.bean.wishlist;
import com.util.ProjectUtil;

public class CartDao {

	
	public static void AddToCart(Cart c) {
		
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql ="insert into cart(pid,uid,product_price,product_qty,total_price,payment_status) values(?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, c.getPid());
			pst.setInt(2, c.getUid());
			pst.setInt(3, c.getProduct_price());
			pst.setInt(4, c.getProduct_qty());
			pst.setInt(5, c.getTotal_price());
			pst.setBoolean(6, c.isPayment_status());
			pst.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static List<Cart> getCartByUser(int uid){
		List<Cart> list=new ArrayList<>();
		
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql="select * from cart where uid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Cart c=new Cart();
				c.setCid(rs.getInt("cid"));
				c.setPid(rs.getInt("pid"));
				c.setUid(rs.getInt("uid"));
				c.setProduct_price(rs.getInt("product_price"));
				c.setProduct_qty(rs.getInt("product_qty"));
				c.setTotal_price(rs.getInt("total_price"));
				c.setPayment_status(rs.getBoolean("payment_status"));
				
				list.add(c);
				
			}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static Cart getCart(int cid)
	{
		Cart c=null;
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql="select * from cart where cid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, cid);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				c=new Cart();
				c.setCid(rs.getInt("cid"));
				c.setPid(rs.getInt("pid"));
				c.setUid(rs.getInt("uid"));
				c.setProduct_price(rs.getInt("product_price"));
				c.setProduct_qty(rs.getInt("product_qty"));
				c.setTotal_price(rs.getInt("total_price"));
				c.setPayment_status(rs.getBoolean("payment_status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	
	public static boolean checkCart(int pid,int uid) {
		Boolean flag=false;
		
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql="select * from cart where uid=? and pid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			pst.setInt(2, pid);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				flag=true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public static void removeFromCart(int pid,int uid) {
	
		
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql="delete  from cart where uid=? and pid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			pst.setInt(2, pid);
			pst.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void updateCart(Cart c)
	{
		
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql="update cart set product_qty=?, total_price=? where cid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, c.getProduct_qty());
			pst.setInt(2, c.getTotal_price());
			pst.setInt(3, c.getCid());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	public static void updatePaymentStatus(int uid)
	{
		boolean flag=true;
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql="update cart set payment_status=? where uid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setBoolean(1,flag);
			pst.setInt(2, uid);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
