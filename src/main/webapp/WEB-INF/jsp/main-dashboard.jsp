<!doctype html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
  <head>
    <meta charset="utf-8">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


	<!-- font awesome core CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/dashboard.css" />" rel="stylesheet">
  </head>

  <body>
  <c:if test = "${isDashboardNavBar}">
  	<%@include file="./main-navbar.jsp"%>
  </c:if>
  
  <c:if test = "${isCheckOutNavBar}">
  	<%@include file="./main-navbar-checkout.jsp"%>
  </c:if>
  
  <c:if test = "${isFromChef}">
  	<%@include file="./main-navbar-chef.jsp"%>
  </c:if>
  
  <c:if test = "${isFromAdmin}">
  	<%@include file="./main-navbar-admin.jsp"%>
  </c:if>
  
  <c:if test = "${isFromLogin}">
  	<%@include file="./main-navbar-login.jsp"%>
  </c:if>

    <div class="container-fluid">
      <div class="row">
        <c:if test = "${isDashboardSideBar}">
        	<%@include file="./main-sidebar.jsp"%>
        </c:if>
		
		<%@include file="./main-container.jsp"%>

      </div>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
	<script src="<c:url value="/resources/js/myapp.js" />"></script>
  </body>
</html>