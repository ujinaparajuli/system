<div id="ModalSignUpForm" class="modal fade">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title">Sign Up</h1>
            </div>
            <div class="modal-body">
                <form:form role="form" method="POST" action="/signup" modelAttribute="signup">
                    <input type="hidden" name="_token" value="">
                    <div class="form-group">
                        <form:label class="control-label" path="email">Email</form:label>
                        <div>
                            <form:input type="email" class="form-control input-lg" name="email" path="email"></form:input>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label class="control-label" path="password">Password</form:label>
                        <div>
                            <form:input type="password" class="form-control input-lg" name="password" path="password"></form:input>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label class="control-label" path="confirm">Confirm Password</form:label>
                        <div>
                            <form:input type="password" class="form-control input-lg" name="confirm" path="confirm"></form:input>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label class="control-label" path="fName">First Name</form:label>
                        <div>
                            <form:input type="text" class="form-control input-lg" name="fName" path="fName" value="" />
           
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label class="control-label" path="lName">Last Name</form:label>
                        <div>
                            <form:input type="text" class="form-control input-lg" name="lName" path="lName" value=""></form:input>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label class="control-label" path="mobile">Mobile</form:label>
                        <div>
                            <form:input type="text" class="form-control input-lg" name="mobile" path="mobile"></form:input>
                        </div>
                    </div>
                    <div class="form-group">
                        <div>
                            <button type="submit" class="btn btn-success">
                                Sign Up
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>