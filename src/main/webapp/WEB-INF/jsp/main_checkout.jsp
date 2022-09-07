<link href="dashboard.css" rel="stylesheet" type="text/css">

<div class="container">
    
    <div class="row g-5">
      <div class="col-md-5 col-lg-4 order-md-last">
        <h4 class="d-flex justify-content-between align-items-center mb-3">
          <span class="text-primary">Your cart</span>
        </h4>
        <ul class="list-group mb-3">
          <li class="list-group-item d-flex justify-content-between">
            <span>Total (NRs)</span>
            <strong>${total}</strong>
          </li>
        </ul>
      </div>
      <div class="col-md-7 col-lg-8">
    
     
     	<spring:url value="/postcheckout" var="postcheckout" />
<form:form method="post" class="needs-validation" action="${postcheckout}" modelAttribute="checkout">
	
	<div class="row g-3">
        <div class="col-sm-6">
          <form:label path="firstName" for="firstName" class="form-label">First name</form:label>
          <form:input type="text" class="form-control" id="firstName" path="firstName" placeholder="" value="" />
          <div class="invalid-feedback">
            Valid first name is required.
          </div>
        </div>

        <div class="col-sm-6">
          <form:label path ="lastName" for="lastName" class="form-label">Last name</form:label>
          <form:input type="text" class="form-control" id="lastName" path="lastName" placeholder="" value=""  />
          <div class="invalid-feedback">
            Valid last name is required.
          </div>
        </div>


        <div class="col-12">
          <form:label path="email" class="form-label">Email <span class="text-muted">(Optional)</span></form:label>
          <form:input type="email" class="form-control" id="email" path="email" placeholder="you@example.com" />
          <div class="invalid-feedback">
            Please enter a valid email address.
          </div>
        </div>

        <div class="col-12">
          <form:label path="mobile" class="form-label">Mobile</form:label>
          <form:input type="text" class="form-control" id="mobile" path="mobile" placeholder="11111111"  />
          <div class="invalid-feedback">
            Please enter your mobile number.
          </div>
        </div>
        
        <form:input type="hidden" path="total" name="total" value="${total}" />
        
      </div>
      
      <h4 class="mb-3"><form:label path="paymentMethod">Payment</form:label></h4>

          <div class="my-3">
            <div class="form-check">
            	<form:radiobutton path="paymentMethod" value="Credit card" data-toggle="modal" data-target="#exampleModal"/>Credit card
             
            </div>
            
            <div class="form-check">
            	<form:radiobutton path="paymentMethod" value="Cash"/>Cash
              
            </div>
          </div>


          <hr class="my-4">
          

          <form:button class="w-100 btn btn-primary btn-lg" type="submit">Confirm checkout</form:button>
</form:form>
     
     
      </div>
    </div>
</div>




<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Card Information</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">

    <div class="row">
        <div class="col mx-auto">
            <div id="pay-invoice" class="card">
                
                    <div class="card-title">
                        <h3 class="text-center">Pay Invoice</h3>
                    </div>
                    <hr>
                    <form action="/echo" method="post" novalidate="novalidate" class="needs-validation">
                        
                       
                        <div class="form-group">
                            <label for="cc-number" class="control-label mb-1">Card number</label>
                            <input id="cc-number" name="cc-number" type="tel" class="form-control cc-number identified visa" required autocomplete="off"  >
                            <span class="invalid-feedback">Enter a valid 12 to 16 digit card number</span>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="cc-exp" class="control-label mb-1">Expiration</label>
                                    <input id="cc-exp" name="cc-exp" type="tel" class="form-control cc-exp" required placeholder="MM / YY" autocomplete="cc-exp">
                                    <span class="invalid-feedback">Enter the expiration date</span>
                                </div>
                            </div>
                            <div class="col">
                                <label for="x_card_code" class="control-label mb-1">CVV</label>
                                <div class="input-group">
                                    <input id="x_card_code" name="x_card_code" type="tel" class="form-control cc-cvc" required autocomplete="off">
                                    <span class="invalid-feedback order-last">Enter the 3-digit code on back</span>
                                    <div class="input-group-append">
                                        <div class="input-group-text">
                                        <span class="fa fa-question-circle fa-lg" data-toggle="popover" data-container="body" data-html="true" data-title="CVV" 
                                        data-content="<div class='text-center one-card'>The 3 digit code on back of the card..<div class='visa-mc-cvc-preview'></div></div>"
                                        data-trigger="hover"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                            <button id="payment-button" type="submit" class="btn btn-lg btn-info btn-block">
                                <i class="fa fa-lock fa-lg"></i>&nbsp;
                                <span id="payment-button-amount">Pay </span>
                                <!-- <span id="payment-button-sending" style="display:none;">Sending…</span> -->
                            </button>
                            <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">Ok</button>
					        
					      </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </div>
      
    </div>
  </div>
</div>

<script>
$(function () {\r\n  $('[data-toggle=\"popover\"]').popover()\r\n})\r\n\r\n\r\n\r\n$(\"#payment-button\").click(function(e) {\r\n\r\n    \r\n    var form = $(this).parents('form');\r\n    \r\n    var cvv = $('#x_card_code').val();\r\n    var regCVV = /^[0-9]{3,4}$/;\r\n    var CardNo = $('#cc-number').val();\r\n    var regCardNo = /^[0-9]{12,16}$/;\r\n    var date = $('#cc-exp').val().split('/');\r\n    var regMonth = /^01|02|03|04|05|06|07|08|09|10|11|12$/;\r\n    var regYear = /^20|21|22|23|24|25|26|27|28|29|30|31$/;\r\n    \r\n    if (form[0].checkValidity() === false) {\r\n      e.preventDefault();\r\n      e.stopPropagation();\r\n    }\r\n    else {\r\n       if (!regCardNo.test(CardNo)) {\r\n       \r\n        $(\"#cc-number\").addClass('required');\r\n        $(\"#cc-number\").focus();\r\n        alert(\" Enter a valid 12 to 16 card number\");\r\n        return false;\r\n      }\r\n      else if (!regCVV.test(cvv)) {\r\n       \r\n        $(\"#x_card_code\").addClass('required');\r\n        $(\"#x_card_code\").focus();\r\n        alert(\" Enter a valid CVV\");\r\n        return false;\r\n      }\r\n      else if (!regMonth.test(date[0]) && !regMonth.test(date[1]) ) {\r\n       \r\n        $(\"#cc_exp\").addClass('required');\r\n        $(\"#cc_exp\").focus();\r\n        alert(\" Enter a valid exp date\");\r\n        return false;\r\n      }\r\n      \r\n      \r\n      \r\n      form.submit();\r\n    }\r\n    \r\n    form.addClass('was-validated');\r\n});"
</script>

    
  