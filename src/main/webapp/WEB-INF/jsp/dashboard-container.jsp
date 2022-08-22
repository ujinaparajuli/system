<div class="row">
    <c:forEach items="${foodItems}" var="foodItem">
			<div class="item-card col-lg-4 col-md-6 mb-4" data-toggle="modal" data-target="#exampleModal" data-id=${foodItem.id} data-title=${foodItem.title} data-summary=${foodItem.summary} data-price=${foodItem.price} data-img=${foodItem.img} style="cursor: pointer;">
              <div class="card h-100">
                <img src="<c:url value='/resources/img/${foodItem.img}.jpg'/>" alt="Icon" width="100" height="100"/>
                <div class="card-body">
                  <h4 class="card-title">
                    ${foodItem.title}
                  </h4>
                  <h5>NRs ${foodItem.price}</h5>
                </div>
              </div>
            </div>
		</c:forEach>
  </div>
  
  <%@include file="./modal.jsp"%>