package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.TeamMate;
import businessLogic.AdminManager;
import daoImpl.CustomerDaoImpl;


@WebServlet("/AddTeamMate")
public class AddTeamMate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTeamMate() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "AddGroupMate.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session=request.getSession(true);
		System.out.println("Inside addrating servlet");
		String path = "";
		AdminManager mgr=new AdminManager();
		CustomerDaoImpl cdao = new CustomerDaoImpl();
		String FirstName= request.getParameter("FirstName");
		String LastName=request.getParameter("LastName");	
		
		String uniName=request.getParameter("UniName");
		String department=request.getParameter("Department");
		String course=request.getParameter("Course");
		
		//String accountId=(String)request.getParameter("accountId");
		if(FirstName != null  && LastName != null  && course != null   &&	department != null && uniName != null){
			TeamMate teamMate = cdao.findStudent(FirstName, LastName, uniName);
			if("".equalsIgnoreCase(teamMate.getFirstName()) || teamMate.getFirstName()==null ){
			TeamMate tm = new TeamMate(0,FirstName,LastName,uniName,course,department);				
				mgr.insertTeamMate(tm);
				path = "AddGroupMate.jsp";
				request.setAttribute("error","Student added successfully !!. You can add more !");
			}
			else{
				request.setAttribute("error","Student already exists in the database !");
				path = "AddGroupMate.jsp";
			}
				
			//	request.setAttribute("error","Customer created successfully !");
				
				
			
		}
		else{
			request.setAttribute("error","Please fill all given fields !");
			path = "AddGroupMate.jsp"; 
			}

			
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
	}

	
	

}
