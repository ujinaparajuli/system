<nav class="navbar sticky-top flex-md-nowrap p-0 navbar-light" style="background-color: #e3f2fd;"">
   <div class="row main-nav-row">
	   <div class="col-sm-2 text-center">
	   		<a href="/user/">Home</a>
	   </div>
	   <div class="col-sm-1">
	   		<a class ="cart-link" href="<spring:url value="/cart/view"/>">View Cart</a>
	   	</div>
	   <div class="col-sm-6">
		   <form class="form-inline" action="/search" method="post">
			  <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="text">
			  <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
	   </div>
	   <div class="col-sm-3 text-right">
	   		<a class ="login-link" href="<spring:url value="/logout"/>">Logout</a>
		</div>
   </div>
</nav>

<%@include file="./cart-modal.jsp"%>