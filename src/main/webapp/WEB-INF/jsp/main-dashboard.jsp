<!doctype html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="utf-8">

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap-readable-theme.css" />" rel="stylesheet">
	
	<!-- font awesome core CSS -->
    <%-- <link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet"> --%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/dashboard.css" />" rel="stylesheet">
  </head>

  <body>
  <c:if test = "${isDashboardNavBar}">
  	<%@include file="./main-navbar.jsp"%>
  </c:if>

    <div class="container-fluid">
      <div class="row">
        <c:if test = "${isDashboardSideBar}">
        	<%@include file="./main-sidebar.jsp"%>
        </c:if>
		
		<%@include file="./main-container.jsp"%>

      </div>
    </div>
    
    <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/popper.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	
	<script src="<c:url value="/resources/js/myapp.js" />"></script>
  </body>
</html>