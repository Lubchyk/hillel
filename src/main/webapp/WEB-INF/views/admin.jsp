<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Admin page</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
			  integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
			  crossorigin="anonymous">

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
			  integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
			  crossorigin="anonymous">

		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
				integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
				crossorigin="anonymous"></script>
		<title>Title</title>
	</head>
	<body>
	<ol class="breadcrumb">
		<li class="active">AdminPage</li>
		<li><a href="/manager">ManagerPage</a></li>
		<li><a href="/user">UserPage</a></li>
	</ol>

	<form action = "/createOrder" name="form0"  method="get" >
	<div class="panel panel-primary" style="margin-top: 7%">
		<div class="panel-heading">
			<h3 class="panel-title">Create Order</h3>
		</div>
		<div class="panel-body">
			<input name="date"  type="date"  placeholder="Enter the date of order">
			<select name="status">
				<option value="NEW">NEW</option>
				<option value="IN_PROGRESS">IN_PROGRESS</option>
				<option value="DONE">DONE</option>
				<option value="ABOLITION">ABOLITION</option>
			</select>
			<input name="user"  type="text" placeholder="Enter the id of user">
			<button id="but1" type="submit" size = "20" class="btn btn-default navbar-btn">Create</button>
		</div>
	</div>
	</form>
	<form action = "/updateOrders" name="form1"  method="get" >
	<div class="panel panel-success">
		<div class="panel-heading">
			<h3 class="panel-title">Update Order</h3>
		</div>
		<div class="panel-body">
			<input name="id"  type="text" placeholder="Enter the id of order">
			<input name="date"  type="date"  placeholder="Enter the date of order">
			<select name="status">
				<option value="NEW">NEW</option>
				<option value="IN_PROGRESS">IN_PROGRESS</option>
				<option value="DONE">DONE</option>
				<option value="ABOLITION">ABOLITION</option>
			</select>
			<input name="user"  type="text" placeholder="Enter the id of user">
			<button id="but2" type="submit" size = "20" class="btn btn-default navbar-btn">Update</button>
		</div>
	</div>
	</form>
	<form action = "/deleteOrders" name="form2"  method="post" >
		<div class="panel panel-danger">
			<div class="panel-heading">
				<h3 class="panel-title">Delete Order</h3>
			</div>
			<div class="panel-body">
				<input name="number" size="35" type="text"  placeholder="Enter the number of order">
				<button id="but3" type="submit" size = "20" class="btn btn-default navbar-btn">Delete</button>
			</div>
		</div>
	</form>
	<form action = "/topOrders" name="form3"  method="get" >
		<div class="panel panel-warning">
			<div class="panel-heading">
				<h3 class="panel-title">Top Order</h3>
			</div>
			<div class="panel-body">
				<button id="but4" type="submit" size = "20" class="btn btn-default navbar-btn">TOP THREE ORDERS </button>
			</div>
		</div>
	</form>

	<div class="conteiner">
		<div class="row" >
			<div class="col-md-6 col-md-offset-3" >
				<div class="panel panel-info" >
					<div class="panel-heading">
						<h3 class="panel-title">Search Orders</h3>
					</div>
					<div class="panel-body">
						<form action = "/findOne" class="navbar-form navbar-left" method="get" >
							<div class="form-group">
								<input name="id" size="35" type="text" class="form-control" placeholder="Enter the number of order">
							</div>
							<button type="submit" class="btn btn-default">Search</button>
						</form>
						<form action = "/findAll" class="navbar-form navbar-left" method="get" >
							<button style="margin-left: 150px" type="submit" class="btn btn-default">Get All Orders</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

		<form action="/logout" method="get" style="background: black;color: white; border-radius: 1em; padding: 1em; position: absolute;
                top: 11%; left: 50%; margin-right: -50%; transform: translate(-50%, -50%) " class="form-inline">
			Dear <strong>${user}</strong>, Welcome to Admin Page.
			<button style="height:35px;width:100px" id="but6" type="submit" size = "20" class="btn btn-default navbar-btn">Logout</button>
		</form>
	</body>
</html>