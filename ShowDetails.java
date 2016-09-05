import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Get Database of all defects.
		ArrayList<Defect> searchedDefect = new ArrayList<Defect>();
		searchedDefect = DataBaseHelper.searchDefect("defectName",
				"application");
		
		// setting up Dispatcher
		RequestDispatcher dispatcher = getServletConfig().getServletContext()
				.getRequestDispatcher("/showDetails.jsp");

		// set values to request

		// Defect("ProjectA", "felipe@erv.com.ca", 1000, "this is a summary",
		// "high", "open", "this is a description"));

		request.setAttribute("application", searchedDefect.get(0));
		request.setAttribute("assignee", searchedDefect.get(1));
		request.setAttribute("defectName", searchedDefect.get(2));
		request.setAttribute("summary", searchedDefect.get(3));
		request.setAttribute("priority", searchedDefect.get(4));
		request.setAttribute("status", searchedDefect.get(5));

		// forward to JSP
		dispatcher.forward(request, response);

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);

	}
}
