function y = ProgonX( A, B, C, G, n )

y = zeros(n, n);
s = zeros(n, 1);
t = zeros(n, 1);

for j = 2:1:n-1
    s(1) = C / B;
    t(1) = - G(1) / B;
   
    for i = 2:1:n-1
        s(i) = C/(B - A * s(i - 1));
        t(i) = (A * t(i - 1) - G(i)) / (B - A * s(i - 1));
    end
    
   % y(j,n) = t(n);

    for i = n-1 : (-1) : 2
        y(i - 1, j) = s(i - 1) * y(i,j) + t(i - 1);
    end
end

end

