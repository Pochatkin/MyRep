$(document).ready(function(){

    $('#SignIn').on('click', function(){
        var login = $('#loginSignIn');
        var password = $('#passwordSignIn');
        //var req = new XMLHttpRequest();
        var dataToServer = {
            action: 'SignIn',
            login: val(login),
            password: val(password)
        }
        var str = JSON.stringify(dataToServer);
        alert(str);
        /*req.open("POST",'/main',true);
        req.send(data);
        req.onreadystatechange = function() {
            if (req.readyState === 4 && req.status === 200){
                answer =  JSON.parse(req.responseText);
            }
                else return;
        }*/
        $.post('main', dataToServer, function(data){
            if(data.response === 'Ok'){
                window.location.href = '/jchess/main.jsp';
            } else {
                alert('Incorrect login/password');
            }
        });
    });

    $('#SignUp').on('click',function(){
        var login = $('#loginSignUp');
        var password = $('#passwordSignUp');
        var repeatPassword = $('#repeatPasswordSignUp');
        if(val(password) === val(repeatPassword)){
            var dataToServer = {
                action: 'SignUp',
                login: val(login),
                password: val(password)
            }
            var str = JSON.stringify(dataToServer);
            alert(str);
            $.post('main',dataToServer,function(data){
                if(data.response === 'Ok'){
                    window.location.href = '/jchess/main.jsp';
                } else {
                    alert('Trouble');
                }
            })
        } else {
            alert('Passwords is not equal');
        }
    })

    $('.toggle').on('click', function() {
      $('.container').stop().addClass('active');
    });

    $('.close').on('click', function() {
      $('.container').stop().removeClass('active');
    });

});