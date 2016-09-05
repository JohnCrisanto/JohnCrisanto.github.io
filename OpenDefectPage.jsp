<%@ page import = "java.util.*" %>
<%@ page import = "java.io.*" %>
<%@ page import = "teamc.Defect" %>
<%@ page import = "java.lang.*" %>
<%@ page import = "teamc.DetailTester" %>

<!DOCTYPE HTML>
<html lang = "en-US">

<head>
	<title>Open Defects</title>
	<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>

<body>
	<div style = "background-color: lightblue; text-align:center;height: 100px">
		<h1>List Of Open Defects</h1>
	</div>
	<br></br>
	<a href = "createDefects.html">
		<input type = "submit" value ="+ Create Defect">
	</a>
	<br></br>
	<!--Search for Open Defects based on selected Project-->
    <form action="OpenDefects" method="post">
	     <select name = "choice">
			<option value="all" selected>All Open Defects</option>
			<option value="ProjectA">Project A </option>
			<option value="ProjectB">Project B </option>
			<option value="ProjectC">Project C </option>
	     </select>
       <input type="submit" value="submit">
    </form>
	<br></br>
	<!--Start Table : Need to query info from database. These are sample data only-->

    <form action="DetailTester" method="post">
         <% List<Defect> newList = (ArrayList<Defect>) request.getAttribute("passby");%> 
         <%// System.out.println("Inside jsp: " + newList.get(1).getDescription()); %>
	<table>
		<thead>
			<tr>
				<th>
					 Defect Name
				</th>
				<th>
					Summary
				</th>
				<th>
					Assignee
				</th>
			</tr>
		</thead>
		<tbody>

        <%for(Defect useList : newList){
        	Integer cell1 = useList.getDefectName();
        	String cell2 = useList.getDescription();
        	String cell3 = useList.getAssignee();
//        	System.out.println(cell1 + " " + cell2 + " " + cell3);
        	
        	
        	%>

			<tr>
				<td>
				    <input type="radio" name="commonKey" value= <%=cell1%> checked = "checked">  <%= cell1%><br>
				</td>
				<td>
					<%=cell2%>
				</td>
				<td>
					<%=cell3%>
				</td>
			</tr>
         <%} %>
		</tbody>
	</table>

       <input type="submit" value="submit">

    </form>


</body>

</html>