<nav class="col-md-2 d-none d-md-block bg-light sidebar">
  <div class="sidebar-sticky">
    <ul class="nav flex-column">
      <li class="nav-item">
      <a class="nav-link" href="<spring:url value="/category/all"/>">
          All
        </a>
      </li>
      <c:forEach items="${categories}" var="category">
		<li class="nav-item">
        <a class="nav-link" href="<spring:url value="/category/${category}"/>">
          ${category}
        </a>
      </li>
      </c:forEach>
    </ul>
  </div>
</nav>