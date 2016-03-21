<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<%if(session.getAttribute("loggedUser") == null) 
		response.sendRedirect("index.jsp");
	%>
</head>
<body>
		<table border="1">
				<tr>
					<th>Title</th>
					<th>Date</th>
					<th>Genre</th>
					<th>Director</th>			
				</tr>
				<c:forEach var="Film" items="${sessionScope.favFilms}">
				<tr>
					<td>"${Film.title}"</td>
					<td>"${Film.date}"</td>
					<td>"${Film.genre}"</td>
					<td>"${Film.director}"</td>
					<td>
						<form action="MoreInfoServlet" method="post">
							<input type="hidden" name="title" value="${Film.title}">
							<input type="hidden" name="date" value="${Film.date}">
							<input type="submit" value="More Info">
						</form>
					</td>
				</tr>
				</c:forEach>
		</table>
		<br>
		<a href="main.jsp"><button>Back</button></a>
</body>
</html>