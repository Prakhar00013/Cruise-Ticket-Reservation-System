package com.anshul.servlets;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.anshul.utility.DBConnection;
@SuppressWarnings("serial")
public class UserViewCruiseFwd extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Cookie ck[] = req.getCookies();
		if(ck!=null) {
			String uName = ck[0].getValue();
			if(!uName.equals("")||uName!=null) {
				try {
					Connection con = DBConnection.getCon();
					PreparedStatement ps = con.prepareStatement("Select * from cruise6");
					ResultSet rs = ps.executeQuery();
					if(rs.next()) 
					{
						RequestDispatcher rd = req.getRequestDispatcher("UserViewCruises.html");
						rd.include(req, res);
						pw.println("<div class='tab'>" + 
								"		<p1 class='menu'>" + 
								"	Hello "+uName+" ! Welcome to our new NITCTC Website" + 
								"		</p1>" + 
								"	</div>");
						pw.println("<div class='main'><p1 class='menu'>Running Cruises</p1></div>");
						pw.println("<div class='tab'><table><tr><th>Cruise Name</th><th>Cruise Number</th>"
								+ "<th>From Port</th><th>To Port</th><th>Seats Available</th><th>Fare (INR)</th><th>Booking</th></tr>");
						long cruiseNo;
						String fromPort;
						String toPort;
						do {
							 cruiseNo = rs.getLong("cr_no");
							 fromPort = rs.getString("from_port");
							 toPort = rs.getString("to_port");
								pw.println(""
								+ "<tr> "
								+ ""
								+ "<td><a href='view?cruiseNo="+cruiseNo+"&fromPort="+fromPort+"&toPort="+toPort+"'>"+rs.getString("cr_name")+"</a></td>"
								+ "<td>"+cruiseNo+"</td>"
								+ "<td>"+fromPort+"</td>"
								+ "<td>"+toPort+"</td>"
								+ "<td>"+rs.getLong("available")+"</td>"
								+ "<td>"+rs.getLong("fare")+" RS</td>"
								+ "<td><a href='bookcruisebyref?cruiseNo="+cruiseNo+"&fromPort="+fromPort+"&toPort="+toPort+"'><div class='red'>Book Now</div></a></td></tr>"
							);
						}while(rs.next());
						pw.println("</table></div>");
					}
					else {
						RequestDispatcher rd = req.getRequestDispatcher("UserViewCruises.html");
						rd.include(req, res);
						pw.println("<div class='main'><p1 class='menu red'> No Running Cruises</p1></div>");
					}
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
