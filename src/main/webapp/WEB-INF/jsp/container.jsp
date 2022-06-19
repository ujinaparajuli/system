<div class="container">

      <div class="row">

        <div class="col-lg-3">

          <%@include file="./sidebar.jsp"%>

        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

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