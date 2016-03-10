function y = Jacobi(n,k,x)
    if (n==0)
        y = 1;
    else
        if(n==1)
            y = (k+1)*x;
        else
            y = ((n+k+2)*(2*n+2*k+3)*x*Jacobi(n-1,k,x) - (n+k+2)*(n+k+1)*Jacobi(n-2,k,x))/((n+2*k+2)*(n+2));
        end;
    end;
end

