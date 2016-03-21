<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main</title>
	<%if(session.getAttribute("loggedUser") == null) 
		response.sendRedirect("index.jsp");
	%>
</head>
<body>
		
		<table border="2">
				<tr>
					<th>Title</th>
					<th>Date</th>
					<th>Genre</th>
					<th>Director</th>
					<th>Add to Favorite</th>
					<th>Add to Watched</th>
				</tr>
				<c:forEach var="Film" items="${sessionScope.films}">
				<tr>
					<td>"${Film.title}"</td>
					<td>"${Film.date}"</td>
					<td>"${Film.genre}"</td>
					<td>"${Film.director}"</td>
					<td>
						<form action="AddFavouriteFilmServlet" method="post">
								<input type="hidden" name="filmDate" value="${Film.date}">
								<input type="hidden" name="setFav" value="${Film.title}">
								<input type="checkbox" checked="<c:if test="${Film.isFavourite == true}">checked</c:if>" onClick="this.form.submit();">Favourite
						</form>
					</td>
					<td>
						<form action="AddWatchedFilmServlet" method="post">
								<input type="hidden" name="filmDate" value="${Film.date}">
								<input type="hidden" name="setWat" value="${Film.title}">
								<input type="checkbox" checked="<c:if test="${Film.isWatched() == true}"><font>checked</font></c:if>" onClick="this.form.submit();">Watched
						</form>
					</td>
					<c:if test="${Film.isUpload() == true}">
					<td>
						<form action="AddOwnFilmServlet" method="get">
								<input type="hidden" name="forDel" value="${Film.title}">
								<input type="submit" value="Delete">
						</form>
					</td>	
					</c:if>	
				</tr>
				</c:forEach>
				<tr>
					<td>
						<form action="LogInServlet" method="get">
								<select name="orderBy">
  									<option value="ASC">ASC</option>
 								    <option value="DESC">DESC</option>
 								 </select>
 								 <input type="submit" value="Show">
						</form>
					</td>
					<td>
						<form action="ShowFilmsServlet" method="get">
								<input type="submit" value="Show my favourites films">
						</form>
					</td>
					<td>
						<form action="ShowFilmsServlet" method="post">
								<input type="submit" value="Show my watched films">
						</form>
					</td>
				</tr>
		</table>
		<center><a href="addFilm.jsp"><button>Add Own Film</button></a></center>
		
</body>
</html>