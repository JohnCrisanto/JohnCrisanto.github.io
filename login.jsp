<!DOCTYPE html>
<html>
<head>
<title>Bug Tracker</title>
</head>
<body>
<table width="100%" border="0">
  <tr>
    <td colspan="2" bgcolor="#b5dcb3" style="text-align:center;">
      <h1>JAVA II Summer/2016 Team C</h1>
	</td>
  </tr>
  <tr valign="top">
    <td bgcolor="silver" width="100%">	
      <h2>Welcome to the Bug Tracker</h2>
      <form action="http://localhost/Login" method=POST>
      <p> Please enter your user ID to Login </p>
      User ID:  <input type="text" name="userId" />
      <br>
      <p> <b>First time users</b> to Sign-up, enter also your email </p>
      Email:  <input type="text" name="email" />
      <br/>
      <br/>
      <input type="submit" accept="submit" value="Submit" />
      </form>
      <% if (request.getAttribute("errorMessage") != null) {
	      out.println("<br><b>ERROR: " + request.getAttribute("errorMessage") + "</b>");
	  }
      %>
    </td>
  </tr>
</body>
</html>