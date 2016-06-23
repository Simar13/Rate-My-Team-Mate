package businessLogic;

import java.sql.SQLException;

import bean.Customer;
import dao.CustomerDao;
import dao.DaoFactory;

public class CustomerManager {

	public Customer getCustomerById(String name, String password) {
		CustomerDao dao=DaoFactory.getCustomerDao();
		Customer c=null;
		try {
			c=dao.getCustomersByIdPwd(name, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
		// TODO Auto-generated method stub
	}
	

}
