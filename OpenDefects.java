package teamc;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/OpenDefects")
public class OpenDefects extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //Get choice of project to list defects
		String choice = request.getParameter("choice");
        if(choice == null){
        	choice = "all";
        }
		System.out.println("What was the choice: " + choice);
		
		//Get Database of all defects.
		ArrayList<Defect> allDefects = new ArrayList<Defect>();
		allDefects = DataBaseHelper.searchDefect("*","");
        System.out.println("Inside Servlet found allDefects: " + allDefects.get(1).getDescription());
        //Choose only defects from open projects & that are open.
		
		ArrayList<Defect> openDefects = new ArrayList<Defect>();
        
		for(Defect openOnly : allDefects){
	    	if(choice.equals("all") && openOnly.getStatus() == "open"){
	    		openDefects.add(openOnly);
	    	}
	    	else if(choice.equals("ProjectA") && openOnly.getapplication() == "ProjectA" && openOnly.getStatus() == "open"){
	    		openDefects.add(openOnly);
	    	}
	    	else if(choice.equals("ProjectB") && openOnly.getapplication() == "ProjectB"  && openOnly.getStatus() == "open"){
	    		openDefects.add(openOnly);
	    	}
	    	else if(choice.equals("ProjectC") && openOnly.getapplication() == "ProjectC"  && openOnly.getStatus() == "open"){
	    		openDefects.add(openOnly);
	    	}	    		
	    }
	
	    request.setAttribute("passby", openDefects);
		
        System.out.println("Inside Servlet found ArrayList3: " + openDefects.get(0).getDescription());
		
		//Instantiate the dispatcher
		RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/jsp/OpenDefectPage.jsp");

		dispatcher.forward(request,  response);;

		openDefects = null;
	}  

}
