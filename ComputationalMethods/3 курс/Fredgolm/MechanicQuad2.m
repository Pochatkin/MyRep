function y = MechanicQuad2(t)



x(1) = (1 - 1/sqrt(3))/2;
x(2) = (1 + 1/sqrt(3))/2;

A(1) = 1/2;
A(2) = 1/2;

for i=1:1:2
    for j=1:1:2
        if(i == j) 
            D(i,j) = 1 - H(x(i),x(j)) * A(j);
        else
            D(i,j) = -H(x(i),x(j)) * A(j);
        end
    end
end

for i = 1:1:2
    b(i) = x(i) + 0.5;
end

disp(D);
disp(b);

z = D\(b');

temp = t + 0.5;

for i = 1:1:2
    temp = temp + H(t,x(i))*z(i) * A(i); 
end

y = temp;

end

