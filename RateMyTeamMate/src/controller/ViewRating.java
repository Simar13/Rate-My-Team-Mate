package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Rating;
import bean.TeamMate;
import daoImpl.CustomerDaoImpl;


@WebServlet("/ViewRating")
public class ViewRating extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	session=request.getSession(true);
    	ArrayList<Rating> tags= new ArrayList<Rating>();
    	String path = "ViewRating.jsp";
		String result = request.getParameter("search");
//		String lastName = request.getParameter("lastname");
//		String university = request.getParameter("uni");
		
		 double helpfulness=0;
		 double knowledgeable=0;
		 double leadership=0;
		 double teamParticipation=0;
		 double avg=0;
		 int i=0;
		CustomerDaoImpl customerDaoImpl =new CustomerDaoImpl();
		System.out.println("In ViewRating Servlet--------- ");
		
		if(result!=null && !"".equalsIgnoreCase(result)){
			String [] studentArray = result.split("---");
			String [] nameArray = studentArray[0].split(" ");
				if(studentArray.length==2 && nameArray.length==2){
					TeamMate teamMate = customerDaoImpl.findStudent(nameArray[0], nameArray[1], studentArray[1]);
					if(teamMate.getFirstName()!=null){
					request.setAttribute("teamMate",teamMate);
					tags = customerDaoImpl.getRatings(nameArray[0],nameArray[1],studentArray[1]);
					for(Rating obj : tags){
						i++;
						helpfulness = helpfulness + Double.parseDouble(obj.getHelpfulness());
						knowledgeable= knowledgeable +Double.parseDouble(obj.getKnowledgeable());
						leadership= leadership+Double.parseDouble(obj.getLeadership());
						teamParticipation=teamParticipation+Double.parseDouble(obj.getTeamParticipation());
						avg= avg+Double.parseDouble(obj.getAverage());
						}
						System.out.println("In cheque Servlet else--------- "+(helpfulness) );
						System.out.println("In cheque Servlet else--------- "+(avg/i) );
						request.setAttribute("helpfulness", (helpfulness/i));
						request.setAttribute("knowledgeable", (knowledgeable/i));
						request.setAttribute("leadership", (leadership/i));
						request.setAttribute("teamParticipation", (teamParticipation/i));
						request.setAttribute("avg", (avg/i));
						request.setAttribute("list", tags);
						request.setAttribute("name",teamMate.getFirstName()+" "+teamMate.getLastName());
						request.setAttribute("university",teamMate.getUniversity());
						request.setAttribute("course",teamMate.getCourse());
				}
				else{
					request.setAttribute("error","Searched person does not found in database. Please add the person then rate.");
					path = "MainPage.jsp";
				}
			}
			else{
				request.setAttribute("error","Searched person does not found in database. Please add the person then rate.");
				path = "MainPage.jsp";
			}
		}
		else{
			request.setAttribute("error","Searched person does not found in database. Please add the person then rate.");
			path = "MainPage.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
   }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
