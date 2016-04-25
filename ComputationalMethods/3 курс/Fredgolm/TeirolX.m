function y = TeirolX( n )

syms x; 

factorial = 1;
temp = 0;

for j = 1:1:n
    
    if(j == 1)
        temp = 0.5 + x;
    else
    factorial = factorial*j
    temp = temp + ((x-0.5)^j)/(factorial);
    end

end

y = temp;
disp(y);


end

