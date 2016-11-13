function y = bigLambda2( u, N, M, h )


for i = 2:1:N-1
    for j = 2:1:M-1
        y(i,j) = (u(i,j+1) - 2 * u(i,j) + u(i,j-1)) / h^2;
    end
end


end

