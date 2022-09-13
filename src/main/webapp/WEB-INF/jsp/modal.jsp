<div class="modal fade" id=exampleModal tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle"></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        
        <div class="row">
			<div class="col-lg">
				<img id="image" class="img img-responsive" width="200" height="200"></img>
			</div>
			<div class="col-lg">
				<p id ="summary"></p>
			</div>
		</div>
		<div class="row">
		
			<div class="col-6">
				<div class="review-container">
				  <span class="stars"></span>
				</div>
			</div>
			<div class="col-6">
				<h4>Price: NRs <strong id="price"></strong></h4>
			</div>
		
		</div>
        
      </div> 

      
      <div class="modal-footer">
		<form class="cart" method="POST">
			<button type="submit" class="btn btn-primary"><span class="bi bi-cart"></span> Add to cart</button>

	    </form>
      </div>
    </div>
  </div>
</div>