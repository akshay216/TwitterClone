package tweet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import finalproject.UserBean;

/**
 * This servlet is used to delete the tweets entered by the user
 * 
 * @author Akshay Sehgal create date: 8th august 2014
 *
 */
public class DeleteTweetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteTweetServlet() {
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
		HttpSession ses = request.getSession(true);
		String tid = (String) ses.getAttribute("tweetid");
		UserBean user = new UserBean();
		user.setTweetid(tid);
		TweetDAO tweetDAOObject = new TweetDAO();
		user = tweetDAOObject.tweetdelete(user);/*
												 * function used to delete the
												 * tweet from the database
												 */
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("MyProfileServlet");
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}
	}

}
