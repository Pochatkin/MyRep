function [y] = Progon(A, B, C, G, n)
y = zeros(1, n);
s = zeros(1, n);
t = zeros(1, n);
z = zeros(1, n - 2);
s(1) = C(1) / B(1);
t(1) = - G(1) / B(1);
for i = 2 : n
    s(i) = C(i)/(B(i) - A(i) * s(i - 1));
    t(i) = (A(i) * t(i - 1) - G(i))/(B(i) - A(i) * s(i - 1));    
end
y(n) = t(n);
for i = n : (-1) : 2
    y(i - 1) = s(i - 1) * y(i) + t(i - 1);
end
for i = 1 : n - 1
    z(i) = y(i) / 2 + y(i + 1) / 2;
end
end

