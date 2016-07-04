var string = '(2131231231231)';



function foo(index){
	var i = 0,
		j = 0,
		k = 0;
	while(i === 0 || j === 0){
		if((string.charAt(index + k) === ')') && (i === 0))
			i = k;
		if((string.charAt(index - k) === '(') && (j === 0))
			j = k;
		k++; 
		if(k > 1000){
			console.log('Erorr');
			break;
		}
	}
	return [i,k];
}


for(var i = 3; i < string.length - 1; i++){
	console.log(foo(i));
}
console.log(string.length);