<h3> Popular Items </h3>
<div class="bd-example">
  <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
	    <c:forEach items="${popularItems}" var="foodItem" varStatus="loop">
	    <c:if test = "${loop.index == 0}">
	    	<li data-target="#carouselExampleCaptions" data-slide-to="${loop.index}" class="active"></li>
	    </c:if>
	    <c:if test = "${loop.index != 0}">
	    	<li data-target="#carouselExampleCaptions" data-slide-to="${loop.index}"></li>
	    </c:if>
	    </c:forEach>
    </ol>
    <div class="carousel-inner">
	    <c:forEach items="${popularItems}" var="popularItem" varStatus="loop">
	    
	    <c:if test = "${loop.index == 0}">
			 <div class="carousel-item active">
		        <img src="/resources/img/${popularItem.img}.jpg" class="d-block" alt="..." height="400">
		        <div class="carousel-caption d-none d-md-block">
		          <h5>${popularItem.title}</h5>
		          <p>${popularItem.summary}</p>
		        </div>
	      </div>
		</c:if>
		
		<c:if test = "${loop.index != 0}">
			 <div class="carousel-item">
		        <img src="/resources/img/${popularItem.img}.jpg" class="d-block alt="..." height="400">
		        <div class="carousel-caption d-none d-md-block">
		          <h5>${popularItem.title}</h5>
		          <p>${popularItem.summary}</p>
		        </div>
	      </div>
		</c:if>
	    	
	    </c:forEach>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>