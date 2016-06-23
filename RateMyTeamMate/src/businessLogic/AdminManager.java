package businessLogic;

import java.util.ArrayList;

import bean.Customer;
import bean.Rating;
import bean.TeamMate;
import dao.CustomerDao;
import daoImpl.CustomerDaoImpl;

public class AdminManager {
	
	private CustomerDao customerDAO;
	

	public AdminManager() {
		this.customerDAO = new CustomerDaoImpl();
	
		
	}
	
	
	public void insertCustomer(Customer c) throws Exception {
    		customerDAO.insertCustomer(c);
    }
	public void insertTeamMate(TeamMate tm) {
		customerDAO.insertTeamMate(tm);
	}
	public void insertRating(Rating rating,String fName,String lName,String uni,String rater) {
		customerDAO.insertRating(rating,fName, lName, uni, rater);
	}
	
	

	
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
	
	
}
