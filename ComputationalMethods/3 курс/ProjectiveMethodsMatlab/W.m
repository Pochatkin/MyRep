function y = W( i, x )
if(i == 1) 
    y = x^2 + 2*x - 13;
else
    if(i == 2)
        y = 2*(x^2 + 2 * x - 13);
    else
        y = (1-x^2)^2 * Jacobi(i,1,x);
    end;
end;

end

