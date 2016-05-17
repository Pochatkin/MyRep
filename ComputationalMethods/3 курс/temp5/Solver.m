function Solver( N,M, sigma )
syms x;
syms t;
u = 3*x*t+9*x+7*t;
[f,fi,psi,a,b] = First(u);
Uz = zeros(6,6);
disp('Функция u - точное решение');
disp(u);
disp('Функция f(x,t)');
disp(f);
disp('Функция fi(x)');
disp(fi);
disp('Функция psi(x)');
disp(psi);
disp('Функция alpha(t)');
disp(a);
disp('Функция beta(t)');
disp(b);
if sigma == 0
    disp('Приближенное решение в узлах крупной сетки для веса =');
    disp(sigma);
    sch = 0;
    [B] = Lattice(f,fi,psi,a,b,N,M);
else [B] = LatSigma(f,fi,psi,a,b,N,M,sigma);
     sch = 1;
end
disp('Приближенное решение в узлах крупной сетки для веса =');
disp(sigma);
disp(B);
%Точное решение
c = 0;
d = 0;
for i = 1 : 6
    for j = 1 : 6
        Uz(i,j) = subs(u, [t x], [c d]);
        d = d + 0.2;
    end
    d = 0;
    c = c + 0.2;
end
disp('Точное решение в узлах крупной сетки');
disp(Uz);
if sch == 1
     Ni = [5, 10, 20];
     Mi = 10;
     for s = 1 : 3
         h = 1 / Ni(s);
         disp('h =');
         disp(h);
         tau = 1 / Mi;
         disp('t =');
         disp(tau);
         [A] = LatSigma(f,fi,psi,a,b,Ni(s),Mi,sigma);
         disp('Норма разности точного решения и приближенного по узлам крупной сетки');
         max1 = Norma(A, Uz);
         disp(max1);
         if s ~= 1
             disp('Норма разности приближенных решений этого и предыдущего');
             max2 = Norma(A, B);
             disp(max2);
         end
         B = A;
     end
else 
Ni = [5, 10, 20];
Mi = [10, 20, 40];
for s = 1 : 3
    h = 1 / Ni(s);
    disp('h =');
    disp(h);
    tau = 1 / Mi(s);
    disp('t =');
    disp(tau);
    [A] = Lattice(f,fi,psi,a,b,Ni(s),Mi(s));
    disp('Норма разности точного решения и приближенного по узлам крупной сетки');
    max1 = Norma(A, Uz);
    disp(max1);
    if s ~= 1
        disp('Норма разности приближенных решений этого и предыдущего');
        max2 = Norma(A, B);
        disp(max2);
    end
    B = A;
end
end


