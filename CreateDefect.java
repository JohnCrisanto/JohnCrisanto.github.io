package BugTrackerSystem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by John on 9/1/2016.
 */
@WebServlet(name = "CreateDefect", urlPatterns = ("/CreateDefect"))
public class CreateDefect extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Created ArrayList for testing
        ArrayList<Defect> defectList = new ArrayList<Defect>();

        //Added hardcoded objects into ArrayList
        defectList.add(new Defect("ProjectA", "felipe@erv.com.ca", 1000, "this is a summary", "high", "open", "this is a description"));
        defectList.add(new Defect("ProjectA", "john@tts.com", 1001, "this is a summary", "high", "open", "this is a description"));
        defectList.add(new Defect("ProjectB", "samuel@ggw.com", 1005, "this is a summary", "high", "open", "this is a description"));

        //Retrieve data from submitted form through createDefects.html and create a new Defect object using retrieved data
        String application = request.getParameter("application");
        String assignee = request.getParameter("assignee");
        Integer defectName = 0;
        String description = request.getParameter("description");
        String priority = request.getParameter("priority");
        String status = "open";
        String summary = request.getParameter("summary");

        Defect tempDefect = new Defect(application, assignee, defectName, description, priority, status, summary);

        Defect defect  = DataBaseHelper.createDefect(tempDefect);

        //Call static method sendEmail from Email Class passing in defect as the parameter
        Email.sendEmail(defect);

        //After sending the email, create dispatcher object to forward request and response to CreateDefect.jsp
        RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/CreateDefect.jsp");
        request.setAttribute("confirmation", "{" + defect.getDefectName() + "}: " + "{" + defect.getSummary() + "} has been successfully created.");
        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
