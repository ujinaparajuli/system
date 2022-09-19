<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

	<c:if test = "${isDashboardContainer}">
		 <%@include file="./dashboard-container.jsp"%>
	</c:if>
	
	<c:if test = "${isCheckoutContainer}">
		 <%@include file="./main_checkout.jsp"%>
	</c:if>
	
	<c:if test = "${isFromChef}">
		 <%@include file="./chef-container.jsp"%>
	</c:if>
	
	<c:if test = "${isFromAdmin}">
		 <%@include file="./admin-container.jsp"%>
	</c:if>
	
	<c:if test = "${isFromLogin}">
		 <%@include file="./login-container.jsp"%>
	</c:if>
	
	<c:if test = "${isFromLoggedInUser}">
		 <%@include file="./dashboard-container.jsp"%>
	</c:if>
	
	<c:if test = "${isCheckoutContainerLoggedIn}">
		 <%@include file="./main_checkout-user.jsp"%>
	</c:if>
	
</main>