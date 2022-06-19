<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1 class="my-4">Categories</h1>
<div class="list-group">
<a href="<spring:url value="/category/all"/>" class="list-group-item" id="a_all">All</a>
	<c:forEach items="${categories}" var="category">
		<a href="<spring:url value="/category/${category}"/>" class="list-group-item" id="a_${category}">${category}</a>
	</c:forEach>
</div>