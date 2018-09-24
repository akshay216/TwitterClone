package finalproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet is invoked when a new user sign up and enters the details it is
 * performing the function of getting the values entered into the database by
 * calling log function of UserDAO
 * 
 * @author Akshay Sehgal create date: 8th august 2014
 *
 */
public class MembershipUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MembershipUpdate() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			UserBean user = new UserBean();// creating new userbean object
			// getting data from the html page
			user.setFullName(request.getParameter("fname"));
			user.setEmail(request.getParameter("email"));
			user.setUserid(request.getParameter("User_Id"));
			user.setPassword(request.getParameter("password"));
			UserDAO userDAOObject = new UserDAO();
			user = userDAOObject.log(user);// calling log function to insert
											// values into database
			PrintWriter out = response.getWriter();
			out.println("<html><body><h1>" + "You must Login now !!!" + "</h1>");
			request.getRequestDispatcher("Login.html").include(request,
					response);
		} catch (SQLException sql) {
			sql.printStackTrace();
			response.sendRedirect("ErrorPage.jsp");
		}
	}
}
