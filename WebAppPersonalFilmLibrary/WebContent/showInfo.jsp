<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<img src="${requestScope.FilmForInfo.poster}">
		
		<table>
				<tr>
						<td>Author: "${requestScope.FilmForInfo.title}"</td>
				</tr>
				<tr>
						<td>Date: "${requestScope.FilmForInfo.date}"</td>
				</tr>
				<tr>
						<td>Director: "${requestScope.FilmForInfo.director}"</td>
				</tr>
				<tr>
						<td>Genre: "${requestScope.FilmForInfo.genre}"</td>
				</tr>
				<tr>
						<td>Runtime: "${requestScope.FilmForInfo.runtime}"</td>
				</tr>
				<tr>
						<td>Rating: "${requestScope.FilmForInfo.rating}"</td>
				</tr>
				</table>
				<c:if test="${requestScope.FilmForInfo.isFavourite == true}">
					 Comments: "${requestScope.FilmForInfo.comment}"
					 
							<form action="MoreInfoServlet" method="get">	
									<textarea name="comment"> </textarea>
									<input type="hidden" name="title" value="${requestScope.FilmForInfo.title}">
									<input type="hidden" name="date" value="${requestScope.FilmForInfo.date}">
									<input type="submit" value="add comment">
							</form>
								
				</c:if>
				
					<a href="main.jsp"><button>Main Menu</button></a>
					<a href="showFavourite.jsp"><button>Back</button></a>
				
		
</body>
</html>