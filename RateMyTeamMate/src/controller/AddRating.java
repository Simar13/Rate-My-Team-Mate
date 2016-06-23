package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.SwearWords;
import bean.Rating;
import bean.TeamMate;
import businessLogic.AdminManager;
import daoImpl.CustomerDaoImpl;


@WebServlet("/AddRating")
public class AddRating extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String[] swearWordList = SwearWords.swearWords;
	private HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRating() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session=request.getSession(true);
		String login =(String) session.getAttribute("json");
		String path = "RateGroupMate.jsp";
		CustomerDaoImpl cdao = new CustomerDaoImpl();
		String result = request.getParameter("search");
		String name = request.getParameter("name");
		String uni = request.getParameter("university");
//		String lastName = request.getParameter("lastname");
//		String university = request.getParameter("uni");
		if(result!=null && !"".equalsIgnoreCase(result)){
				String [] studentArray = result.split("---");
				String [] nameArray = studentArray[0].split(" ");
				if(studentArray.length==2 && nameArray.length==2){
					TeamMate teamMate = cdao.findStudent(nameArray[0], nameArray[1], studentArray[1]);
							if(teamMate.getFirstName()!=null){
							request.setAttribute("name",teamMate.getFirstName()+" "+teamMate.getLastName());
							request.setAttribute("university",teamMate.getUniversity());
							request.setAttribute("course",teamMate.getCourse());
							request.setAttribute("department",teamMate.getDepartment());
							}
							else{
								request.setAttribute("error","Searched person does not found in database. Please add the person then rate.");
								 path = "SearchToRate.jsp";
							}
				}
				else{
					request.setAttribute("error","Searched person does not found in database. Please add the person then rate.");
					 path = "SearchToRate.jsp";
				}
		}
		else if(name!=null && uni!=null){
			if(login==null){
				path="SignIn.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
				
			}
			else{
				String [] nameArray = name.split(" ");
				TeamMate teamMate = cdao.findStudent(nameArray[0], nameArray[1], uni);
						if(teamMate.getFirstName()!=null){
						request.setAttribute("name",teamMate.getFirstName()+" "+teamMate.getLastName());
						request.setAttribute("university",teamMate.getUniversity());
						request.setAttribute("course",teamMate.getCourse());
						request.setAttribute("department",teamMate.getDepartment());
						}
						else{
							request.setAttribute("error","Searched person does not found in database. Please add the person then rate.");
							 path = "SearchToRate.jsp";
						}
				}
			
			
		}
		else{
			request.setAttribute("error","Searched person does not found in database. Please add the person then rate.");
			path = "SearchToRate.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session=request.getSession(true);
		System.out.println("Inside AddRatging servlet");
		String path = "";
		String rater = (String) session.getAttribute("name");
		CustomerDaoImpl cdao = new CustomerDaoImpl();
		AdminManager mgr=new AdminManager();
		String name = request.getParameter("name");
		String [] nameArray = name.split(" ");
		
		String university = request.getParameter("university");
		String starT=(String) request.getParameter("starT");
		String starL=request.getParameter("starL");			
		String star1=request.getParameter("star1");
		String star=request.getParameter("star");
		String course=request.getParameter("cc");
		String [] tag=request.getParameterValues("checkbox");
		String comment=request.getParameter("comment");
		// tags 
		ArrayList<String> tags= new ArrayList<String>();
		for (int i = 0; i < tag.length; i++) {
		   tags.add(tag[i]);
		}
		
		// comment array
		String [] tagArray = comment.split(" ");
		ArrayList<String> commentList= new ArrayList<String>();
		for (int i = 0; i < tagArray.length; i++) {
			commentList.add(tagArray[i]);
		}
		StringBuffer sb = new StringBuffer();
		ArrayList<String> newComment= filter(commentList);
		Iterator<String> itr = newComment.iterator();
		int i=0;
			while (itr.hasNext()) {
				if(i==0){
				sb.append(itr.next());
				i++;
				}
				else{
					sb.append(" ");
					sb.append(itr.next());
				}
				System.out.println(sb);
			}
		double averageTmp = ((Double.parseDouble(star)+Double.parseDouble(star1)+Double.parseDouble(starL)+Double.parseDouble(starT))/4);
		String average = String.valueOf(averageTmp);
			if(starT != null  && starL != null  && course != null   &&	star1 != null && star != null && sb != null && tag != null){
				
				TeamMate teamMate = cdao.findRater(nameArray[0], nameArray[1], university, rater);
				if("".equalsIgnoreCase(teamMate.getCourse()) || teamMate.getCourse()==null ){
					Rating rating = new Rating(0,course,star,star1,starL,starT,tags,sb.toString(),average);
					mgr.insertRating(rating,nameArray[0], nameArray[1], university, rater);
								
					path = "MainPage.jsp";
					request.setAttribute("error","Student has been rated successfully !");
				}
				else{
					path = "MainPage.jsp";
					request.setAttribute("error","You has already rated the student !");
				}
			}
		
			else{
				request.setAttribute("error","Please fill all given fields !");
				path = "RateGroupMate.jsp"; 
				}

			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
	}
	
	
	
	
	
	 public static ArrayList<String> filter(ArrayList<String> commentList) {
		 ArrayList<String> newList = new ArrayList<String>();
		
	        for (String comment : commentList) {
	            String stars;
	            newList.add(comment);	            
	            
	            for (String swearWord : swearWordList) {
	            Pattern pat = Pattern.compile(swearWord, Pattern.CASE_INSENSITIVE);
	            Matcher mat = pat.matcher(comment);
	          
	            while (mat.find()) {
	            	 if( (comment.length()== swearWord.length()) ||  ((comment.length()-swearWord.length())==1)){
	                char[] replace = new char[mat.end() - mat.start()];
	                for (int i = 0; i < mat.end() - mat.start(); i++) {
	                	replace[i] = '*';
	                  }
	                stars = new String(replace);
	            //    comment = mat.replaceFirst(stars);
	                newList.remove(comment);
	                newList.add(stars);
	              }	           
	            }
	           }
	        }
	      return newList;
	   }

	
	

}
