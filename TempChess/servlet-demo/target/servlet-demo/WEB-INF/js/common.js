$(document).ready(function(){
$('button').on('click', function () {
$.getJSON('/', function (data){
$('.text').val(data.sds);
}
});
});