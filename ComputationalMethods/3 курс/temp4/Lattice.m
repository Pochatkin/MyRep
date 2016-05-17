function [A ] = Lattice( f,fi,psi,alpha,b,N,M )
syms x;
syms t;
a = cos(x);
T = 1;
A = zeros(6, 6);
xi = zeros(1, N + 1);
ti = zeros(1, M + 1);
%Матрица значений функции f(x,t)
zf = zeros(M + 1, N + 1);
h = 1 / N;
tau = T / M;
w = zeros(M + 1, N + 1);
for i = 0 : N
    xi(i + 1) = i * h;
end
for i = 0 : M
    ti(i + 1) = i * tau;
end
%zf - матрица значений f(x,t)
for i = 1 : N + 1
    for k = 1 : M + 1
        zf(k,i) = subs(f, [t x], [ti(k) xi(i)]);
    end
end

for i = 1 : N + 1
    w(1,i) = subs(fi,x,xi(i));
    w(2,i) = tau * subs(psi,x,xi(i)) + w(1,i);
end

for k = 2 : M
    for i = 2 : N
        Ln = subs(a,x,xi(i)) / h^2 * (w(k,i+1) - 2 * w(k,i) + w(k,i-1));
        w(k+1,i) = (Ln + zf(k,i)) * tau^2 + 2 * w(k,i) - w(k-1,i);
    end
    %b1 = 1, b2 = 0
    w(k+1,N+1) = subs(b,t,ti(k+1)); %(2 * h * subs(b, t, ti(k+1)) + 4 * w(k+1,N) - w(k+1,N-1)) / (2 * h + 3);
end
for k = 3 : M + 1
    w(k,1) =  (- subs(alpha,t,ti(k)) * h * 2 + 4*w(k,2) - w(k,3))/3;
end

n = 1;
m = 1;
for i = 1 : N + 1
    for j = 1 : M + 1
        if (mod(ti(j),0.2) == 0) && (mod(xi(i),0.2) == 0)
            A(n,m) = w(j,i);
            n = n + 1;
            if mod(n,7) == 0
                n = 1;
                m = m + 1;
            end
        end
    end
end
end

