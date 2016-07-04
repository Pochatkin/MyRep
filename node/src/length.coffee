swap (a,b) -> for k, v of object
	# ...


str = 'asdasdasdasd'
for i in [0..str.length]
	temp = str[i]
	str[i] = str[str.length - i]
	str[str.length - i] = temp