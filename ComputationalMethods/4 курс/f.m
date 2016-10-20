function z = f(x, y)

syms t r;

temp = subs(- diff(diff(u(t,r), t), t) - diff(diff(u(t,r), r),r) ,t, x);
z = subs(temp, r, y); 


end

