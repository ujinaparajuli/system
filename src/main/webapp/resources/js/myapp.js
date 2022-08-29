$('#exampleModal').on('show.bs.modal', function (event) {
  var div = $(event.relatedTarget) // Button that triggered the modal
  var id = div.data('id')
  var title = div.data('title')
  var price = div.data('price')
  var summary = div.data('summary')
  var img = div.data('img')
  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
  var modal = $(this)
  modal.find('.modal-body #summary').html(summary)
  modal.find('.modal-title').html(title)
  modal.find('.modal-body #price').html(price)
  var imgTag = modal.find('.modal-body #image')
  var imgUrl = "/resources/img/" + img + ".jpg"
  var addToCarturl = "/add/" + id
  var formTag = modal.find('.modal-footer .cart')
  imgTag.attr('src', imgUrl);
  formTag.attr('action', addToCarturl)
  
})

$('#ModalEditForm').on('show.bs.modal', function (event) {
  var div = $(event.relatedTarget) // Button that triggered the modal
  var id = div.data('id')
  var title = div.data('title')
  var price = div.data('price')
  var summary = div.data('summary')
  var img = div.data('img')
  var menuName = div.data('menu')
  
  var editItemUrl = "/admin/edit/item/" + id
  
  var modal = $(this)
  
  var formTag = modal.find(".modal-body .edit-item-form");
  formTag.attr('action', editItemUrl)
  
  $(".modal-body #title").val( title );
  $(".modal-body #price").val( price );
  $(".modal-body #summary").val( summary );
  $(".modal-body #menu").val( menuName );
  $(".modal-body #img").val( img );
  
})

$('#view-order-modal').on('show.bs.modal', function (event) {
  var div = $(event.relatedTarget) // Button that triggered the modal
  var fname = div.data('fname')
  var lname = div.data('lname')
  var items = div.data('items')
  var orderId = div.data('id')
  
  var modal = $(this)
  
  modal.find('.modal-title').html(fname + " " + lname)
  var txt = "";
  items = items.split("title=");
  
  for (i = 1; i < items.length; i++) {
	  var orderName = items[i].substr(0, items[i].indexOf(','));
	  
	  var countStr = items[i].split('count=')[1];
	  var count = countStr.substr(0, countStr.indexOf(','));
	  txt = txt + '<div class="row"><div class="col-6"><h4>' + orderName + ' ' + '(' + count + ')</h4></div></div>';
  }
  
  modal.find('.modal-body').html(txt)
  
  var viewOrderUrl = "/chef/order/" + orderId
  
  var aTagViewOrder = modal.find('.modal-footer .view-order-button')
  aTagViewOrder.attr('href', viewOrderUrl)
  
})

$(document).on("click", '.cart-link', function(e) {
                e.preventDefault();
                $.ajax({
                	  url: "/cart/view",
                	  type: "GET",
                	  success: function(json) {
                	    if(Object.keys(json).length !== 0){
                	    	var txt1 = "";          
                	    	
                	    	if(json.cartEmpty){
                	    		txt1 = '<p>your cart is empty</p>'
                	    	}else {
                	    		for (var i = 0; i < json.cartDtos.length; i++){
                    	    		
                    	    		var imgUrl = '/resources/img/' + json.cartDtos[i].img + '.jpg'
                    	    		var title = json.cartDtos[i].title
                    	    		var price = json.cartDtos[i].price
                    	    		var count = json.cartDtos[i].count
                    	    		var total = json.cartDtos[i].itemTotal
                    	    		
                    	    		txt1 = txt1 + '<tr><td class="w-25"><img src="' + imgUrl + '" class="img-fluid img-thumbnail" alt="Sheep"></td><td>' + title + '</td><td>' + price + '</td><td class="qty"><input id="quantity" type="number" value ="' + count + '" class="form-control quantity-input"></td><td>' + total + '</td><td><a href="#" class="btn btn-danger btn-sm"><i class="fa fa-times"></i></a></td></tr>'

//                    	    		txt1 = txt1 + '<tr><td class="w-25"><img src="' + imgUrl + '" class="img-fluid img-thumbnail" alt="Sheep"></td><td>' + title + '</td><td>' + price + '</td><td class="qty"><input type="text" class="form-control" id="input1" value="' + count + '"></td><td>' + total + '</td><td><a href="#" class="btn btn-danger btn-sm"><i class="fa fa-times"></i></a></td></tr>'  
                    	    		
                    	        }
                	    		
                	    		$('.modal-body .price').text("$" + json.grandtotalWithTax);
                	    	}
                	    	
                	    	$('.modal-body #t-body').html(txt1);
                	      $('#cartModal').modal('show'); // Showing modal
                	    }else {
                	      // Show error message
                	      $('#login-error').text("Invalid login.");
                	    }
                	  },

                	  error: function() {
                	    // Probably something wrong with the server.
                	    $('#login-error').text("Server error.");
                	  }   
                	});
                });


