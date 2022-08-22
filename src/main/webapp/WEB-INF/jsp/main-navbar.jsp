<nav class="navbar sticky-top flex-md-nowrap p-0 navbar-light" style="background-color: #e3f2fd;"">
   <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Company name</a>
   <form class="form-inline col-sm-9">
	  <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
	  <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
	</form>
	<ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
	       	<a class ="cart-link" href="<spring:url value="/cart/view"/>">
	       		<i class="fa fa-shopping-cart fa-4x" aria-hidden="true"></i>
	       	</a>
       	
        </li>
      </ul>
</nav>

<%@include file="./cart-modal.jsp"%>