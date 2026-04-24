package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

}
