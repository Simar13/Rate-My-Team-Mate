package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import daoImpl.CustomerDaoImpl;


@WebServlet("/SearchStudent")
public class SearchStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchStudent() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session=request.getSession(true);
		String path = "RateGroupMate.jsp";
		CustomerDaoImpl cdao = new CustomerDaoImpl();
		String query=request.getParameter("query");
		/*BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
    //        session.setAttribute("json",json);
        }*/
//		String [] tagArray = json.split(" ");
        
        ArrayList<String> list = cdao.findStudentAjax(query, query);
        Gson gson =new Gson();
        String searchList = gson.toJson(list);
        System.out.println("Inside sear student"+list);
        response.getWriter().write(searchList);
		
//		RequestDispatcher rd = request.getRequestDispatcher(path);
//		rd.forward(request, response);
//			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	

}
