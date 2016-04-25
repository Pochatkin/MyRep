function G = Resolution( n )

syms x y;

f(y) = y + 1/2;

tempX = TeylorX(n-1,y);
tempY = TeylorY(n-1,y);
temp = 0;

for j=1:1:n
    for k=1:1:n
        eta(j,k) = int(tempY(j) * tempX(k),0,1);
        if(j == n)
            b(k) = int(tempY(j) * f(y),0,1);
        end
        if(j == k)
            A(j,k) = 1 - eta(j,k);
        else
            A(j,k) = - eta(j,k);
        end
    end
end

D = inv(A);

for j=1:1:n
    for k=1:1:n
        if(k==1)
            c(j) = D(j,k);
        else
            c(j) = c(j) + D(j,k)*b(k);
        end
    end
end

temp = 1/2 + x;
tempX = TeylorX(n-1,x);

for k=1:1:n
    temp = temp + c(k)*tempX(k);
end
G=temp;
disp(vpa(G));

end

