package follow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import finalproject.ConnectionManager;
import finalproject.UserBean;

/**
 * This is java class DAO is used to access the data required for the purpose of
 * follow or unfollow with the use of various functions
 * 
 * @author Akshay Sehgal create date: 8th august 2014
 *
 */
public class FollowCheckDAO {
	Connection mCon = null;
	ConnectionManager connectionObj = new ConnectionManager();
	PreparedStatement stmt = null;
	ResultSet rs = null;

	/*
	 * This function is used to check whether the user is already following the
	 * person or not
	 */
	public UserBean check(UserBean bean) {
		mCon = connectionObj.getConnection();
		try {

			String sql = "SELECT * FROM following WHERE user_id=? and following_id=?";
			stmt = mCon.prepareStatement(sql);
			stmt.setString(1, bean.getUserid());
			stmt.setString(2, bean.getFollowId());
			
			rs = stmt.executeQuery();
			if (rs.next()) {
				bean.setIsfollowing(1);
			} else {
				bean.setIsfollowing(0);
			}

		} catch (SQLException s) {
			s.printStackTrace();
			System.out.println("error is: " + s);
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		} finally {
			try {
				rs.close();
				stmt.close();
				mCon.close();
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}

		return bean;

	}

	/*
	 * This function is used to return the follower count of the user
	 */
	public UserBean followercount(UserBean bean) {
		mCon = connectionObj.getConnection();
		int count = 0;
		try {
			String sql = "SELECT * FROM following WHERE following_id=?";
			stmt = mCon.prepareStatement(sql);
			stmt.setString(1, bean.getFollowId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				count++;
			}

			bean.setFollowcount(count);

		} catch (SQLException s) {
			s.printStackTrace();
			System.out.println("error is: " + s);
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		} finally {
			try {
				rs.close();
				stmt.close();
				mCon.close();
			} catch (SQLException s) {
				s.printStackTrace();
			}
		}

		return bean;

	}

	/*
	 * This function make the changes in the database making the user following
	 * the person he intend to when he clicks on the follow button
	 */
	public UserBean follow(UserBean bean) {
		int count = 0;
		try {

			mCon = connectionObj.getConnection();
			String sql = "INSERT INTO following values(?,?)";
			stmt = mCon.prepareStatement(sql);
			stmt.setString(1, bean.getUserid());
			stmt.setString(2, bean.getFollowId());
			if (stmt.executeUpdate() == 1) {
				count = 1;
			}
			bean.setIsfollowing(count);

		} catch (SQLException s) {
			// s.printStackTrace();
			System.out.println(s);
			System.out.println("cant follow");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				mCon.close();

			} catch (SQLException s) {
				s.printStackTrace();

			}
		}

		return bean;
	}

	/*
	 * This function make the changes in the database making the user unfollow
	 * the person he intend to when he clicks on the unfollow button
	 */
	public UserBean unfollow(UserBean bean) {

		try {
			mCon = connectionObj.getConnection();
			String sql = "Delete from following where user_id=? and following_id=?";
			stmt = mCon.prepareStatement(sql);
			stmt.setString(1, bean.getUserid());
			stmt.setString(2, bean.getFollowId());
			stmt.executeUpdate();
			int count = bean.getIsfollowing();
			count--;
			bean.setIsfollowing(count);
		} catch (SQLException s) {
			bean.setMatch(false);
			s.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				mCon.close();

			} catch (SQLException s) {
				s.printStackTrace();

			}
		}
		return bean;
	}

}
