package tweet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import finalproject.ConnectionManager;
import finalproject.UserBean;

/**
 * This servlet is used to update the tweets on the users home page when he
 * enters any tweet.
 * 
 * @author Akshay Sehgal create date: 8th august 2014
 *
 */
public class TweetServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TweetServ() {
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
		HttpSession session = request.getSession();
		UserBean user = new UserBean();
		user = (UserBean) session.getAttribute("bean");
		String message = request.getParameter("message");
		user.setMessage(message);
		TweetDAO tweetDAOobject = new TweetDAO();
		user = tweetDAOobject.tweet(user);
		response.setContentType("text/html");

		try {
			ConnectionManager connectobj = new ConnectionManager();
			Connection connection = connectobj.getConnection();
			Statement statement = connection.createStatement();

			String user_id = user.getUserid();
			ResultSet resultset = statement
					.executeQuery("select p.fullName ,t.tweet_id, t.message,t.created from person p, tweet t  where (t.user_id=\""
							+ user_id
							+ "\" or t.user_id in (select following_id from following where user_id=\""
							+ user_id
							+ "\")) and t.user_id=p.user_id order by t.created DESC");
			if (!resultset.next()) {
				out.println("Sorry, could not find tweets... ");
			} else {
				out.println("<html><body><TABLE align=center width=80% >"
						+ "<TH width=25%>FROM</TH>"
						+ "<TH width=50%>TWEET</TH><TH width=25%>TIME</TH></TR>");
				do {
					out.println("<TR><TD width=25%>"
							+ resultset.getString("fullName")
							+ "</TD><TD width=50%>"
							+ resultset.getString("message")
							+ "</TD><TD width=25%>"
							+ resultset.getString("created") + "</TD></TR>");
				} while (resultset.next());
				out.println("</table></body></html>");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
