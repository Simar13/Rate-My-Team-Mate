package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import bean.Customer;
import businessLogic.CustomerManager;
import dao.CustomerDao;
import daoImpl.CustomerDaoImpl;


@WebServlet({"/Login"})
public class LoginServlet extends HttpServlet {
	 
	private static final long serialVersionUID = 1L;
       
HttpSession session;
    
    /**
     * @see HttpServlet#HttpServlet()
     */


	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		session=request.getSession(true);
		String login =(String) session.getAttribute("json");
		String logout=request.getParameter("logout");
		String action=request.getParameter("action");
		String path = "MainPage.jsp";
		if(logout!=null && logout.equalsIgnoreCase("true")){
			
			session.invalidate();
			System.out.println("IN logout"+ session );
			response.sendRedirect("MainPage.jsp");
		}else{
			
			if(login==null){
				path="SignIn.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
				
			}
			else{
				if(action.equalsIgnoreCase("add")){
				path="AddGroupMate.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
				}
				else{
					path="SearchToRate.jsp";
					RequestDispatcher rd = request.getRequestDispatcher(path);
					rd.forward(request, response);
					
				}
			}
			/*String path= "SignUp.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);*/
		}
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		session=request.getSession(true);
		// TODO Auto-generated method stub
		String path = "";
		if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
		    // Handle ajax response (e.g. return JSON data object).
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	        String json = "";
	        if(br != null){
	            json = br.readLine();
	            System.out.println("json obj: " +json);
	         //   JSONArray jsonArray = new JSONArray(json);
	            try {
					JSONObject obj = new JSONObject(json);
				if(obj.has("wc")){
					 System.out.println("json obj: " +obj.getString("Za")+obj.getString("Na"));
					  session.setAttribute("name",obj.getString("Za")+obj.getString("Na"));
					 
				}
				else{
			     System.out.println("json obj: " +obj.getString("first_name")+obj.getString("last_name"));
			        session.setAttribute("name",obj.getString("first_name")+obj.getString("last_name"));
				}
	   
	            session.setAttribute("json",json);
	    
	            }  catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	      
		} else {
		    // Handle normal response (e.g. forward and/or set message as attribute).
		
		
		String name = request.getParameter("UserName");
		String password = request.getParameter("Password");
		CustomerManager mgr = new CustomerManager();
		
		Customer c = new Customer();
		// System.out.println(c);
		
		c = mgr.getCustomerById(name, password);
		System.out.println("*************2222*************");
		System.out.println(c);
		
		try {
			if (c.getUserid() != null && c.getPassword() != null) {
				path = "MainPage.jsp";
			
				/*Store in session*/
				CustomerDao customerDao=new CustomerDaoImpl();
				Customer customer=customerDao.getCustomersByIdPwd(c.getUserid(), c.getPassword());
				session.setAttribute("json",customer.getNric());
				session.setAttribute("name",customer.getFirstName()+customer.getLastName());
			}
			else {
				path = "MainPage.jsp";
				request.setAttribute("message", "Invalid user name and password");
				System.out.println(" message"+request.getAttribute("message"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	//	response.sendRedirect(arg0);
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	//	response.sendRedirect("MainPage.jsp");
		}
		
	}
		
}