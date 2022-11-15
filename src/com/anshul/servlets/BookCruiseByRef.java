package com.anshul.servlets;
import java.io.*;
import java.time.*;
import javax.servlet.*;
import javax.servlet.http.*;
@SuppressWarnings("serial")
public class BookCruiseByRef extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		Cookie ck[] =req.getCookies();
		if(ck!=null) {
			String uName = ck[0].getValue();
			//String pWord = ck[1].getValue();
			
			if(!uName.equals("")||uName != null )
			{
				long cruiseNo =Long.parseLong(req.getParameter("cruiseNo"));
				int seat = 1;
				String fromStn = req.getParameter("fromPort");
				String toStn = req.getParameter("toPort");
				RequestDispatcher rd = req.getRequestDispatcher("UserViewCruises.html");
				rd.include(req, res);
				pw.println("<div class='main'><p1 class='menu'>Your Ticket Booking Information</p1></div>");
				pw.println("<div class='tab'><form action='bookcruises' method='post'>"
						+ "<table>"
						+ "<tr><td>USER ID:</td><td>"+uName+"</td>"
						+ "<td>Cruise NO:</td><td>"+cruiseNo+"</td></tr>"
						+ "<tr><td>From Station:</td><td>"+fromPort+"</td>"
						+ "<td>To Station :</td><td>"+toPort+"</tr>"
						+ "<tr><td>Journey Date:</td><td>"
						+ "<input type='hidden' name='cruisenumber' value='"+cruiseNo+"'>"
						+ "<input type='date' name='journeydate' value='"+LocalDate.now()+"'</td>"
						+ "<td>No of Seats:</td><td><input type='text' name='seats' value='"+seat+"'</td></tr>"
						+ "</table></div>"
						+ "<div class='tab'><p1 class='menu'><input type='submit'value='Pay And Book'></p1></div>"
						+ "</form>");

			}
		}
		else {
			RequestDispatcher rd =req.getRequestDispatcher("UserLogin.html");
			pw.println("<div class='tab'><p1 class='menu'>Please Login First!</p1></div>");
		}
	}
}