<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Weather provider</title>
</head>
<body>
		<center><h1>Hello, Choose your city, which you want a weather: </h1>	
		<table>
			<tr>
				<td>Enter name of city:</td>
			</tr>
			<tr>
				<td>
				<form action="GetWeatherServlet" method="get">
						<input type="text" name="cityName"><br>
						<input type="submit" value="Show me">
				</form>
				</td>
			</tr>
		</table></center>
		
		
</body>
</html>