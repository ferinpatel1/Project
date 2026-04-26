package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.Product;
import com.util.ProjectUtil;

public class ProductDao {
	
	public static void addProduct(Product p) {
		try {
			
			Connection conn = ProjectUtil.createConnection();
			String sql = "insert into product(product_category,product_name,product_price,product_desc,product_image,uid) values(?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setString(1, p.getProduct_category());
			pst.setString(2, p.getProduct_name());
			pst.setInt(3, p.getProduct_price());
			pst.setString(4, p.getProduct_desc());
			pst.setString(5, p.getProduct_image());
			pst.setInt(6,p.getUid());
			pst.executeUpdate();
			
			
		} catch (Exception e) {
		}
	}
	
	public static List<Product> getProductBySeller(int uid){
		List<Product> list = new ArrayList<Product>();
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql ="select * from product where uid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, uid);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Product p=new Product();
				p.setPid(rs.getInt("pid"));
				p.setUid(rs.getInt("uid"));
				p.setProduct_price(rs.getInt("product_price"));
				p.setProduct_category(rs.getString("product_category"));
				p.setProduct_desc(rs.getString("product_desc"));
				p.setProduct_name(rs.getString("product_name"));
				p.setProduct_image(rs.getString("product_image"));
				list.add(p);
				
			}
			
		} catch (Exception e) {
			
		}
		
		return list;
		
	}
	
	public static Product getProduct(int pid){
		Product p = new Product();
		try {
			Connection conn=ProjectUtil.createConnection();
			String sql ="select * from product where pid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, pid);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				
				p.setPid(rs.getInt("pid"));
				p.setUid(rs.getInt("uid"));
				p.setProduct_price(rs.getInt("product_price"));
				p.setProduct_category(rs.getString("product_category"));
				p.setProduct_desc(rs.getString("product_desc"));
				p.setProduct_name(rs.getString("product_name"));
				p.setProduct_image(rs.getString("product_image"));
				
				
			}
			
		} catch (Exception e) {
			
		}
		
		return p;
		
	}

	public static void UpdateProduct(Product p) {
		
try {
			
			Connection conn = ProjectUtil.createConnection();
			String sql = "update product set product_category=?,product_name=?,product_price=?,product_desc=?,product_image=? where pid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setString(1, p.getProduct_category());
			pst.setString(2, p.getProduct_name());
			pst.setInt(3, p.getProduct_price());
			pst.setString(4, p.getProduct_desc());
			pst.setString(5, p.getProduct_image());
			pst.setInt(6,p.getPid());
			
			pst.executeUpdate();
			
			
		} catch (Exception e) {
		}
		
	
		
	}


}
