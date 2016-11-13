function G = setGx(u, N, M, tau, h, f)

L = bigLambda2(u, N, M, h);


for i = 2:1:N
    for j = 2:1:M-1
        G(i - 1,j - 1) = -u(i,j) - tau/2 * (L(i,j) + f(i,j));
    end
end
        

end

