$(document).ready(function(){

    var login = document.getElementById('login');
    var password = document.getElementById('password');
    $('#SignIn').on('click', function(){
        var req = new XMLHttpRequest();
        var data = {
        login: login.value,
        password: password.value
        }
        var str = JSON.stringify(data);
        alert(str);
        /*req.open("POST","home of server",true);
        req.send(data);
        req.onreadystatechange = function() {
            if (req.readyState === 4 && req.status === 200){
                answer =  JSON.parse(req.responseText);
            }
                else return;
        }
        if(answer === 'Ok'){
            //TODO: GOTO to next jsp
        }*/

    });

    $('.toggle').on('click', function() {
      $('.container').stop().addClass('active');
    });

    $('.close').on('click', function() {
      $('.container').stop().removeClass('active');
    });

});