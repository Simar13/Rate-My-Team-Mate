package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.Customer;
import bean.Rating;
import bean.TeamMate;

public interface CustomerDao {
	
	public ArrayList<Rating> getRatings(String firstName, String lastName, String university);
	public void insertCustomer(Customer c) throws Exception;
	public void insertTeamMate(TeamMate tm);
	public void insertRating(Rating rating,String fName,String lName,String uni,String rater);
	public Customer getCustomersByIdPwd(String userId, String pwd)
			throws SQLException;
	public Customer getCustomersByUserId(String userId)
			throws SQLException;
	public Customer findCustomer(String firstName, String lastName);
	

	public void updatePassword(String accountId, String pwd);
	

}
