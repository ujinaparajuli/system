<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">

<link href="<c:url value="/resources/css/bootstrap-readable-theme.css" />" rel="stylesheet">

</head>

<body>
	<div class="container">
	<form action="/cash/checkout/${orderId}" method="POST">
	  <div class="form-group">
	    <label for="exampleInputEmail1">Order Id</label>
	    <input type="order-id" class="form-control" name="orderId" aria-describedby="emailHelp" placeholder="Enter order id">
	  </div>
	  <button type="submit" class="btn btn-primary">Submit</button>
	</form>
	</div>
	
	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
		  <script src="<c:url value="/resources/js/popper.min.js" />"></script>
		   <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</body>