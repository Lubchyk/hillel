<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Welcome page</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
		  integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
		  crossorigin="anonymous">

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
		  integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
		  crossorigin="anonymous">

	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
			integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
			crossorigin="anonymous"></script>
</head>
	<body>
		<style>
			body {
				background: white }
			form {
				background: black;
				color: white;
				border-radius: 1em;
				padding: 1em;
				position: absolute;
				top: 50%;
				left: 50%;
				margin-right: -50%;
				transform: translate(-50%, -50%) }

			but1 {
				height:300px;
				width:500px;
			}
		</style>
		<ol class="breadcrumb">
			<li><a href="/admin">AdminPage</a></li>
			<li><a href="/manager">ManagerPage</a></li>
			<li><a href="/user">UserPage</a></li>
		</ol>

		<form action="/logout" method="get" class="form-inline">
			Dear <strong>${user}</strong>, You can not get to the selected page.
			<button style="height:35px;width:100px" id="but6" type="submit" size = "20" class="btn btn-default navbar-btn">Logout</button>
		</form>

	</body>
</html>