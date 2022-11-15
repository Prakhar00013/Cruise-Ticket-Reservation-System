package com.anshul.servlets;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.anshul.utility.DBConnection;
@SuppressWarnings("serial")
public class AdminCruiseUpdate extends HttpServlet{

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
						long trNo = Long.parseLong(req.getParameter("cruisenumber"));
						PreparedStatement ps = con.prepareStatement("select * from cruise6 where cr_no=?");
						ps.setLong(1, crNo);
						ResultSet rs = ps.executeQuery();
						if(rs.next()) {
						RequestDispatcher rd = req.getRequestDispatcher("AdminHome.html");
						rd.include(req, res);
						pw.println("<div class='tab'>Cruise Schedule Update</div>");
						pw.println("<div class='tab'>"
								+ "<table><form action='updatecruiseschedule' method='post'>"
								+ "<tr><td>Cruise No :</td><td><input type='text' name='cruiseno' value='"+rs.getLong("tr_no")+"'></td></tr>"
								+ "<tr><td>Cruise Name :</td><td><input type='text' name='cruisename' value='"+rs.getString("tr_name")+"'></td></tr>"
								+ "<tr><td>From Port :</td><td><input type='text' name='fromport' value='"+rs.getString("from_port")+"'></td></tr>"
								+ "<tr><td>To Port :</td><td><input type='text' name='toport' value='"+rs.getString("to_port")+"'></td></tr>"
								+ "<tr><td>Available seats:</td><td><input type='text' name='available' value='"+rs.getLong("available")+"'></td></tr>"
								+ "<tr><td>Fare (INR) :</td><td><input type='text' name='fare' value='"+rs.getLong("fare")+"'></td></tr>"
								+ "<tr><td></td><td><input type='submit' name='submit' value='Update Cruise Schedule'></td></tr>"
								+ "</form></table>"
								+ "</div>");
						}
						else {
							RequestDispatcher rd = req.getRequestDispatcher("AdminUpdateCruise.html");
							rd.include(req, res);
							pw.println("<div class='tab'>Cruise Not Available</div>");
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
