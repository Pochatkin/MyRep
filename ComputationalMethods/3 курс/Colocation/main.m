
function y = main( k)

syms x;

for p=1:1:k
    t(p) = cos(pi*(2*p-1)/(2*k));
end



% Lu(n,x) = (-(5-x)/(7-3*x) * diff(W(n,x),x,2) - (1-x)/2 * diff(W(n,x),x) + (1+1/2*sin(x))*W(n,x));


for r=1:1:k
    f1(r) = f(t(r));
end

for p =1:1:k
    for r=1:1:k
        Lu1(p,r) = eval(subs(Lu(r,x),x,t(p)));
    end
end

y = Lu1\(f1');

disp(y);

temp = 0;
for r=1:1:k
    temp = temp + y(r)*W(r,-1/2);
end

disp(temp)

temp = 0;
for r=1:1:k
    temp = temp + y(r)*W(r,0);
end

disp(temp)

temp = 0;
for r=1:1:k
    temp = temp + y(r)*W(r,1/2);
end

disp(temp)


end



