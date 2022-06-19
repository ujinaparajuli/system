<spring:url value="/postcheckout" var="postcheckout" />          
          <form:form method="POST" class="needs-validation"
          action="${postcheckout}" modelAttribute="checkoutDto">
          
          <div class="row g-3">
            <div class="col-sm-6">
              <label for="firstName" class="form-label">First name</label>
              <form:input type="text" class="form-control" id="firstName" path="firstName" placeholder="" value="" />
              <div class="invalid-feedback">
                Valid first name is required.
              </div>
            </div>

            <div class="col-sm-6">
              <label for="lastName" class="form-label">Last name</label>
              <form:input type="text" class="form-control" id="lastName" path="lastName" placeholder="" value=""  />
              <div class="invalid-feedback">
                Valid last name is required.
              </div>
            </div>


            <div class="col-12">
              <label for="email" class="form-label">Email <span class="text-muted">(Optional)</span></label>
              <form:input type="email" class="form-control" id="email" path="email" placeholder="you@example.com" />
              <div class="invalid-feedback">
                Please enter a valid email address for shipping updates.
              </div>
            </div>

            <div class="col-12">
              <label for="mobile" class="form-label">Mobile</label>
              <form:input type="text" class="form-control" id="mobile" path="mobile" placeholder="11111111"  />
              <div class="invalid-feedback">
                Please enter your mobile number.
              </div>
            </div>
          </div>
          
          <h4 class="mb-3">Payment</h4>

          <div class="my-3">
            <div class="form-check">
              <input id="credit" name="paymentMethod" type="radio" class="form-check-input" checked required>
              <label class="form-check-label" for="credit">Credit card</label>
            </div>
            <div class="form-check">
              <input id="debit" name="paymentMethod" type="radio" class="form-check-input" required>
              <label class="form-check-label" for="debit">Debit card</label>
            </div>
            <div class="form-check">
              <input id="cash" name="paymentMethod" type="radio" class="form-check-input" required>
              <label class="form-check-label" for="cash">Cash</label>
            </div>
          </div>


          <hr class="my-4">
          

          <button class="w-100 btn btn-primary btn-lg" type="submit">Confirm checkout</button>
        </form:form>