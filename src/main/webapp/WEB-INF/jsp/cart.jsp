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

<section class="h-100 h-custom" style="background-color: #eee;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col">
        <div class="card">
          <div class="card-body p-4">

            <div class="row">

              <div class="col-lg-7">
                <h5 class="mb-3"><a href="<spring:url value="/"/>" class="text-body"><i
                      class="fas fa-long-arrow-alt-left me-2"></i>Continue shopping</a></h5>
                <hr>

                <div class="d-flex justify-content-between align-items-center mb-4">
                  <div>
                    <p class="mb-1">Shopping cart</p>
                    <p class="mb-0">You have ${itemCount} items in your cart</p>
                  </div>
                </div>
			<c:forEach items="${allCart}" var="cart">
                <div class="card mb-3">
                  <div class="card-body">
                    <div class="d-flex justify-content-between">
                      <div class="d-flex flex-row align-items-center">
                        <div>
                          <img
                            src="<c:url value='/resources/img/${cart.img}.jpg'/>"
                            class="img-fluid rounded-3" alt="Shopping item" style="width: 65px;">
                        </div>
                        <div class="ms-3">
                          <h5>${cart.title}</h5>
                       
                        </div>
                      </div>
                      <div class="d-flex flex-row align-items-center">
                        <div style="width: 50px;">
                          <h5 class="fw-normal mb-0">${cart.count}</h5>
                        </div>
                        <div style="width: 80px;">
                          <h5 class="mb-0">NRs ${cart.itemTotal}</h5>
                        </div>
                      </div>
                    </div>
                  </div>
                  </c:forEach>
                </div>

              </div>
              
              <div class="card mb-3">
                  <div class="card-body">
                    <div class="d-flex justify-content-between">
                      <div class="d-flex flex-row align-items-center">
                        <div class="ms-3">
                          <h5>Your Total is </h5>
                       
                        </div>
                      </div>
                      <div class="d-flex flex-row align-items-center">
                        <div style="width: 50px;">
                          
                        </div>
                        <div style="width: 80px;">
                          <h5 class="mb-0">NRs ${grandTotal}</h5>
                        </div>
                      </div>
                    </div>
                  </div>
       
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

 <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
		  <script src="<c:url value="/resources/js/popper.min.js" />"></script>
		   <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</body>