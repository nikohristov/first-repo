<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%if(session.getAttribute("loggedUser") == null) 
		response.sendRedirect("index.jsp");
	%>
<title>Add own film</title>
</head>
<body>
		<center>
		<form action="AddOwnFilmServlet" method="post">
			<table>
				<caption>Film Characteristics:</caption>
				<tr>
						<td>Title: </td>
						<td><input type="text" name="title"></td>
				</tr>
				<tr>
						<td>Date: </td>
						<td><input type="text" name="date"></td>
				</tr>
				<tr>
						<td>Genre: </td>
						<td><input type="text" name="genre"></td>
				</tr>
				<tr>
						<td>Director: </td>
						<td><input type="text" name="director"></td>
				</tr>
				<tr>
						<td><input type="submit"></td>
				</tr>
			</table>
		</form>
		</center>
</body>
</html>