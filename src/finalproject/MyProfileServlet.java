package finalproject;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tweet.TweetDAO;

/**
 * This servlet is invoked when the user on the Home page decides to check his
 * own profile to edit of delete tweets.
 * 
 * @author Akshay Sehgal create date: 8th august 2014
 *
 */
public class MyProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyProfileServlet() {
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
		HttpSession session = request.getSession(true); // creates session
		UserBean user = new UserBean();// creates bean object
		user = (UserBean) session.getAttribute("bean");// getting bean through
														// session
		TweetDAO tweetDAOObject = new TweetDAO();
		user = tweetDAOObject.getMyTweet(user);// returns the user tweets
												// through bean
												// list
		user = tweetDAOObject.getProfileDetails(user);// returns the user
														// profile
		// details through bean list
		user = tweetDAOObject.myTweetCount(user);// counts the number of tweets
		user = tweetDAOObject.myFollowerCount(user);// counts the number of
													// followers

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("MyProfilePage.jsp");
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}

	}

}
