package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

import com.bean.User;
import com.dao.Userdao;

@WebServlet("/UserController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 512 , maxRequestSize = 1024 * 1024 * 512  ) //512 mb data
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private String extractfilename(Part file){
		String cd=file.getHeader("content-disposition");
		System.out.println(cd); // form-data; name="product_image"; filename="user.jpg";
		String[] items = cd.split(";");
		for(String string : items) {
			if(string.trim().startsWith("filename")) {
				return string.substring(string.indexOf("=") + 2,string.length() - 1 );
			}
		}
		
		
		
		return "";
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		
		if(action.equalsIgnoreCase("sign up")) {
			
			boolean flag=Userdao.checkEmail(request.getParameter("email"));
			if(flag==false) {
				
				if(request.getParameter("password").equals(request.getParameter("cpassword"))) {
				User u =new User();
				u.setUsertype(request.getParameter("usertype"));
				u.setFname(request.getParameter("fname"));
				u.setLname(request.getParameter("lname"));
				u.setEmail(request.getParameter("email"));
				u.setMobile(Long.parseLong(request.getParameter("mobile")));
				u.setAddress(request.getParameter("address"));
				u.setPassword(request.getParameter("password"));
				
				
				String savepath = "/Users/ferin/Desktop/Java/Project/src/main/webapp/profile_picture";
				File fileSaveDir = new File(savepath);
				if(!fileSaveDir.exists()) {
					fileSaveDir.mkdir();
				}
				Part file1 = request.getPart("profile_picture");
				String fileName = extractfilename(file1);
				file1.write(savepath + File.separator + fileName); 
				String savePath2 = "/Users/ferin/Desktop/Java/Project/src/main/webapp/profile_picture";
				File imgSaveDir =new File(savePath2);
				if(!imgSaveDir.exists()) {
					imgSaveDir.mkdir();
				}
				u.setProfile_picture(fileName);
				Userdao.signupUser(u);
				
				request.setAttribute("msg", "User Sign Up Succesfully");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
				
				}else {
					request.setAttribute("msg", "Password & Confirm password Not matching");
					request.getRequestDispatcher("signup.jsp").forward(request, response);
				}
				
				
				
			}
			else {
				request.setAttribute("msg", "Email Alredy Registred");
				request.getRequestDispatcher("signup.jsp").forward(request, response);
			}
			
			
			
			
		}
		
		else if(action.equalsIgnoreCase("login")) {
			User u=Userdao.loginUser(request.getParameter("email"));
			if(u==null) {
				request.setAttribute("msg", "Email not registered");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			else {
				if(u.getPassword().equals(request.getParameter("password"))){
					
					HttpSession session =request.getSession();
					session.setAttribute("u", u);
					
					if(u.getUsertype().equals("buyer")) {
					request.getRequestDispatcher("index.jsp").forward(request, response);
					}else {
						request.getRequestDispatcher("seller-index.jsp").forward(request, response);
						
					}
					
				}
				else {
					request.setAttribute("msg", "Incorrect Password");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
				
			}
			
		}
		
		else if(action.equalsIgnoreCase("update profile")) {
			HttpSession session = request.getSession();
			User u = (User)session.getAttribute("u");
			u.setFname(request.getParameter("fname"));
			u.setLname(request.getParameter("lname"));
			u.setMobile(Long.parseLong(request.getParameter("mobile")));
			u.setAddress(request.getParameter("address"));
			Userdao.updateProfile(u);
			
			request.setAttribute("msg", "User Profile Upadated Succesfully");
			session.setAttribute("u", u);
			if(u.getUsertype().equals("buyer")) {
				request.getRequestDispatcher("profile.jsp").forward(request, response);
	    	}
	    	else {
	    		request.getRequestDispatcher("seller-profile.jsp").forward(request, response);
	    		
	    	}
			
			
	}
		else if(action.equalsIgnoreCase("changepassword")) {
			HttpSession session = request.getSession();
			User u = (User)session.getAttribute("u");
			if(u.getPassword().equals(request.getParameter("old_password"))) {
				if(request.getParameter("new_password").equals(request.getParameter("cpassword"))) {
				    if(!u.getPassword().equals(request.getParameter("new_password"))) {
				    	
				    	Userdao.changePassword(request.getParameter("new_password"), u.getEmail());
				    	request.setAttribute("msg", "Password Changed succesfully");
				    	session.removeAttribute("u");
				    	session.invalidate();
				    	request.getRequestDispatcher("login.jsp").forward(request, response);
				    	
				    	
				    }
				    else {
				    	request.setAttribute("msg", "New password can not be same as old");
				    	if(u.getUsertype().equals("buyer")) {
				    		request.getRequestDispatcher("change-password.jsp").forward(request, response);
				    	}
				    	else {
				    		request.getRequestDispatcher("seller-change-password.jsp").forward(request, response);
				    		
				    	}
				    }
				    	
				    }
				 else {
				    	request.setAttribute("msg", "New password and Confirm password not matching");
				    	if(u.getUsertype().equals("buyer")) {
				    		request.getRequestDispatcher("change-password.jsp").forward(request, response);
				    	}
				    	else {
				    		request.getRequestDispatcher("seller-change-password.jsp").forward(request, response);
				    		
				    	}
				    }
				}
			 else {
			    	request.setAttribute("msg", "Enter Correct old Password");
			    	if(u.getUsertype().equals("buyer")) {
			    		request.getRequestDispatcher("change-password.jsp").forward(request, response);
			    	}
			    	else {
			    		request.getRequestDispatcher("seller-change-password.jsp").forward(request, response);
			    		
			    	}
			    }
			
		}
	}
}
