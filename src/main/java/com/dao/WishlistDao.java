package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.wishlist;
import com.util.ProjectUtil;

public class WishlistDao {

	
	public static void AddToWishlist(wishlist w) {
		
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql ="insert into wishlist(pid,uid) values(?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, w.getPid());
			pst.setInt(2, w.getUid());
			pst.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static List<wishlist> getWishlistByUser(int uid){
		List<wishlist> list=new ArrayList<wishlist>();
		
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql="select * from wishlist where uid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				wishlist w=new wishlist();
				w.setWid(rs.getInt("wid"));
				w.setPid(rs.getInt("pid"));
				w.setUid(rs.getInt("uid"));
				list.add(w);
				
			}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static boolean checkwishlist(int pid,int uid) {
		Boolean flag=false;
		
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql="select * from wishlist where uid=? and pid=?";
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
	
	public static void removeFromwishlist(int pid,int uid) {
	
		
		try {
			Connection conn = ProjectUtil.createConnection();
			String sql="delete  from wishlist where uid=? and pid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, uid);
			pst.setInt(2, pid);
			pst.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
