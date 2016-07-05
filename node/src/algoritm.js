var string = '((2131231231231)';
var lastIndex, delta, i, j, k;


function foo(index){		
	if(lastIndex != undefined){
		console.log('in if');
		console.log(lastIndex);
		if(Math.abs(lastIndex - index) <= delta){
			console.log('No for');		}
	} else {
		console.log('In else')
		i = -1,
		j = -1,
		k = 0;
		while(i === -1 || j === -1){
			if((string.charAt(index + k) === ')') && (i === -1))
				i = index + k;
			if((string.charAt(index - k) === '(') && (j === -1))
				j = index - k;
			k++; 
			if(k > 1000){
				console.log('Erorr');
				break;
			}
		}
	}
	lastIndex = index;
	delta = Math.min(i,j);
	console.log('lastIndex = ' + lastIndex + '  delta = ' + delta);
	return [j,i];
}


for(var i = 3; i < string.length - 1; i++){
	console.log(foo(i));
	console.log('-----------------------------');
}
console.log(string.length);