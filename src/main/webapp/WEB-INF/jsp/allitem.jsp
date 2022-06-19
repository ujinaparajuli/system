<div class="container">

	<div class="col-lg-9">

			<div class="row">

				<div class="col-xs-12">

					<table class="table table-striped table-borderd" id="product-datatable">
						<thead>
							<tr>
								<th>Item Name</th>
								<th>Summary</th>
								<th>Price</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${foodItems}" var="foodItem">
								<tr id="table-content">
									<td>${foodItem.title}</td>
									<td>${foodItem.summary}</td>
									<td>$ NRs{foodItem.price}</td>
								</tr>
							</c:forEach>
						</tbody>

					</table>

				</div>

			</div>

	</div>
</div>