package tweet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import finalproject.ConnectionManager;
import finalproject.UserBean;

/**
 * This class is data access class for all the tweet operations
 * 
 * @author Akshay Sehgal create date: 8th august 2014
 *
 */
public class TweetDAO {
	Connection currentCon = null;
	ResultSet rs = null;
	ConnectionManager connectionObj = new ConnectionManager();
	PreparedStatement stmt = null;

	/*
	 * this function is used to enter the tweets entered by the user into the
	 * database
	 */
	public UserBean tweet(UserBean bean) {
		String tweetQuery = "INSERT INTO tweet(user_id,message) VALUES(?,?)";
		try {
			currentCon = connectionObj.getConnection();
			stmt = currentCon.prepareStatement(tweetQuery);
			stmt.setString(1, bean.getUserid());
			stmt.setString(2, bean.getMessage());
			stmt.executeUpdate();
		} catch (SQLException sqlEx) {
			System.out
					.println("Tweet insertion failed: DB Exception has occurred! "
							+ sqlEx);
		} catch (Exception ex) {
			System.out
					.println("Tweet insertion failed: An Exception has occurred! "
							+ ex);
		}

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
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
			}
		}

		return bean;
	}

	/*
	 * this function is used to edit the tweets already entered by the user
	 */
	public UserBean tweetedit(UserBean bean) {
		String tweetQuery = "UPDATE tweet set message=? WHERE tweet_id=?";
		try {
			currentCon = connectionObj.getConnection();
			stmt = currentCon.prepareStatement(tweetQuery);
			stmt.setString(1, bean.getMessage());
			stmt.setString(2, bean.getTweetid());
			stmt.executeUpdate();
		} catch (SQLException sqlEx) {
			System.out.println("Log In failed: DB Exception has occurred! "
					+ sqlEx);
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! "
					+ ex);
		}

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
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
			}
		}

		return bean;
	}

	/*
	 * this function is used to delete the tweets entered by the user
	 */
	public UserBean tweetdelete(UserBean bean) {
		String tweetQuery = "DELETE FROM tweet WHERE tweet_id=?";
		try {
			currentCon = connectionObj.getConnection();
			stmt = currentCon.prepareStatement(tweetQuery);
			stmt.setString(1, bean.getTweetid());
			stmt.executeUpdate();
		} catch (SQLException sqlEx) {
			System.out.println("Tweet delete failed: DB Exception has occurred! "
					+ sqlEx);
		} catch (Exception ex) {
			System.out.println("Tweet delete failed: An Exception has occurred! "
					+ ex);
		}

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
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
			}
		}

		return bean;
	}

	/*
	 * this function is used to get all the tweets from the database which were
	 * entered by the user
	 */
	public UserBean getMyTweet(UserBean bean) {
		ResultSet rs = null;
		String tweetQuery = "Select message,created,tweet_id from tweet where user_id=? order by created DESC";
		List<String> dataList = new ArrayList<String>();
		try {
			currentCon = connectionObj.getConnection();
			stmt = currentCon.prepareStatement(tweetQuery);
			stmt.setString(1, bean.getUserid());
			rs = stmt.executeQuery();

			while (rs.next()) {
				dataList.add(rs.getString("message"));
				dataList.add(rs.getString("created"));
				dataList.add(rs.getString("tweet_id"));
			}
			bean.setDataList(dataList);
		} catch (SQLException sqlEx) {
			System.out.println("Log In failed: DB Exception has occurred! "
					+ sqlEx);
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! "
					+ ex);
		}

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
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
			}

		}
		return bean;

	}

	/*
	 * This function is used to get profile details of the user
	 */
	public UserBean getProfileDetails(UserBean bean) {
		ResultSet rs = null;
		String sql = "Select user_id,fullname,email from person where user_id=?";
		List<String> dataList = new ArrayList<String>();
		try {
			currentCon = connectionObj.getConnection();
			stmt = currentCon.prepareStatement(sql);
			stmt.setString(1, bean.getUserid());
			rs = stmt.executeQuery();

			while (rs.next()) {
				dataList.add(rs.getString("user_id"));
				dataList.add(rs.getString("fullname"));
				dataList.add(rs.getString("email"));
			}
			bean.setDataList1(dataList);
		} catch (SQLException sqlEx) {
			System.out.println("Log In failed: DB Exception has occurred! "
					+ sqlEx);
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! "
					+ ex);
		}

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
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
			}

		}
		return bean;

	}

	/*
	 * This function is used to fetch tweets of the user
	 */
	public UserBean fetchtweets(UserBean bean) {
		ResultSet rs = null;
		List<String> dataList = new ArrayList<String>();
		try {
			Connection currentCon = connectionObj.getConnection();
			String tweetQuery = "select p.fullName, t.message,t.created from person p, tweet t  where (t.user_id=? or t.user_id in (select following_id from following where user_id=?)) and t.user_id=p.user_id order by t.created DESC";
			stmt = currentCon.prepareStatement(tweetQuery);
			stmt.setString(1, bean.getUserid());
			stmt.setString(2, bean.getUserid());
			rs = stmt.executeQuery();
			while (rs.next()) {
				dataList.add(rs.getString(1));
				dataList.add(rs.getString(2));
				dataList.add(rs.getString(3));
			}
			bean.setDataList(dataList);
		}

		catch (SQLException sqlEx) {
			System.out
					.println("Tweet Fetch failed: DB Exception has occurred! "
							+ sqlEx);
		} catch (Exception ex) {
			System.out
					.println("Tweet Fetch failed: An Exception has occurred! "
							+ ex);
		}

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
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
			}

		}
		return bean;

	}

	/*
	 * This function is used to get the tweet count of the user
	 */
	public UserBean myTweetCount(UserBean bean) {
		String tweetQuery = "Select message from tweet where user_id=?";
		ResultSet rs = null;
		int count = 0;
		try {
			currentCon = connectionObj.getConnection();
			stmt = currentCon.prepareStatement(tweetQuery);
			stmt.setString(1, bean.getUserid());
			rs = stmt.executeQuery();

			while (rs.next()) {
				count++;
			}
			bean.setTweetcount(count);
		} catch (SQLException sqlEx) {
			System.out.println("Log In failed: DB Exception has occurred! "
					+ sqlEx);
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! "
					+ ex);
		}

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
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
			}

		}
		return bean;
	}

	/*
	 * This function is used to get the follower count of the user
	 */
	public UserBean myFollowerCount(UserBean bean) {
		String tweetQuery = "Select count(*) from following where following_id=?";
		ResultSet rs = null;
		int followerCount = 0;
		try {
			currentCon = connectionObj.getConnection();
			stmt = currentCon.prepareStatement(tweetQuery);
			stmt.setString(1, bean.getUserid());
			rs = stmt.executeQuery();

			while (rs.next()) {
				followerCount++;
			}
			bean.setFollowcount(followerCount);
		} catch (SQLException sqlEx) {
			System.out.println("Log In failed: DB Exception has occurred! "
					+ sqlEx);
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! "
					+ ex);
		}

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
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
			}

		}
		return bean;
	}

	/*
	 * This function is used to get the tweets of the person whose profile is
	 * visited
	 */
	public UserBean profiletweet(UserBean bean) {
		ResultSet rs = null;
		List<String> dataList = new ArrayList<String>();
		try {
			currentCon = connectionObj.getConnection();
			String tweetQuery = "SELECT * FROM tweet WHERE user_id=?";
			stmt = currentCon.prepareStatement(tweetQuery);
			stmt.setString(1, bean.getFollowId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				dataList.add(rs.getString("message"));
				dataList.add(rs.getString("created"));
			}
			bean.setDataList1(dataList);
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Exception is:" + e);
		}
		return bean;
	}

}
