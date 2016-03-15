function y = Lu( n,x )

y = (-(5-x)/(7-3*x) * diff(W(n,x),x,2) - (1-x)/2 * diff(W(n,x),x) + (1+1/2*sin(x))*W(n,x));


end

