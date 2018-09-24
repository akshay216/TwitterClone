package follow;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import finalproject.UserBean;

/**
 * this servlet class is called whenever the unfollow button on the profile page
 * is pressed it updates and removes the details of the user and the person he
 * wants to unfollow from the database
 * 
 * @author Akshay Sehgal create date: 8th august 2014
 *
 */
public class ForUnFollow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ForUnFollow() {
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

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserBean user = new UserBean(); // creating bean object
		HttpSession session = request.getSession(true); // creating session
		user = (UserBean) session.getAttribute("bean"); /*
														 * getting bean through
														 * session
														 */
		FollowCheckDAO followCheckDAOObject = new FollowCheckDAO();
		user = followCheckDAOObject.unfollow(user); /*
													 * calls the function which
													 * removes the details from
													 * the following table
													 */
		RequestDispatcher rd = request.getRequestDispatcher("ProfileServlet");
		rd.forward(request, response);
	}

}
