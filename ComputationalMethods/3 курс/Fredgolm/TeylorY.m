function z = TeylorY( n,y )


factorial = 1; 

for j = 1:1:n+1
    
    if(j == 1)
        temp(j) = 1*y^0;
    else
    factorial = factorial*(j-1);
    temp(j) = ((y)^(j-1))/(factorial);
    end

end

z = temp;


end

