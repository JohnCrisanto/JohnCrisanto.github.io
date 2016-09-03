package teamc;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DetailTester extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		//Get choice from OpenDefectPage jsp radio buttons.
		String answer = request.getParameter("commonKey");

        System.out.println("Inside DetailTester " + answer);
		

	}
}
