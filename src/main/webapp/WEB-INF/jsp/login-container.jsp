<div class="login-container">
      <form class="form-signin" method="post" action="/login">
        <h2 class="form-signin-heading">Please sign in</h2>
		<p>
          <label for="username" class="sr-only">Username</label>
          <input type="text" id="username" name="username" class="form-control" placeholder="Username" required="" autofocus="">
        </p>
        <p>
          <label for="password" class="sr-only">Password</label>
          <input type="password" id="password" name="password" class="form-control" placeholder="Password" required="">
        </p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
      
      <div class="sign-up">
      <div>
	      <button type="button" class="btn btn-link" data-toggle="modal" data-target="#ModalSignUpForm">
			    Sign Up
			</button>
      </div>
      <div>
      	<a href="/checkout" class="link-primary">Continue As a Guest</a>
      </div>
	</div>
</div>
<%@include file="./sign-up-modal.jsp"%>