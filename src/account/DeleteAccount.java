package account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import finalproject.UserBean;
import finalproject.UserDAO;

/**
 * This servlet is called when the user wants to delete his account by clicking
 * delete account link
 * 
 * @author Akshay Sehgal create date: 8th august 2014
 *
 */
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteAccount() {
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
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		UserBean user = new UserBean();
		user = (UserBean) session.getAttribute("bean");
		UserDAO userDAOObject = new UserDAO();
		user = userDAOObject.deleteAccount(user);// calling deleteAccount() to
													// delete the details of the
													// user who wants to delete
													// his account
		if (user.isIsdelete()) {
			out.println("<html><body><center><h2>Your Account has been deleted!!<h2><h3>Sign Up to use Twitter Clone<h3></center></body></html>");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("Login.html");
			if (dispatcher != null) {
				dispatcher.include(request, response);// including Login.html
														// page
			}
		}
	}

}
