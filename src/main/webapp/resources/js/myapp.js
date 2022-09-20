$('#exampleModal').on('show.bs.modal', function (event) {
  var div = $(event.relatedTarget) // Button that triggered the modal
  var id = div.data('id')
  var title = div.data('title')
  var price = div.data('price')
  var summary = div.data('summary')
  var img = div.data('img')
  var imgType = div.data('imgType') 
  var review = div.data('review')
  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
  var modal = $(this)
  modal.find('.modal-body #summary').html(summary)
  modal.find('.modal-title').html(title)
  modal.find('.modal-body #price').html(price)
  var imgTag = modal.find('.modal-body #image')
  var imgUrl = "data:" + imgType + ";base64," + img
  var addToCarturl = "/add/" + id
  var formTag = modal.find('.modal-footer .cart')
  imgTag.attr('src', imgUrl);
  formTag.attr('action', addToCarturl)
  
  var stars = $('span.stars');
  stars.html($('<span />').width(Math.max(0, (Math.min(5, parseFloat(review)))) * 40));
})

//$.fn.stars = function() {
//  return $(this).each(function() {
//    $(this).html($('<span />').width(Math.max(0, (Math.min(5, parseFloat(2)))) * 40));
//  });
//}

$('#deleteItemModal').on('show.bs.modal', function (event) {
  var div = $(event.relatedTarget) // Button that triggered the modal
  var id = div.data('id')
  var modal = $(this)
  
  var deleteUrl = "/admin/item/delete/" + id
  var aTagDelete = modal.find('.modal-footer .delete-link')
  aTagDelete.attr('href', deleteUrl)
  
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
                    	    		
//                    	    		var imgUrl = '/resources/img/' + json.cartDtos[i].img + '.jpg'
                    	    		var imgUrl = "data:" + json.cartDtos[i].imageType + ";base64," + json.cartDtos[i].imageName
                    	    		var title = json.cartDtos[i].title
                    	    		var price = json.cartDtos[i].price
                    	    		var count = json.cartDtos[i].count
                    	    		var total = json.cartDtos[i].itemTotal
                    	    		var itemId = json.cartDtos[i].itemId
                    	    		
                    	    		txt1 = txt1 + '<tr><td class="w-25"><img src="' + imgUrl + '" class="img-fluid img-thumbnail" alt="Sheep" height="80" width="80"></td><td>' + title + '</td><td>' + price + '</td><td class="qty"><input id="quantity" type="number" value ="' + count + '" min="1" max="10" onchange="updateCart(' + itemId + ', this)"></td><td>' + total + '</td><td><button class="btn btn-danger btn-sm"><i class="fa fa-times" onclick="removeItemCart(' + itemId + ')"></i></button></td></tr>';
//                    	    		txt1 = txt1 + '<tr><td class="w-25"><img src="' + imgUrl + '" class="img-fluid img-thumbnail" alt="Sheep"></td><td>' + title + '</td><td>' + price + '</td><td class="qty"><input type="text" class="form-control" id="input1" value="' + count + '"></td><td>' + total + '</td><td><a href="#" class="btn btn-danger btn-sm"><i class="fa fa-times"></i></a></td></tr>'  
                    	    		
                    	        }
                	    		
                	    		$('.modal-body .price').text("NRs " + json.grandtotalWithTax);
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


function updateCart(itemId, count) {
    $.ajax({
    	url: '/add/' + itemId + '/' + count.value,
        type: "GET",

        // Function to call when to
        // request is ok 
        success: function (json) {
        	if(Object.keys(json).length !== 0){
    	    	var txt1 = "";          
    	    	
    	    	if(json.cartEmpty){
    	    		txt1 = '<p>your cart is empty</p>'
    	    	}else {
    	    		for (var i = 0; i < json.cartDtos.length; i++){
        	    		
//        	    		var imgUrl = '/resources/img/' + json.cartDtos[i].img + '.jpg'
        	    		var imgUrl = "data:" + json.cartDtos[i].imageType + ";base64," + json.cartDtos[i].imageName
        	    		var title = json.cartDtos[i].title
        	    		var price = json.cartDtos[i].price
        	    		var count = json.cartDtos[i].count
        	    		var total = json.cartDtos[i].itemTotal
        	    		var itemId = json.cartDtos[i].itemId
        	    		
        	    		txt1 = txt1 + '<tr><td class="w-25"><img src="' + imgUrl + '" class="img-fluid img-thumbnail" alt="Sheep" height="80" width="80"></td><td>' + title + '</td><td>' + price + '</td><td class="qty"><input id="quantity" type="number" value ="' + count + '" min="1" max="10" onchange="updateCart(' + itemId + ', this )"></td><td>' + total + '</td><td><button class="btn btn-danger btn-sm"><i class="fa fa-times" onclick="removeItemCart(' + itemId + ')"></i></button></td></tr>';
//        	    		txt1 = txt1 + '<tr><td class="w-25"><img src="' + imgUrl + '" class="img-fluid img-thumbnail" alt="Sheep"></td><td>' + title + '</td><td>' + price + '</td><td class="qty"><input type="text" class="form-control" id="input1" value="' + count + '"></td><td>' + total + '</td><td><a href="#" class="btn btn-danger btn-sm"><i class="fa fa-times"></i></a></td></tr>'  
        	    		
        	        }
    	    		
    	    		$('.modal-body .price').text("NRs " + json.grandtotalWithTax);
    	    	}
    	    	
    	    	$('.modal-body #t-body').html(txt1);
            }
        },

        // Error handling 
        error: function (error) {
            console.log(`Error ${error}`);
        }
    });
}

function removeItemCart(itemId) {
    $.ajax({
    	url: '/delete/' + itemId,
        type: "GET",

        // Function to call when to
        // request is ok 
        success: function (json) {
        	if(Object.keys(json).length !== 0){
    	    	var txt1 = "";          
    	    	
    	    	if(json.cartEmpty){
    	    		txt1 = '<p>your cart is empty</p>'
    	    	}else {
    	    		for (var i = 0; i < json.cartDtos.length; i++){
        	    		
//        	    		var imgUrl = '/resources/img/' + json.cartDtos[i].img + '.jpg'
        	    		var imgUrl = "data:" + json.cartDtos[i].imageType + ";base64," + json.cartDtos[i].imageName
        	    		var title = json.cartDtos[i].title
        	    		var price = json.cartDtos[i].price
        	    		var count = json.cartDtos[i].count
        	    		var total = json.cartDtos[i].itemTotal
        	    		var itemId = json.cartDtos[i].itemId
        	    		
        	    		txt1 = txt1 + '<tr><td class="w-25"><img src="' + imgUrl + '" class="img-fluid img-thumbnail" alt="Sheep" height="80" width="80"></td><td>' + title + '</td><td>' + price + '</td><td class="qty"><input id="quantity" type="number" value ="' + count + '" min="1" max="10" onchange="updateCart(' + itemId + ', this )"></td><td>' + total + '</td><td><button class="btn btn-danger btn-sm"><i class="fa fa-times onclick="removeItemCart(' + itemId + ')""></i></button></td></tr>';
//        	    		txt1 = txt1 + '<tr><td class="w-25"><img src="' + imgUrl + '" class="img-fluid img-thumbnail" alt="Sheep"></td><td>' + title + '</td><td>' + price + '</td><td class="qty"><input type="text" class="form-control" id="input1" value="' + count + '"></td><td>' + total + '</td><td><a href="#" class="btn btn-danger btn-sm"><i class="fa fa-times"></i></a></td></tr>'  
        	    		
        	        }
    	    		
    	    		$('.modal-body .price').text("NRs " + json.grandtotalWithTax);
    	    	}
    	    	
    	    	$('.modal-body #t-body').html(txt1);
            }
        },

        // Error handling 
        error: function (error) {
            console.log(`Error ${error}`);
        }
    });
}

$('#credit-card-radio').change(function() {
    if(this.checked) {
    	var innerHtml = '<div class="row gy-3"><div class="col-md-6"><label for="cc-name" class="form-label">Name on card</label><input type="text" class="form-control" id="cc-name" placeholder="" required><small class="text-muted">Full name as displayed on card</small><div class="invalid-feedback">Name on card is required</div></div><div class="col-md-6"><label for="cc-number" class="form-label">Credit card number</label><input type="text" class="form-control" id="cc-number" placeholder="" required><div class="invalid-feedback">Credit card number is required</div></div><div class="col-md-3"><label for="cc-expiration" class="form-label">Expiration</label><input type="text" class="form-control" id="cc-expiration" placeholder="" required><div class="invalid-feedback">Expiration date required</div></div><div class="col-md-3"><label for="cc-cvv" class="form-label">CVV</label><input type="text" class="form-control" id="cc-cvv" placeholder="" required><div class="invalid-feedback">Security code required</div></div></div>'
    	$('.card-form').html(innerHtml);
    }
});

$('#cash-radio').change(function() {
    if(this.checked) {
    	$('.card-form').html('');
    }
});


