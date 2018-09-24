package finalproject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class is taking parameters from Login.html and setting our userbean in
 * session
 * 
 * @author Akshay Sehgal create date: 8th august 2014 after verifying the user
 *         id through UserDAO login function It is transferring the control to
 *         LoginfetchServlet during failure control is transferred to
 *         ErrorLogin.html page
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 2562294252731783855L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		doPost(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		try {
			UserBean user = new UserBean();
			user.setUserid(request.getParameter("userId"));
			user.setPassword(request.getParameter("password"));
			UserDAO userDAOObject = new UserDAO();
			user = userDAOObject.login(user);
			// checking if the user is active i.e logged in or not
			if (user.isActive()) {
				HttpSession session = request.getSession(true);
				session.setAttribute("bean", user);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("LoginFetchTweetServlet");
				if (dispatcher != null) {
					dispatcher.forward(request, response);
				}
			} else
				response.sendRedirect("ErrorLogin.html"); // error page
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}

}