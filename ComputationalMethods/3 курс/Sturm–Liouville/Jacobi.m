function y = Jacobi(n,x)
    if (n==0)
        y = 1;
    else
        if(n==1)
            y = x;
        else
            m = n -1;
            y = ((2*m+1)/(m+1) * x * Jacobi(n-1,x) - m/(m+1) * Jacobi(n-2,x));
        end;
    end;
end

