function Main
clear; syms x y;

%--------------Param--------------------
u_ex(x,y) = x * y^2 * (1 + y);
p(x,y) = 1 + x/2 + 0*y;
q(x,y) = 1 + 0*x + 0*y;
f(x,y) = -(diff(p(x,y)*(diff(u_ex(x,y),x)),x) + diff(q(x,y)*(diff(u_ex(x,y),y)),y));

N = 10;
kmax = 3*N;
eps = 0.0001;

%---------------------------------------
u_ex = matlabFunction(u_ex);
p = matlabFunction(p);
q = matlabFunction(q);
f = matlabFunction(f);

U_0 = Lattice(u_ex, N);

%Точное решение
[U_ex, U_ex_big] = Exact(u_ex, f, N);

%[U_big, k, pH] = OptimalParam(U_0, U_ex, F_ij, eps, N); 
%[U_big , k, pH] = Seidel(U_0, U_ex, F_ij, eps, N);
%[U_big , k, pH] = UpRelaxation(U_0, U_ex, F_ij, eps, N);  
[U_big] = ChangeDirect(U_0, U_ex, p,q,f, eps, kmax, N);  

disp('Мера аппроксимации ДУ разностной схемой на точном решении');
disp(Norm(L(p,q,f, U_ex, 1/N)));

disp('Норма невязки нулевого приближения');
disp(Norm(L(p,q,f, U_0, 1/N)));

disp('Решение на крупной сетке')
disp(U_big)

disp('Таблица точного решения на “крупной” сетке:')
disp(U_ex_big)

