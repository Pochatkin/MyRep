$(document).ready(function(){
    $('Search').on('click',function(){
        var dataToServer = {
            active: 'search'
        }
        $.post('main',dataToServer,function(data){
            if(data.color === 'white'){
                window.location.href = '/jchess/whiteBoard.jsp';
            }
            if(data.color === 'black'){
                window.location.href = '/jchess/blackBoard.jsp';
            }
        });
    })
})