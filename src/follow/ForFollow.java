package follow;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import finalproject.UserBean;

/**
 * this servlet class is called whenever the follow button on the profile page
 * is pressed it enters the details of the user and the person he wants to
 * follow in the database
 * 
 * @author Akshay Sehgal create date: 8th august 2014
 */
public class ForFollow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection Con1 = null;

	public ForFollow() {
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
		doPost(request, response); // transfers request and response to doPost()
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
		HttpSession session = request.getSession(true); // setting the session
		UserBean user = new UserBean();
		user = (UserBean) session.getAttribute("bean"); // getting userbean
		FollowCheckDAO followCheckDAOObject = new FollowCheckDAO(); // through
																	// session
		user = followCheckDAOObject.follow(user); // calls the follow() from
		// FollowCheckDAO. This functions
		// set the isfollowing parameter of
		// the bean and update the data in
		// following table
		RequestDispatcher rd = request.getRequestDispatcher("ProfileServlet");
		rd.forward(request, response);
	}

}
