<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>
			<center>
					<form action="LogInServlet" method="post">
					<table>
						<c:if test="${requestScope.errorMessage != null}">
							<font color="red"><c:out value="${requestScope.errorMessage}"/></font>
						</c:if>
						<caption>Log In</caption>
						<tr>
								<td>Username</td>
								<td><input type="text" name="name"></td>
						</tr>
						<tr>
								<td>Password</td>
								<td><input type="password" name="password"></td>
						</tr>
						<tr>
								<td>
									<% request.setAttribute("errorMessage", null);%>
									<input type="submit" value="Log In">
									<a href="register.jsp"><input type="button" value="Register"></a>
								</td>
						</tr>
					</table>
					</form>		
			</center>
			
	
</body>
</html>