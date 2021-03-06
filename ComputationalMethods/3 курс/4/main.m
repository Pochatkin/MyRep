function y = main(N,M)
%u_tt = cos(x)*u_xx + f(x,t)
%u = t + x^2;  f(x,t) = -2 * cos(x);
%phi(x) = x^2;  psi(x) = 1;  alpha(t) = 0;  beta(t) = 1+t;
% ������������ N >= M
% N = M = 20;



h = 1/N;
tau = 1/M;




for i=1:1:N+1
    x(i) = (i - 1) * h;
    t(i) = (i - 1) * tau;
end

for i=1:1:N+1
    u_arr(i,1) = Phi1(x(i)); 
    u_arr(i,2) = tau*Psi1(x(i)) + u_arr(i,1);
end

for i=2:N
    for k=2:M
        u_arr(i,k+1) = 2*u_arr(i,k) - u_arr(i,k-1) + tau^2 *(cos(x(i)) * (u_arr(i+1,k) - 2*u_arr(i,k) + u(i-1,k))/h^2 - 2 * cos(x(i)));
    end
end

for k = 1:1:M+1
    u_arr(N+1,k) = beta1(t(k));
end

for k = 1:1:M+1
    u_arr(1,k) = (4*u_arr(2,k) - u_arr(3,k))/3; 
end

y = u_arr;

end

