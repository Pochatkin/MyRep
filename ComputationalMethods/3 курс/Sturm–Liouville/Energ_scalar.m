function y = Energ_scalar(i, j)

syms x;


g = W(i,x)*W(j,x)*q(x) + p(x)*diff(W(i,x),x)*diff(W(j,x),x);
y = vpa(int(g,-1,1) + 0.8*p(-1)*W(i,-1)*W(j,-1) + 0.85*p(1)*W(i,1)*W(j,1));


end

