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

for p = 1:1:n
    for r = 1:1:n
        temp = temp + vpa(R(r,p) * W(r,x));
    end
    z(p) = temp;
    temp = 0;
end

disp('Собственные числа:');
y = D;

disp(y);
disp(' ');
disp('Собственные функции');
for p = 1:1:n
    disp(z(p));
end
end

