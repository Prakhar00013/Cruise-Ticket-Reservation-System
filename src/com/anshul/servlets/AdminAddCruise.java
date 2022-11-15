package com.anshul.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.anshul.utility.DBConnection;

import java.sql.*;
@SuppressWarnings("serial")
public class AdminAddCruise extends HttpServlet{

	/**
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Cookie ck[] = req.getCookies();
		if(ck!=null) {
			String uName = ck[0].getValue();
			String pWord = ck[1].getValue();
			if(!uName.equals("")||uName!=null) {
				try {
					Connection con = DBConnection.getCon();
					PreparedStatement ps = con.prepareStatement("insert into cruise6 values(?,?,?,?,?,?)");
					ps.setLong(1, Long.parseLong(req.getParameter("cruiseno")));
					ps.setString(2,req.getParameter("cruisename"));
					ps.setString(3, req.getParameter("fromport"));
					ps.setString(4, req.getParameter("toport"));
					ps.setLong(5, Long.parseLong(req.getParameter("available")));
					ps.setLong(6,Long.parseLong(req.getParameter("fare")));
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						RequestDispatcher rd = req.getRequestDispatcher("AddCruises.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>Cruise Added Successfully!</p1></div>");
					}
					else {
						RequestDispatcher rd = req.getRequestDispatcher("AddCruises.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>Error in filling the cruise Detail</p1></div>");
					}
				}
				catch(Exception e) {}				
			}
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>Please Login first !</p1></div>");
		}
	}

}
