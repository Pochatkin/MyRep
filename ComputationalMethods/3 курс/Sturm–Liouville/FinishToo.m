function y = TooFinish( n )

syms x;

for p = 1:1:n
    z(p) = 1;
end;

for p = 1:1:n
    for r = 1:1:n
        A(p,r) = Energ_scalar(p,r);
    end
end


for p = 1:1:10
    newZ = A\(z');
    oldZ = z;
    z = (newZ');
end;

disp('Минимальное собственное число');
disp(dot(z,oldZ) / dot(oldZ,oldZ));

disp('Собственная функция');
temp = 0;
for p = 1:1:n
    temp = temp + z(p) * W(p,x);
end
y = vpa(temp);


end

