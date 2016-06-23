package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Customer;
import businessLogic.AdminManager;
import businessLogic.PreferenceManager;


@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "Signup.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session=request.getSession(true);
		System.out.println("Inside createUpdate servlet");
		String path = "";
		AdminManager mgr=new AdminManager();	
		PreferenceManager pm = new PreferenceManager();
		String FirstName=(String) request.getParameter("FirstName");
		String LastName=request.getParameter("LastName");		
		String UserName=request.getParameter("UserName");
		String Password=request.getParameter("Password");		;
		String UniName=request.getParameter("UniName");
		String Department=request.getParameter("Department");
		String que=request.getParameter("question");
		String ans=request.getParameter("answer");
		String email=request.getParameter("Email");
		String PhoneNumber=request.getParameter("PhoneNumber");
		//String accountId=(String)request.getParameter("accountId");
		if(FirstName != null  && LastName != null  && Password != null  && UserName != null && email != null &&	
				Department != null && UniName != null){
			
			Customer c=new Customer("c",FirstName,LastName ,Department,UniName,UserName,Password, 
					"you favorite sport","hocky",email,Long.parseLong(PhoneNumber));			
			System.out.println("customer ::"+c);
			Customer customer= pm.findCustomer(UserName, Password);
			System.out.println("customer ::"+customer);
			if(customer.getFirstName()==null)
				try {
					mgr.insertCustomer(c);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else
				request.setAttribute("error"," User already exists !");
				
			//	request.setAttribute("error","Customer created successfully !");
				path = "Signup.jsp";
					
			
		}
		else{
			request.setAttribute("error","Please fill all given fields !");
			path = "Signup.jsp";
			}

			
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
	}

	
	

}
