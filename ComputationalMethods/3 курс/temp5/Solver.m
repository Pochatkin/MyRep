function Solver( N,M, sigma )
syms x;
syms t;
u = 3*x*t+9*x+7*t;
[f,fi,psi,a,b] = First(u);
Uz = zeros(6,6);
disp('������� u - ������ �������');
disp(u);
disp('������� f(x,t)');
disp(f);
disp('������� fi(x)');
disp(fi);
disp('������� psi(x)');
disp(psi);
disp('������� alpha(t)');
disp(a);
disp('������� beta(t)');
disp(b);
if sigma == 0
    disp('������������ ������� � ����� ������� ����� ��� ���� =');
    disp(sigma);
    sch = 0;
    [B] = Lattice(f,fi,psi,a,b,N,M);
else [B] = LatSigma(f,fi,psi,a,b,N,M,sigma);
     sch = 1;
end
disp('������������ ������� � ����� ������� ����� ��� ���� =');
disp(sigma);
disp(B);
%������ �������
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
disp('������ ������� � ����� ������� �����');
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
         disp('����� �������� ������� ������� � ������������� �� ����� ������� �����');
         max1 = Norma(A, Uz);
         disp(max1);
         if s ~= 1
             disp('����� �������� ������������ ������� ����� � �����������');
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
    disp('����� �������� ������� ������� � ������������� �� ����� ������� �����');
    max1 = Norma(A, Uz);
    disp(max1);
    if s ~= 1
        disp('����� �������� ������������ ������� ����� � �����������');
        max2 = Norma(A, B);
        disp(max2);
    end
    B = A;
end
end


