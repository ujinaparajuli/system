<c:choose>
    <c:when test="${empty orders}">
       <p> Currently no order is placed </p>
    </c:when>    
    <c:otherwise>
    	<p> All Orders</p>
	    <c:forEach items="${orders}" var="order">
	    	<div class="row order-row">
			  <div class="col-sm-8">Order Number ${order.orderId}</div>
			  <div class="col-sm-4"><button type="button" class="btn btn-light" data-toggle="modal" data-target="#view-order-modal" data-id="${order.orderId}" data-fname="${order.firstName}" data-lname="${order.lastName}" data-items="${order.items}">View Order</button></div>
			</div>
	    </c:forEach>
    </c:otherwise>
</c:choose>
<%@include file="./view-order-modal.jsp"%>