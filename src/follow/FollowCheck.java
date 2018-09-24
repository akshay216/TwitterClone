package follow;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tweet.TweetDAO;
import finalproject.UserBean;

/**
 * this servlet is used to check whether the user is following the person or not
 * and returns the tweet of the person and the follower count of the person
 * which is displayed on the profile page of the person
 * 
 * @author Akshay Sehgal create date: 8th august 2014
 */
public class FollowCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FollowCheck() {
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

		HttpSession session = request.getSession(true);
		UserBean user = new UserBean();
		user = (UserBean) session.getAttribute("bean");
		try {
			FollowCheckDAO followCheckDAOObject = new FollowCheckDAO();
			TweetDAO tweetDAOObject = new TweetDAO();
			user = followCheckDAOObject.check(user); // used to whether user is
			// following the person or not
			user = followCheckDAOObject.followercount(user); // returns the
																// follower
			// count of the person
			// whose profile you
			// want to see
			user = tweetDAOObject.profiletweet(user); // returns the tweet of
														// the
			// person whose profile you want
			// to see
			// control transferred to profile page.jsp
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("ProfilePage.jsp");
			if (dispatcher != null) {
				dispatcher.forward(request, response);
			}

		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
