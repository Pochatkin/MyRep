function y = Scalar( i,j )

syms x;

g = W(i,x) * W(j,x);

y = int(g,-1,1);


end

