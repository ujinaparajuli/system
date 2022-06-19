
<spring:url value="/postcheckout" var="postcheckout" />
<form:form method="post" class="needs-validation" action="${postcheckout}" modelAttribute="checkout">
	
	<div class="row g-3">
        <div class="col-sm-6">
          <form:label path="firstName" for="firstName" class="form-label">First name</label>
          <form:input type="text" class="form-control" id="firstName" path="firstName" placeholder="" value="" />
          <div class="invalid-feedback">
            Valid first name is required.
          </div>
        </div>

        <div class="col-sm-6">
          <form:label path ="lastName" for="lastName" class="form-label">Last name</label>
          <form:input type="text" class="form-control" id="lastName" path="lastName" placeholder="" value=""  />
          <div class="invalid-feedback">
            Valid last name is required.
          </div>
        </div>


        <div class="col-12">
          <form:label path="email" class="form-label">Email <span class="text-muted">(Optional)</span></label>
          <form:input type="email" class="form-control" id="email" path="email" placeholder="you@example.com" />
          <div class="invalid-feedback">
            Please enter a valid email address.
          </div>
        </div>

        <div class="col-12">
          <form:label path="mobile" class="form-label">Mobile</label>
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
            	<form:radiobutton path="paymentMethod" value="Credit card"/>Credit card
             
            </div>
            <div class="form-check">
            	<form:radiobutton path="paymentMethod" value="Debit card"/>Debit card
             
            </div>
            <div class="form-check">
            	<form:radiobutton path="paymentMethod" value="Cash"/>Cash
              
            </div>
          </div>


          <hr class="my-4">
          

          <form:button class="w-100 btn btn-primary btn-lg" type="submit">Confirm checkout</button>
</form:form>