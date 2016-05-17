function [ Ai ] = LatSigma( f,fi,psi,a,b,N,M,sigma )
syms x;
syms t;
p = x + 2;
T = 1;
Ai = zeros(6, 6);
xi = zeros(1, N + 1);
ti = zeros(1, M + 1);
%Матрица значений функции f(x,t)
zf = zeros(M + 1, N + 1);
h = 1 / N;
tau = T / M;
w = zeros(M + 1, N + 1);
G = zeros(1, N + 1);
A = zeros(1, N + 1);
B = zeros(1, N + 1);
C = zeros(1, N + 1);
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
A(1) = 0;
B(1) = -1;
C(1) = 0;

A(N + 1) = -1;
B(N + 1) = - h - 1;
C(N + 1) = 0;

for k = 2 : M
    for i = 2 : N
        G(1) = subs(a,t,ti(k+1));
        G(N + 1) = subs(b,t,ti(k+1)) * h;
        Ln1 =  subs(p,x,xi(i)+h*1/2) / h^2 * (w(k,i+1) - w(k,i)) - subs(p,x,xi(i)-h*1/2) / h^2 * (w(k,i) - w(k,i-1));
        Ln2 =  subs(p,x,xi(i)+h*1/2) / h^2 * (w(k-1,i+1) - w(k-1,i)) - subs(p,x,xi(i)-h*1/2) / h^2 * (w(k-1,i) - w(k-1,i-1));
        G(i) = (-2 * w(k,i) + w(k-1,i)) / tau^2 - (1 - 2 * sigma) * Ln1 - sigma * Ln2 - zf(k,i);
        A(i) = sigma * subs(p,x,xi(i)-h*1/2) / h^2;
        B(i) = (sigma * subs(p,x,xi(i)+h*1/2) + sigma * subs(p,x,xi(i)-h*1/2)) / h^2 + 1 / tau^2;
        C(i) = sigma * subs(p,x,xi(i)+h*1/2) / h^2;
    end
    z = Progon(A, B, C, G, N + 1);
    w(k + 1,:) = z;
end

%V eto ya veryu
n = 1;
m = 1;
for i = 1 : N + 1
    for j = 1 : M + 1
        if (mod(ti(j),0.2) == 0) && (mod(xi(i),0.2) == 0)
            Ai(n,m) = w(j,i);
            n = n + 1;
            if mod(n,7) == 0
                n = 1;
                m = m + 1;
            end
        end
    end
end
end

