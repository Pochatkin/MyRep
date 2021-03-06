function [y,z] =  Gauss( n )

syms x;

for j=1:1:(2*n-1)
    phi(j) = 1 / j;
end
for j=1:1:n
    for k=1:1:n
        A(j,k) = phi(n+j-k);
    end
end

for j=1:1:n
    b(j) = -phi(n+j - 1);
end

a = A\(b');

temp = x^n;

for j=1:1:n
    temp = temp + x^(n-j) * a(j);
end

disp(vpa(temp));


roots = solve(temp);
roots = real(roots);


for j=1:1:n
    coef(j) = int(temp /((x-roots(j)) * subs(diff(temp),x,roots(j))),0,1);
end

y = roots;
z = coef;

disp(vpa(y));
disp(vpa(z));

end

