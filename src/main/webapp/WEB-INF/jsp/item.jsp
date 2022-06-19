<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">

<link href="<c:url value="/resources/css/bootstrap-readable-theme.css" />" rel="stylesheet">

</head>

<body>

<%@include file="./navbar.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-xs-12">
			<nav class="breadcrumb">
				<a class="breadcrumb-item" href="<c:url value='/'/>">Home</a> <a
					class="breadcrumb-item" href="<c:url value='/category/all'/>">AllItems</a>
				<span class="breadcrumb-item active">${foodItem.title}</span>
			</nav>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12 col-lg-4">
			<img src="<c:url value='/resources/img/${foodItem.img}.jpg'/>" class="img img-responsive" />
		</div>
		<div class="col-xs-12 col-lg-8">
			<h3>${foodItem.title}</h3>
			<hr/>
			
			<p>${foodItem.summary}</p>
			<hr/>
			
			<h4>Price: NRs <strong>${foodItem.price}</strong></h4>
			<hr/>
			<spring:url value="/add/${foodItem.id}" var="addToCart" />
			<form class="cart" action="${addToCart}" method="POST">
				<button type="submit"><a class="btn btn-success">
											<span class="glyphicon glyphicon-shopping-cart"></span>Add To Cart</a></button>
		    </form>
		</div>
	</div>
</div>


 <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
		  <script src="<c:url value="/resources/js/popper.min.js" />"></script>
		   <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</body>