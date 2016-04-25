function y = Ritz( n )

syms x;

for j = 1:1:n
    for r = 1:1:n
        f(j,r) = Energ_scalar(j,r);
    end
end

disp(f);

[R, D] = eig(f);

temp = 0;

for j = 1:1:n
    for r = 1:1:n
        temp = temp + vpa(R(r,1) * W(r,x));
    end
    z(j) = temp;
end


disp('Собственные числа:');
y = eig(f);

disp(y);
disp(' ');
disp('Собственные функции');

for j = 1:1:n
   disp(z(j));
end

disp('');
disp('Невязка');

for r = 1:1:n
    temp = z(r);
    g = (-1)*diff(p(x)*diff(temp,x),x)+q(x)*temp-y(r)*temp;
    disp(vpa(g));
    disp(' ');
    disp(vpa(sqrt(int(g^2,-1,1))) / 1000);
    disp(' ');
end

end

