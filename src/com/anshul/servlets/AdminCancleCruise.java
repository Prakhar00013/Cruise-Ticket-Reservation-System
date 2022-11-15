package com.anshul.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.anshul.utility.DBConnection;

import java.sql.*;
@SuppressWarnings("serial")
public class AdminCancleCruise extends HttpServlet{

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
					PreparedStatement ps = con.prepareStatement("delete from cruise6 where cr_no=?");
					ps.setLong(1,Long.parseLong(req.getParameter("cruiseno")));
					int k = ps.executeUpdate();
					if(k==1) {
						RequestDispatcher rd = req.getRequestDispatcher("CancleCruise.html");
						rd.include(req, res);
						pw.println("<div class='main'><p1 class='menu'>Cruise number "+req.getParameter("cruiseno")+" has been Cancelled Successfully.</p1></div>");
					}
					else {
						RequestDispatcher rd = req.getRequestDispatcher("CancleCruise.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>Cruise No."+req.getParameter("cruiseno")+" is Not Available !</p1></div>");
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
