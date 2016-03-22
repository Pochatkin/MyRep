function y = W( i, x )
if(i == 1) 
    y = x^2 + 2*x - 17/3;
else
    if(i == 2)
        y = x^3-3*x+2;
    else
        y = (1-x^2)^2 * Jacobi(i,1,x);
    end;
end;

end

