package businessLogic;

import java.sql.SQLException;

import bean.Customer;
import dao.CustomerDao;
import daoImpl.CustomerDaoImpl;

public class PreferenceManager {
	
	private CustomerDao customerDAO;

	public PreferenceManager() {
		this.customerDAO=new CustomerDaoImpl();
	}

	
	public void updatePassword(String accountId, String pwd) {
		
		customerDAO.updatePassword(accountId,pwd);
		
	}
	public Customer findCustomer(String firstName, String lastName) {
		
		return customerDAO.findCustomer(firstName,lastName);
		
	}
	public Customer getCustomersByUserId(String userId) throws SQLException {
		
		return customerDAO.getCustomersByUserId(userId);
		
	}

	
	

	
}
