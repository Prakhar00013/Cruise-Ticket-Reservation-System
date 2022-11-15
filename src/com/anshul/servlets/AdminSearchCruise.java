package com.anshul.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.anshul.utility.DBConnection;

import java.sql.*;
@SuppressWarnings("serial")
public class AdminSearchCruise extends HttpServlet{

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
					PreparedStatement ps = con.prepareStatement("Select * from cruise6 where cr_no=?");
					ps.setLong(1,Long.parseLong(req.getParameter("cruisenumber")));
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						RequestDispatcher rd = req.getRequestDispatcher("AdminSearchCruise.html");
						rd.include(req, res);
						pw.println("<div class='main'><p1 class='menu'>Searched Cruise Detail</p1></div>");
						pw.println("<div class='tab'>"
								+ "<table>"
								+ "<tr><td class='blue'>Cruise Name :</td><td>"+rs.getString("cr_name")+"</td></tr>"
								+ "<tr><td class='blue'>Cruise Number :</td><td>"+rs.getLong("cr_no")+"</td></tr>"
								+ "<tr><td class='blue'>From Port :</td><td>"+rs.getString("from_Port")+"</td></tr>"
								+ "<tr><td class='blue'>To Port :</td><td>"+rs.getString("to_Port")+"</td></tr>"
								+ "<tr><td class='blue'>Available Seats:</td><td>"+rs.getLong("available")+"</td></tr>"
								+ "<tr><td class='blue'>Fare (INR) :</td><td>"+rs.getLong("fare")+" RS</td></tr>"
								+ "</table>"
								+ "</div>");
					}
					else {
						RequestDispatcher rd = req.getRequestDispatcher("AdminSearchCruise.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>Cruise No."+req.getParameter("cruisenumber")+" is Not Available !</p1></div>");
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
