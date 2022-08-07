<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<nav class="navbar navbar-expand-lg navbar navbar-dark bg-primary">
  <div class="container-fluid">  
    <a class="navbar-brand" href="#">My Application</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="<spring:url value="/"/>">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="<spring:url value="/cart/view"/>">View Cart</a>
        </li>
		  <!--Navbar-->
		  <nav class="navbar navbar-dark default-color">
		    <!-- <form class="form-inline my-2 my-lg-0 ml-auto">
		      <input class="form-control" type="search" placeholder="Search" aria-label="Search">
		      <button class="btn btn-outline-white btn-md my-2 my-sm-0 ml-3" type="submit">Search</button>
		    </form> -->
		    <spring:url value="search" var="search" />
			<form:form method="get" action="${search}">
				<form:input class="form-control" type="search" placeholder="Search" id="text" path="text" name="text" />
				<form:button class="btn btn-outline-white btn-md my-2 my-sm-0 ml-3" type="submit">Search</form:button>
			</form:form>
		  </nav>
		  <!--/.Navbar-->
      </ul>
    </div>
  </div>
</nav>