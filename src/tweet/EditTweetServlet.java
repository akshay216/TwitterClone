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
 * This servlet is used to edit the tweets entered by the user
 * 
 * @author Akshay Sehgal create date: 8th august 2014
 *
 */
public class EditTweetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditTweetServlet() {
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
		String msg = request.getParameter("EditTweetArea");
		UserBean user = new UserBean();
		user.setTweetid(tid);
		user.setMessage(msg);
		TweetDAO tweetDAOObject = new TweetDAO();
		user = tweetDAOObject.tweetedit(user);// function used to edit tweet
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("MyProfileServlet");
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}

	}

}
