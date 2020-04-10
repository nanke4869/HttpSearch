package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DBTool.*;

public class Search extends HttpServlet{
	public Search() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		  // Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			   throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			   throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
	
		String searchString = request.getParameter("search");
		try {
			if (searchString.equals("Temperature")) {
				Connection con=DBUtil.getConnection();
				Statement stmt=con.createStatement();
				String sql="select Temperature from demodatabase.TemandHum";
				ResultSet rs=stmt.executeQuery(sql);
			    while(rs.next())
			    {
			    	String tmp = rs.getString("Temperature");
			    	out.write(tmp);
			    }
			}
			else if(searchString.equals("Humidity")) {
				Connection con=DBUtil.getConnection();
				Statement stmt=con.createStatement();
				String sql="select Humidity from demodatabase.TemandHum";
				ResultSet rs=stmt.executeQuery(sql);
			    while(rs.next())
			    {
			    	String hum = rs.getString("Humidity");
			    	out.write(hum);
			    }
			}
		}catch(Exception ex)
        {
        	ex.printStackTrace();
        	out.println("异常");
        }
		finally
        {
        	DBUtil.Close();
        	//out.print(type);
        	out.flush();
        	out.close();
        }
		
	}
}
