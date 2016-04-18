function y = Ritz( n )

syms x;

for p = 1:1:n
    for r = 1:1:n
        f(p,r) = Energ_scalar(p,r);
    end
end

disp(f);

[R, D] = eig(f);

temp = 0;


for r = 1:1:n
    temp = temp + vpa(R(r,1) * W(r,x));
end
z1 = temp;

for r = 1:1:n
    temp = temp + vpa(R(r,2) * W(r,x));
end
z2 = temp;

for r = 1:1:n
    temp = temp + vpa(R(r,3) * W(r,x));
end
z3 = temp;

disp('Собственные числа:');
y = eig(f);

disp(y);
disp(' ');
disp('Собственные функции');

    disp(vpa(z1));
    disp(vpa(z2));
    disp(vpa(z3));
disp('');
disp('Невязка');

t(x) = z1;
h(x) = diff(t(x),x);
g = (-1)*diff(p(x)*h(x),x)+q(x)*t(x)-y(p)*t(x);
disp(vpa(sqrt(int(g^2,-1,1))));
disp(' ');

end

