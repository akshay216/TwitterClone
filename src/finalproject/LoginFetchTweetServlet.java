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
 * This servlet is performing the function of fetching tweets as soon as the
 * user sign in after fetching tweets control is transferred to HomePage jsp
 * 
 * @author Akshay Sehgal create date: 8th august 2014
 */
public class LoginFetchTweetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginFetchTweetServlet() {
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
		HttpSession session = request.getSession(true);
		UserBean user = new UserBean();
		user = (UserBean) session.getAttribute("bean");
		TweetDAO tweetDAOObject = new TweetDAO();
		user = tweetDAOObject.fetchtweets(user); // function used to fetch all
													// tweets and return
													// userbean object
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("HomePage.jsp");
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
	}
}
