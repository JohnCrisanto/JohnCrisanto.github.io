<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<title>Bug Tracker</title>
</head>
<body>
<table width="100%" border="0">
  <tr valign="top">
    <td bgcolor="#b5dcb3" width="100%">	
      <h2>New / Update Defect</h2>
	  <p> Please fill defect fields below and click submit</p>
      <form action="http://localhost/HandleDefect" method=POST>
	  <table width="50%" border="0">
	  <tr>
	  <td>Defect Id (Leave blank for a new defect):</td><td><input type="text" name="head" /></td>
	  </tr>
	  <tr>
	  <td>Application:</td><td><select name="appName">
      <option value="Frontend" selected>Frontend System</option>
      <option value="BackEnd">Backend System</option>
      </select></td>
	  </tr>
	  <tr>
	  <td>Heading:</td><td><input type="text" name="head" /></td>
	  </tr>
	  <tr>
	  <td>Description:</td><td><input type="text" name="desc" /></td>
	  </tr>
	  <tr>
      <td>Priority:</td><td><select name="prio">
      <option value="high" selected>High</option>
      <option value="medium">Medium</option>
	  <option value="low">Low</option>
      </select></td>
	  <tr>
	  <td>Assignee:</td><td><select name="owner">
      <%  
      // retrieve your list from the request, with casting 
      ArrayList<String> list = (ArrayList<String>) request.getAttribute("userList");
      // print the user list
      for(String user : list) {
          out.println("<option value=\"" + user + "\">" + user + "</option>");
      }
      %>
      </select></td>
	  </tr>
	  <tr>
	  </tr>
      </table>
	  <br />
      <input type="submit" accept="submit" value="Submit" />
	  <br />
      </form>
	</td>
  </tr>
  <tr valign="top">
    <td bgcolor="silver" width="100%">	
      <h2>Defect Search</h2>
      <form action="http://localhost/SearchDefect" method=POST>
      Defect ID:  <input type="text" name="defectId" />
      <br />
	  <br />
      <input type="submit" accept="submit" value="Submit" />
      </form>
	</td>
  </tr>
    <tr valign="top">
    <td bgcolor="#b5dcb3" width="100%">	
      <h2>Application Defects</h2>
	  <p> Select an application to see its defects </p>
      <form action="http://localhost/ListDefects" method=POST>
      Application: <select name="appName">
      <option value="Frontend" selected>Frontend System</option>
      <option value="BackEnd">Backend System</option>
      </select>
      <input type="submit" accept="submit" value="Submit" />
      </form>
	  <p> Results: </p>
	  <ul>
          <%  
          // retrieve your list from the request, with casting 
          if (request.getAttribute("defectList") != null) {
              ArrayList<String> defectList = (ArrayList<String>) request.getAttribute("defectList");
              // print the information about every category of the list
              for(String defect : defectList) {
                  out.println("<li>" + defect + "</li>");
              }
          } else {
              out.println("<p>None</p>");
          }
          %>
      </ul>
	</td>
  </tr>
</body>
</html>
