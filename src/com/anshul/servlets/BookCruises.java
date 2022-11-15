package com.anshul.servlets;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.anshul.utility.DBConnection;
@SuppressWarnings("serial")
public class BookCruises extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		Cookie ck[] =req.getCookies();
		if(ck!=null) {
			String uName = ck[0].getValue();
			//String pWord = ck[1].getValue();
			RequestDispatcher rd = req.getRequestDispatcher("BookCruises.html");
			rd.include(req, res);
			if(!uName.equals("")||uName != null )
			{
				long seat =Long.parseLong(req.getParameter("seats"));
				long cruiseNo =Long.parseLong(req.getParameter("cruisenumber"));
				try {
					Connection con = DBConnection.getCon();
					PreparedStatement ps = con.prepareStatement("Select * from cruise6 where cr_no = ?");
					ps.setLong(1, cruiseNo);
					ResultSet rs = ps.executeQuery();
					if(rs.next())
					{
						long avail =rs.getLong("available");
						if(seat>avail) {
							pw.println("<div class='tab'><p1 class='menu red'>Only "+avail+" Seats are Available in this Cruise!</p1></div>");
							
						}
						else if(seat<=avail) {
							avail = avail - seat;
							PreparedStatement ps1 = con.prepareStatement("update cruise6 set available=? where cr_no=?");
							ps1.setLong(1, avail);
							ps1.setLong(2, cruiseNo);
							int k= ps1.executeUpdate();
							if(k==1)
							{
								pw.println("<div class='tab'><p1 class='menu green'>"+seat+" Seats Booked Successfully!</p1></div>");
							
								
							}
							else {
								pw.println("<div class='tab'><p1 class='menu red'>Transaction Declined. Try Again !</p1></div>");
								
							}
						}
					}
					else {
						pw.println("<div class='tab'><p1 class='menu'>Invalid Cruise Number !</p1></div>");
						
					}
					
				}
				catch(Exception e) {}
			}
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
			pw.println("<div class='tab'><p1 class='menu'>Please Login First !</p1></div>");
			rd.include(req, res);
		}
	}

}
