function y = energ_scalar(u,v)
    
syms x;
syms t;
syms u;
u = Jacobi(2,1,x);
y = integral(symfun(u,[t]),-1,1);
    
end


