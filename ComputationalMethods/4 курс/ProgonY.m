function y = ProgonY(A, B, C, G, n)
y = zeros(n, n);
s = zeros(n, 1);
t = zeros(n, 1);

for i = 2:1:n-1
    s(1) = C / B;
    t(1) = - G(1) / B;
    for j = 2:1:n-1
        s(j) = C/(B - A * s(j - 1));
        t(j) = (A * t(j - 1) - G(j))/(B - A * s(j - 1));    
    end


    for j = n-1 : (-1) : 2
        y(i, j - 1) = s(j - 1) * y(i,j) + t(j - 1);
    end
end

end

