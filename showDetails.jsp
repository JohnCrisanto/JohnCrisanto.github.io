<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<title> Defect Details </title>
<link href="/css/styles.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!--In page style sheet: only for this page-->
<style>
	div{
		width:50%;
		border:4px solid lightblue;
		border-radius: 25px;
		padding:20px;

	}
</style>
</head>
<body>

<h1>Project Name</h1>
<h2><span style="color:lightblue"> Defect Name: <%=request.getAttribute("defectName") %>
Summary <%=request.getAttribute("summary") %></span></h2>
<hr align="left" with="100%">
<br></br>

<div>
	<h3>Details:</h3>
	<hr align="left" with="50%">
	<label>Status: </label>
		<%=request.getAttribute("status") %>
	<br></br>
	<label>Priority: </label>
		<%=request.getAttribute("priority") %>
	<br></br>
	<label>Assignee: </label>
		<%=request.getAttribute("assignee") %>
</div>
<br></br>
<div>
	<h3>Description:</h3>
	<hr align="left" with="50%">
	<br></br>
	<textarea rows="4" col="100"> 
	<%=request.getAttribute("description") %>
	</textarea>
	<br></br>
</div>
<br></br>
<div>
	<h3>Comments:</h3>
	<hr align="left" with="50%">
	<br></br>
	<textarea rows="4" col="100"> 

	</textarea>
	<br></br>
</div>

<br></br>
<a href="listOfDefects.html">
	<button type = "button"> List Of Defects</button>
</a>
</body>
</html>