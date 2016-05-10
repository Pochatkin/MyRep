function y = TeylorX( n,x )


factorial = 1;

for j = 1:1:n+1
    
    if(j == 1)
        temp(j) = 1*x^0;
    else
        factorial = factorial*(j-1);
        temp(j) = ((x-0.5)^(j-1))/(factorial);
    end

end

y = temp;


end

