package finalproject;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet is called through AutoSearchServlet when the name of the user
 * whose profile you want to see is pressed in suggestion list
 * 
 * @author Akshay Sehgal create date: 8th august 2014 after getting profile
 *         details control is transferred to FollowCheckServlet where it checks
 *         whether the user is already following the person or not
 */
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String page = "FollowCheck";

	public ProfileServlet() {
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
		String followId = (String) session.getAttribute("followId");
		UserBean user = new UserBean();
		user = (UserBean) session.getAttribute("bean");
		user.setFollowId(followId);
		UserDAO userDAOObject = new UserDAO(); // creates DAO class object
		user = userDAOObject.profile(user); // calls the function used to get
											// the profile of the followed user
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		if (dispatcher != null) {
			dispatcher.forward(request, response);
		}

	}
}
