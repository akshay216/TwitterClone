package finalproject;

import java.util.ArrayList;
import java.util.List;

/**
 * this is userbean class containing all the reusable components of the project.
 * there is only one bean for the entire project
 * 
 * @author Akshay Sehgal create date: 8th august 2014
 */
public class UserBean {
	// required properties for bean
	// reusable component , part of model
	private String userid;
	private String password;
	private String email;
	private String fullName;
	public boolean Active;
	private String followId;
	private String unfollowId;
	private int followcount;
	private boolean match;
	private int isfollowing;
	private String message;
	private String tweetid;
	private List<String> dataList = new ArrayList<String>();
	private List<String> dataList1 = new ArrayList<String>();
	private int tweetcount;
	private boolean isdelete;

	public String getFollowId() {
		return followId;
	}

	public void setFollowId(String followId) {
		this.followId = followId;
	}

	public String getUnfollowId() {
		return unfollowId;
	}

	public void setUnfollowId(String unfollowId) {
		this.unfollowId = unfollowId;
	}

	public int getFollowcount() {
		return followcount;
	}

	public void setFollowcount(int followcount) {
		this.followcount = followcount;
	}

	public boolean isMatch() {
		return match;
	}

	public void setMatch(boolean match) {
		this.match = match;
	}

	public int getIsfollowing() {
		return isfollowing;
	}

	public void setIsfollowing(int isfollowing) {
		this.isfollowing = isfollowing;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public boolean isActive() {
		return Active;
	}

	public void setActive(boolean Active) {
		this.Active = Active;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTweetid() {
		return tweetid;
	}

	public void setTweetid(String tweetid) {
		this.tweetid = tweetid;
	}

	public List<String> getDataList() {
		return dataList;
	}

	public void setDataList(List<String> dataList) {
		this.dataList = dataList;
	}

	public List<String> getDataList1() {
		return dataList1;
	}

	public void setDataList1(List<String> dataList1) {
		this.dataList1 = dataList1;
	}

	public int getTweetcount() {
		return tweetcount;
	}

	public void setTweetcount(int tweetcount) {
		this.tweetcount = tweetcount;
	}

	public boolean isIsdelete() {
		return isdelete;
	}

	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}

}