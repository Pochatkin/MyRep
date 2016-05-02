function z = TeylorY( n,y )


for j = 1:1:n+1
    
    if(j == 1)
        temp(j) = 1*y^0;
    else
    temp(j) = ((y^2)^(j-1));
    end

end

z = temp * (-0.5);


end

