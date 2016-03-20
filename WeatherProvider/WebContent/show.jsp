<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show Weather</title>
</head>
<body>
					<center><table>
							<caption><c:out value="${requestScope.name}"/></caption>
							<tr>
									<th>ID: <c:out value="${requestScope.id}"/></th>
							</tr>
							<tr>
									<td>Temperature: <c:out value="${requestScope.temp}"/></td>
							</tr>
							<tr>
									<td>Weather desc: <c:out value="${requestScope.desc}"/></td>
							</tr>
							<tr>
									<td>Count clouds: <c:out value="${requestScope.cloud}"/></td>
							</tr>
							<tr>
									<td>
										<form action="index.jsp">
												<input type="submit" value="Another city">
										</form>

									</td>
							</tr>
					</table>
					</center>
					
	
</body>
</html>