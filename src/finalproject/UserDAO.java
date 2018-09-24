package finalproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is UserDAO class all the functions in this class are used to set
 * connection with data base read,write or update values
 * 
 * @author Akshay Sehgal create date: 8th august 2014
 */
public class UserDAO {
	Connection cCon = null;
	ResultSet rs = null;
	ConnectionManager connectionObj = new ConnectionManager();
	PreparedStatement stmt = null;

	/*
	 * this function is used to validate the userid and password entered by the
	 * user at the time of log in
	 */
	public UserBean login(UserBean bean) {
		try {
			// create connection using ConnectionManager class
			cCon = connectionObj.getConnection();
			String sql = "select * from person where User_Id=? AND  encpassword=sha1(?)";
			stmt = cCon.prepareStatement(sql);
			stmt.setString(1, bean.getUserid());
			stmt.setString(2, bean.getPassword());
			rs = stmt.executeQuery();
			boolean more = rs.next();
			if (!more) {
				System.out
						.println("Sorry, you are not a registered user! Please sign up first");
				bean.setActive(false);
			}// if user exists set the isValid variable to true
			else if (more) {
				String fullname = rs.getString("fullName");
				bean.setFullName(fullname);
				bean.setActive(true);
			}
		}

		catch (SQLException sqlEx) {
			System.out.println("DB Exception has occurred!");
			sqlEx.printStackTrace();
		} catch (Exception ex) {
			System.out.println("An Exception has occurred!");
			ex.printStackTrace();
		}
		// Free the database resources
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {

				}
				stmt = null;
			}
			if (cCon != null) {
				try {
					cCon.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return bean;
	}

	/*
	 * this function returns all the details of the person whose profile we want
	 * to see in bean form
	 */

	public UserBean profile(UserBean bean) {
		List<String> dataList = new ArrayList<String>();
		ResultSet rs = null;
		try {
			// set up connection
			cCon = connectionObj.getConnection();

			String sql = "SELECT * FROM person WHERE user_id=?";
			stmt = cCon.prepareStatement(sql);
			stmt.setString(1, bean.getFollowId());
			rs = stmt.executeQuery();
			// to add all the details in the datalist
			while (rs.next()) {
				dataList.add(rs.getString("user_id"));
				dataList.add(rs.getString("fullName"));
				dataList.add(rs.getString("email"));
			}
			bean.setDataList(dataList);
		} catch (SQLException sqlEx) {
			System.out.println("DB Exception has occurred!");
			sqlEx.printStackTrace();
		} catch (Exception ex) {
			System.out.println("An Exception has occurred!");
			ex.printStackTrace();
		}
		// Free the database resources
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {

				}
				stmt = null;
			}
			if (cCon != null) {
				try {
					cCon.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return bean;
	}

	/*
	 * this function deletes the details of the user from all the tables and
	 * hence the account of the person is deleted
	 */
	public UserBean deleteAccount(UserBean bean) {
		try {
			cCon = connectionObj.getConnection();
			String userId = bean.getUserid();
			String deleteQuery1 = "delete from tweet where user_id=?";
			stmt = cCon.prepareStatement(deleteQuery1);
			stmt.setString(1, userId);
			stmt.executeUpdate();
			String deleteQuery2 = "delete from following where user_id=?";
			stmt = cCon.prepareStatement(deleteQuery2);
			stmt.setString(1, userId);
			stmt.executeUpdate();
			String deleteQuery3 = "delete from following where following_id=?";
			stmt = cCon.prepareStatement(deleteQuery3);
			stmt.setString(1, userId);
			stmt.executeUpdate();
			String deleteQuery4 = "delete from person where user_id=?";
			stmt = cCon.prepareStatement(deleteQuery4);
			stmt.setString(1, userId);
			stmt.executeUpdate();

			bean.setIsdelete(true);
		}

		catch (SQLException sqlEx) {
			System.out.println("DB Exception has occurred!");
			sqlEx.printStackTrace();
		} catch (Exception ex) {
			System.out.println("An Exception has occurred!");
			ex.printStackTrace();
		}
		// free the resources of the database
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {

				}
				stmt = null;
			}
			if (cCon != null) {
				try {
					cCon.close();
				} catch (Exception e) {
				}
			}
		}

		return bean; // return userbean object
	}

	/*
	 * This function is used to insert the person details in the table hence
	 * create the account of the person when the person sign up
	 */
	public UserBean log(UserBean bean) throws SQLException {
		// create connection
		cCon = connectionObj.getConnection();
		try {
			String sql = "insert into person (user_id,encpassword,fullName,email,active) values(?,sha1(?),?,?,?)";
			stmt = cCon.prepareStatement(sql);
			stmt.setString(1, bean.getUserid());
			stmt.setString(2, bean.getPassword());
			stmt.setString(3, bean.getFullName());
			stmt.setString(4, bean.getEmail());
			stmt.setInt(5, 1);
			stmt.executeUpdate();
		} finally {
			if (stmt != null) {
			}
			try {
				stmt.close();
			} catch (Exception e) {

			}
			stmt = null;
		}
		if (cCon != null) {
			try {
				cCon.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return bean;
	}

	/*
	 * this function edit the details of the user when he clicks on the edit
	 * profile link on the home page
	 */
	public UserBean editProfile(UserBean bean) {
		String query = "UPDATE person set encpassword=sha1(?), fullName=?, email=? WHERE user_id=?";
		try {
			// creating connection
			cCon = connectionObj.getConnection();
			stmt = cCon.prepareStatement(query);
			stmt.setString(1, bean.getPassword());
			stmt.setString(2, bean.getFullName());
			stmt.setString(3, bean.getEmail());
			stmt.setString(4, bean.getUserid());
			stmt.executeUpdate();
		} catch (SQLException sqlEx) {
			System.out.println("DB Exception has occurred!");
			sqlEx.printStackTrace();
		} catch (Exception ex) {
			System.out.println("An Exception has occurred!");
			ex.printStackTrace();
		}
		// free the resources from the database
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {

				}
				stmt = null;
			}
			if (cCon != null) {
				try {
					cCon.close();
				} catch (Exception e) {
				}
			}
		}

		return bean; // return userbean object
	}

}
