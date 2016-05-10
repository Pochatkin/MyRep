function y = MechanicQuad3( t )


x(1) = (1 - sqrt(3/5))/2;
x(2) = 1/2;
x(3) = (1 + sqrt(3/5))/2;

A(1) = 5/18;
A(2) = 4/9;
A(3) = 5/18;

for i=1:1:3
    for j=1:1:3
        if(i == j) 
            D(i,j) = 1 - H(x(i),x(j)) * A(j);
        else
            D(i,j) = -H(x(i),x(j)) * A(j);
        end
    end
end

for i = 1:1:3
    b(i) = x(i) + 0.5;
end

z = D\(b');
temp = t + 0.5;

for i = 1:1:3
    temp = temp + H(t,x(i))*z(i) * A(i); 
end

y = temp;



end

