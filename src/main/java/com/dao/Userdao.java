package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bean.User;
import com.util.ProjectUtil;

public class Userdao {
	
	public static boolean checkEmail(String email) {
		boolean flag=false;
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql="select * from user where email=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				flag = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}
	
	
	public static void signupUser(User u) {
		try {
			
			Connection conn=ProjectUtil.createConnection();
			String sql="insert into user(fname,lname,email,mobile,address,password,profile_picture) values(?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, u.getFname());
			pst.setString(2, u.getLname());
			pst.setString(3, u.getEmail());
			pst.setLong(4, u.getMobile());
			pst.setString(5, u.getAddress());
			pst.setString(6, u.getPassword());
			pst.setString(7, u.getProfile_picture());
			pst.executeUpdate();
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static User loginUser(String email) {
	 
		User u = null;
		
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql="select * from user where email=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				u = new User();
				u.setUid(rs.getInt("uid"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setEmail(rs.getString("email"));
				u.setMobile(rs.getLong("mobile"));
				u.setAddress(rs.getString("address"));
				u.setPassword(rs.getString("password"));
				u.setProfile_picture(rs.getString("profile_picture"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return u;
	
	}
	
	public static void updateProfile(User u) {
		
		try {
			
			Connection conn=ProjectUtil.createConnection();
			String sql="update user set fname=?,lname=?,mobile=?,address=? where email=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,u.getFname());
			pst.setString(2, u.getLname());
			pst.setLong(3, u.getMobile());
			pst.setString(4, u.getAddress());
			pst.setString(5, u.getEmail());
			pst.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public static void changePassword(String new_password,String email) {
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql = "update user set password=? where email=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,new_password);
			pst.setString(2, email);
			pst.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	}


