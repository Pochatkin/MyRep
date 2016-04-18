$(document).ready(function(){
    $('#Send').on('click',function(){
        var email = $('#email');
        if(isEmail(val(email))){
            var dataToServer = {
                action: 'forgotPassword',
                email: val(email)
            }
            var str = JSON.stringify(dataToServer);
            alert(str);
            $.post('main',dataToServer,function(data){
                if(data.response === 'Ok'){
                    window.location.href = '/jchess/SignInAndSignUp.jsp';
                } else {
                    alert('Oooops. We have some trouble. Please, try again.');
                }
            })
        } else {
            alert('Incorrect email!');
        }
    })
})

function isEmail(email){
    for(i = 0, i < email.length, i++ ){
        //blablabla
    }
}