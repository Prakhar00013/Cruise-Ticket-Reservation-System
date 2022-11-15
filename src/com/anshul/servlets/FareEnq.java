package com.anshul.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.anshul.utility.DBConnection;

import java.sql.*;
@SuppressWarnings("serial")
public class FareEnq extends HttpServlet{
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
					PreparedStatement ps = con.prepareStatement("Select * from cruise6 where from_port=? and to_port=?");
					ps.setString(1,req.getParameter("fromport"));
					ps.setString(2, req.getParameter("toport"));
					ResultSet rs = ps.executeQuery();
					if(rs.next()) 
					{
						RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
						rd.include(req, res);
						pw.println("<div class='tab'>" + 
								"		<p1 class='menu'>" + 
								"	Hello "+uName+" ! Welcome to our new NITCTC Website" + 
								"		</p1>" + 
								"	</div>");
						pw.println("<div class='main'><p1 class='menu'>Fare for Cruises BetWeen Port "+req.getParameter("fromport")+" and "+req.getParameter("toport")+" is <p2 class=\"red\">RS "+rs.getLong("fare")+"</p2></p1></div>");
						pw.println("<div class='tab'><table><tr><th>Cruise Name</th><th>Cruise No</th>"
								+ "<th>From Port</th><th>To Port</th><th>Seats</th><th>Fare (INR)</th></tr>");
						do {
								pw.println(""
								+ "<tr><td>"+rs.getString("cr_name")+"</td>"
								+ "<td>"+rs.getLong("cr_no")+"</td>"
								+ "<td>"+rs.getString("from_Port")+"</td>"
								+ "<td>"+rs.getString("to_Port")+"</td>"
								+ "<td>"+rs.getLong("available")+"</td>"
								+ "<td>"+rs.getLong("fare")+" RS</td></tr>");
					}while(rs.next());
						pw.println("</table></div>");
						}
					else {
						RequestDispatcher rd = req.getRequestDispatcher("CruiseBwPort.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>There are no cruises Between"+req.getParameter("fromport")+" and "+req.getParameter("toport")+"</p1></div>");					}
				}
				catch(Exception e) {}
				
			}
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>Please Login first !</p1></div>");
		}
	}
}
