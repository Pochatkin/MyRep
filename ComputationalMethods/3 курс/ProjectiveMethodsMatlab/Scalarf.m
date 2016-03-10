function y = Scalarf( k  )

syms x;
n = k;
g(x) = f(x) * W(n,x);
y = eval(int(g,-1,1));

end

