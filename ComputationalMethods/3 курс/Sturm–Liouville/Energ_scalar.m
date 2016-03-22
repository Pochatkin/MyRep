function y = Energ_scalar(i, j)

syms x;

p(z) = 1/(2+z/3);

g = W(i,x)*W(j,x);

y = int(g,-1,1) + 0.8*p(-1)*W(i,-1)*W(j,-1) + 0.85*p(1)*W(i,1)*W(j,1);



end

