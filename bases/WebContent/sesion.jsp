<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Datos Sesion jsp</title>
	</head>
	
	<body>
		<h1>Datos Sesion jsp</h1>

		<hr>
			${sessionScope.email}
		<hr>
			<p>Email: ${email}</p>
		<hr>
			<p>Password:${password}</p>
	</body>
</html>