package com.anshul.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
@SuppressWarnings("serial")
public class BookCruiseFwd extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("text/html");
		Cookie ck[] =req.getCookises();
		if(ck!=null) {
			String uName = ck[0].getValue();
			//String pWord = ck[1].getValue();
			if(!uName.equals("")|| uName != null)
			{
				RequestDispatcher rd =req.getRequestDispatcher("BookCruises.html");
				rd.forward(req, res);
			}
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
			PrintWriter pw = res.getWriter();
			pw.println("<div class='tab'><p1 class='menu'>Please Login First !</p1></div>");
			rd.include(req, res);
		}
	}

}