<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 9/1/2016
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateDefect</title>
</head>
<body>
<h1>
<%= request.getAttribute("confirmation") %>
    </div>
    <br></br>
    <form action="../OpenDefects" method="POST">
        <input type="submit"  value="Go Back to List of Defects.">
    </form>
    <br></br>
</h1>
</body>
</html>
