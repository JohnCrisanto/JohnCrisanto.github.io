import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class TestProject extends HttpServlet {

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {

        //Read value from HTML form
        String clientName = request.getParameter("name");
        ArrayList<String> userList = new ArrayList<String>();
        userList.add("john");
        userList.add("samuel");
        userList.add("felipe");
        userList.add("larry");

        ArrayList<String> defectList = new ArrayList<String>();
        defectList.add("GT11000 Crash in frontend");
        defectList.add("GT11001 Cannot login");
        defectList.add("fGT11002 Slow response");
        defectList.add("GT11003 Blank window");

        //Forward to JSP
        RequestDispatcher dispatcher =
            getServletConfig().getServletContext().getRequestDispatcher(
                "/servlets/main.jsp");
        
        request.setAttribute("message" , "Hello " + clientName);
        request.setAttribute("userList" , userList);
        //request.setAttribute("defectList" , defectList);
        request.setAttribute("errorMessage" , "User not found, please try again");
        
        dispatcher.forward(request,response);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse
                      response) throws IOException, ServletException {
        doPost(request, response);
    }
}
