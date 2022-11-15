package com.anshul.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.anshul.utility.DBConnection;

import java.sql.*;
@SuppressWarnings("serial")
public class UpdateCruiseSchedule extends HttpServlet{
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
					PreparedStatement ps = con.prepareStatement("update cruise6 set cr_no=?,cr_name=?,from_port=?,to_port=?,available=?,fare=? where cr_no=?");
					ps.setLong(1, Long.parseLong(req.getParameter("cruiseno")));
					ps.setString(2,req.getParameter("cruisename"));
					ps.setString(3, req.getParameter("fromport"));
					ps.setString(4, req.getParameter("toport"));
					ps.setLong(5, Long.parseLong(req.getParameter("available")));
					ps.setLong(6,Long.parseLong(req.getParameter("fare")));
					ps.setLong(7, Long.parseLong(req.getParameter("cruiseno")));
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						RequestDispatcher rd = req.getRequestDispatcher("AdminUpdateCruise.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>Cruise Updated Successfully!</p1></div>");
					}
					else {
						RequestDispatcher rd = req.getRequestDispatcher("AdminUpdateCruise.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>Error in filling the Cruise Detail</p1></div>");
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
