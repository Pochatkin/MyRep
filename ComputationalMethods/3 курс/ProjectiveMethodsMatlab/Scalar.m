function y = Scalar( k,j )

syms x;
n = k;
m = j;
Lu(x) = (-(5-x)/(7-3*x) * diff(W(n,x),x,2) - (1-x)/2 * diff(W(n,x),x) + (1+1/2*sin(x))*W(n,x)) * W(m,x);
y = eval(int(Lu(x),-1,1));

end

