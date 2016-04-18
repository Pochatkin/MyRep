<html>
    <head>
        <title>Game page</title>
        <script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
        <script type="text/javascript" src="/jchess/js/chess_board_blackPlayer.js"></script>
        <link rel="stylesheet" href="css/style.css">
        <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet" >
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">

    </head>
    <body onload='draw();'>
       		<div  class="row">
    		    <div class="col-sm-3">
    		    <img src="img/logo.png" class ="im" width="200" height="200">
    		    <img src="img/logo2.png" class ="im" width="200" height="200">
    		    </div>
    		    <div id="Board" class="pull-left">
    			    <canvas id="chess" width="800" height="800" border="0"></canvas>
    		    </div>
    		    <div class="col-md-3">
                    <table class="table table-striped">
                    <thead ><tr><th>White</th><th>Black</th></tr></thead>
                    <tbody>
                      <tr><td>F6</td><td>A4</td></tr>
                      <tr><td>..</td><td>..</td></tr>
                      <tr><td>..</td><td>..</td></tr>
                      <tr><td>..</td><td>..</td></tr>
                      <tr><td>..</td><td>..</td></tr>
                      <tr><td>..</td><td>..</td></tr>
                      <tr><td>..</td><td>..</td></tr>
                      <tr><td>..</td><td>..</td></tr>
                      <tr><td>..</td><td>..</td></tr>
                      <tr><td>..</td><td>..</td></tr>
                      <tr><td>..</td><td>..</td></tr>
                      <tr><td>..</td><td>..</td></tr>
                      <tr><td>..</td><td>..</td></tr>
                      <tr><td>..</td><td>..</td></tr>
                      <tr><td>..</td><td>..</td></tr>
                      <tr><td>..</td><td>..</td></tr>
                      <tr><td>..</td><td>..</td></tr>
                      <tr><td>..</td><td>..</td></tr>
                      <tr><td>..</td><td>..</td></tr>
                      <tr><td>..</td><td>..</td></tr>
                      </tbody>
                    </table>
                </div>
            </div>

    </body>
</html>