package account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import finalproject.UserBean;
import finalproject.UserDAO;

/**
 * This servlet is invoked when the user wants to edit his profile details
 * 
 * @author Akshay Sehgal create date: 8th august 2014
 *
 */
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditProfileServlet() {
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
		response.setContentType("text/html");// reads text and html
		HttpSession session = request.getSession(true);
		UserBean user = new UserBean();
		user = (UserBean) session.getAttribute("bean");
		user.setFullName(request.getParameter("fname"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		UserDAO userDAOobject = new UserDAO();
		userDAOobject.editProfile(user);// call the function to edit the profile
										// details of the user
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>" + "You can now login with new credentials !!!"
				+ "</h1>");
		request.getRequestDispatcher("Login.html").include(request, response);

	}

}
