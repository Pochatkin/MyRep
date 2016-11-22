function [U_n, U_big] = Exact(u, f, N)
    xi = 0:(1/N):1;
    
    U_n = zeros(N+1);

    for i = 1 : N + 1
        for j = 1 : N + 1
            U_n(i,j) = u(xi(i),xi(j));
        end
    end
    
    if N ~= 5 
        U_big = Exact (u,f,5);
    else 
        U_big = U_n;
    end;
end
