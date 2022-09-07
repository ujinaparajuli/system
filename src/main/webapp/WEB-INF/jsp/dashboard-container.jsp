<c:if test = "${isCheckoutSuccessful}">
		<div class="alert alert-primary alert-dismissible fade show" role="alert">
		  Checkout is successful, Please wait for your order. Once order is ready we will mail you.
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>

</c:if>

<%@include file="./popular-item-carousel.jsp"%>

<div class="row main-dashboard-container">
	<c:forEach items="${foodItems}" var="foodItem">
	
		<div class="card item-card mb-4 ml-5" data-toggle="modal" data-target="#exampleModal" data-id="${foodItem.id}" data-title="${foodItem.title}" data-summary="${foodItem.summary}" data-price="${foodItem.price}" data-img="${foodItem.img}" style="cursor: pointer;">
			<div class="row">
				<div class="col-lg-6 text-right">
				 		<img src="<c:url value='/resources/img/${foodItem.img}.jpg'/>" alt="Icon" width="100" height="70"/>
				</div>
				<div class="col-lg-6 text-left">
					${foodItem.title} <br> <br> NRs ${foodItem.price}
				 </div>
			</div>
		</div>
	</c:forEach>
</div>
  
  <%@include file="./modal.jsp"%>