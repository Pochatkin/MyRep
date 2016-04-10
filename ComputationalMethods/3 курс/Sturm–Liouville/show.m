function y = show( x,u )

syms c;


z = cos(u*x)+c*sin(u*x);
z1 = -u*sin(u*x)+c*u*cos(u*x);

y = vpa(z + 0.85*z1);
disp(y);

end

