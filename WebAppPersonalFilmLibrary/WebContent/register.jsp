<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
			<center>
				<form action="SignUpServlet" method="post">
				<table>
						<c:if test="${requestScope.errorMessage != null}">
							<font color="red">The user name exists</font>
						</c:if>
						<caption>Register</caption>
						<tr>
								<td>
								  	User name<br>
								  	<input type="text" name="username">
								</td>
						</tr>
						<tr>
								<td>
								  	password<br>
								  	<input type="password" name="password">
								</td>
						</tr>
						<tr>
								<td>
								  	<input type="submit" value="Register me">
								</td>
						</tr>
				</table>
				</form>
			</center>
			
	
</body>
</html>