function G = setGy(u, N, M, tau, h, f)

L = bigLambda1(u, N, M, h);


for i = 2:1:N
    for j = 2:1:M
        G(i - 1,j - 1) = -u(i,j) - tau/2 * (L(i,j) + f(i,j));
    end
end
end

