<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Distribution Manager|Add Employee</title>
</head>
<body>
	<h2>Add Employee</h2>
	<form action="employees/add" method="post">
		<label for="givenname">First Name: </label>
		<input id="givenname" name="givenname" type="text" required="required"><br>
		<label for="surname"> Surname: </label> 
		<input id="surname" name="surname" type="text" required="required"> <br>
		<label for="username"> Username: </label>
		<input id="username" name="username" type="text" required="required"> <br>
		<label for="role"> Role: </label> 
		<select id="role" name="role" >
			<option value="cashier">cashier</option>
		</select> <br>
		<button type="submit" name="add" value="Add Employee">Add Employee</button>
	</form>
</body>
</html>