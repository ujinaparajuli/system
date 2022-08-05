<div class="container">

      <div class="row">

        <div class="col-lg-3">

          <%@include file="./sidebar.jsp"%>

        </div>
        <!-- /.col-lg-3 -->

			  <div class="col-lg-9">
			  <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
			  <div class="carousel-inner">
			    <div class="carousel-item active">
			      <img src="chickenfriedrice.jpg" class="d-block w-100" alt="...">
			    </div>
			    <div class="carousel-item">
			      <img src="coffee.jpg" class="d-block w-100" alt="...">
			    </div>
			    <div class="carousel-item">
			      <img src="vthali.jpg" class="d-block w-100" alt="...">
			    </div>
			    <div class="carousel-item">
			      <img src="VegSpringRoll.jpg" class="d-block w-100" alt="...">
			    </div>
			    
			  </div>
			  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Previous</span>
			  </button>
			  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Next</span>
			  </button>
			</div>

          <div class="row">
          
				<c:forEach items="${foodItems}" var="foodItem">
					<div class="col-lg-4 col-md-6 mb-4">
		              <div class="card h-100">
		                <img src="<c:url value='/resources/img/${foodItem.img}.jpg'/>" alt="Icon" width="100" height="100"/>
		                <div class="card-body">
		                  <h4 class="card-title">
		                    <a href="<spring:url value="/item/${foodItem.id}"/>">${foodItem.title}</a>
		                  </h4>
		                  <h5>NRs ${foodItem.price}</h5>
		                  
		                </div>
		                <div class="card-footer">
			                <spring:url value="/add/${foodItem.id}" var="addToCart" />
							<form class="cart" action="${addToCart}" method="POST">
								<button type="submit"><a />Add to cart</a></button>
						    </form>
		                </div>
		              </div>
		            </div>
				</c:forEach>

          </div>
          <!-- /.row -->

        </div>
        <!-- /.col-lg-9 -->

      </div>
      <!-- /.row -->

    </div>