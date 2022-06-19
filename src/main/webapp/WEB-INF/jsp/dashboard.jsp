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


<c:if test = "${isCheckoutSuccessful}">
<div class="alert alert-primary" role="alert">
  your payment is successful , please wait for your order, you will get notification via email and mobile number once your order is ready.
</div>
</c:if>

<%@include file="./container.jsp"%>

 <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
		  <script src="<c:url value="/resources/js/popper.min.js" />"></script>
		   <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</body>

</html>