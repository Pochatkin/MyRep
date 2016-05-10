function y = psi(  )

syms x t;

temp = diff(u(x,t),x,1);

y = temp;

end

