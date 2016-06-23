

package dao;

import daoImpl.CustomerDaoImpl;

public class DaoFactory {
	

	
	public static CustomerDao getCustomerDao(){
		return new CustomerDaoImpl();
	}

	

}
