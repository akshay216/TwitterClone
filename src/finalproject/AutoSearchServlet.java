package finalproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet is used to search the various persons searched by the user
 * 
 * @author Akshay Sehgal create date: 8th august 2014
 *
 */
/*
 * In this servlet connection is done in the doPost(), although it has to be done in DAO
 */
public class AutoSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AutoSearchServlet() {
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
		response.setContentType("text/html");
		String searchName = request.getParameter("queryString");
		HttpSession session = request.getSession(true);
		try {
			String connectionURL = "jdbc:mysql://localhost:3306/test";
			Connection con;
			Class.forName("com.mysql.jdbc.Driver");
			// Get a Connection to the database
			con = DriverManager.getConnection(connectionURL, "root", "root");
			// Add the data into the database
			String sql = "SELECT user_id,fullName FROM person WHERE fullName LIKE '"
					+ searchName + "%' LIMIT 10";
			Statement st = con.createStatement();
			st.executeQuery(sql);
			ResultSet rs = st.getResultSet();
			out.println("<html><body><ul>");
			while (rs.next()) {
				out.println("<li onclick= 'fill(" + rs.getString("fullName")
						+ ");'><a href=\"ProfileServlet?id="
						+ rs.getString("user_id") + "\">"
						+ rs.getString("fullName") + "</a>");
				session.setAttribute("followId", rs.getString("user_id"));
			}

		}

		catch (Exception e) {
			out.println("Exception is ;" + e);
		}
		out.println("</ul></body></html>");
	}
}
