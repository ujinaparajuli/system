<div class="row">
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