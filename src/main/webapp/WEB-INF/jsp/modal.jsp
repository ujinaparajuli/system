<div class="modal fade" id=exampleModal tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        
        <div class="row">
				<div class="col-xs-12 col-lg-4">
					<img id="image" class="img img-responsive" ></img>
				</div>
				<div class="col-xs-12 col-lg-8">
					<h3 id="title"></h3>
					<hr/>
					
					<p id ="summary"></p>
					<hr/>
					
					<h4>Price: NRs <strong id="price"></strong></h4>
					<hr/>
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