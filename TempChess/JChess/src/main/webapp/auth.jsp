<html>
    <head>
        <title>Start</title>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
        <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
        <link rel="stylesheet" href="css/style.css">

         <script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
         <script type="text/javascript" src="js/authorization.js"></script>

    </head>

    <body>
    <div class="container">
      <div class="card"></div>
      <div class="card">
        <h1 class="title">Login</h1>
        <form>
          <div class="input-container">
            <input type="text" id="loginSignIn" required="required">
            <label for="login">Login</label>
            <div class="bar"></div>
          </div>
          <div class="input-container">
            <input type="password" id="passwordSignIn" required="required">
            <label for="password">Password</label>
            <div class="bar"></div>
          </div>
          <div class="button-container">
            <button value="SignIn" id="SignIn"><span>Sign In</span></button>
          </div>
          <div class="footer"><a href="/jchess/forgotPass.jsp">Forgot your password?</a></div>
        </form>
      </div>


      <div class="card alt">
          <div class="toggle"></div>
          <h1 class="title">Register
            <div class="close"></div>
          </h1>
          <form>
            <div class="input-container">
              <input type="text" id="loginSignUp" required="required"/>
              <label for="login">Login</label>
              <div class="bar"></div>
            </div>
            <div class="input-container">
              <input type="password" id="passwordSignUp" required="required"/>
              <label for="password">Password</label>
              <div class="bar"></div>
            </div>
            <div class="input-container">
              <input type="password" id="repeatPasswordSignUp" required="required"/>
              <label for="repeat_password">Repeat Password</label>
              <div class="bar"></div>
            </div>
            <div class="button-container">
              <button id = 'SignUp'><span>SignUp</span></button>
            </div>
          </form>
        </div>

    </div>
    </body>
</html>