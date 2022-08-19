<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

	<c:if test = "${isDashboardContainer}">
		 <%@include file="./dashboard-container.jsp"%>
	</c:if>
	
	<c:if test = "${isViewCartContainer}">
		 <%@include file="./cart1.jsp"%>
	</c:if>
	
	<c:if test = "${isCheckoutContainer}">
		 <%@include file="./checkout.jsp"%>
	</c:if>
	
</main>