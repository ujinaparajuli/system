<div class="modal fade" id="cartModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header border-bottom-0">
        <h5 class="modal-title" id="exampleModalLabel">
          Your Shopping Cart
        </h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <table class="table table-image">
          <thead>
            <tr>
              <th scope="col"></th>
              <th scope="col">Product</th>
              <th scope="col">Price</th>
              <th scope="col">Qty</th>
              <th scope="col">Total</th>
            </tr>
          </thead>
          <tbody id="t-body">
          
            <!-- <tr>
              <td class="w-25">
                <img src="https://s3.eu-central-1.amazonaws.com/bootstrapbaymisc/blog/24_days_bootstrap/vans.png" class="img-fluid img-thumbnail" alt="Sheep">
              </td>
              <td>Vans Sk8-Hi MTE Shoes</td>
              <td>89$</td>
              <td class="qty"><input type="text" class="form-control" id="input1" value="2"></td>
              <td>178$</td>
              <td>
                <a href="#" class="btn btn-danger btn-sm">
                  <i class="fa fa-times"></i>
                </a>
              </td>
            </tr> -->
            
          </tbody>
        </table> 
        <div class="d-flex justify-content-end">
          <h5>Total: <span class="price text-success"></span></h5>
        </div>
      </div>
      <div class="modal-footer border-top-0 d-flex justify-content-between">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <spring:url value="/login" var="checkout" />
        <form class="checkout" action="${checkout}" method="GET">
			<button type="submit" class="btn btn-success">Checkout</button>
		</form>
      </div>
    </div>
  </div>
</div>