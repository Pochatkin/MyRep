function [ f,fi,psi,alpha,beta ] = First( u )
syms x;
syms t;
f = diff(diff(u,t),t) - diff(diff(u,x),x)*cos(x);
fi = subs(u,t,0);
psi = subs(diff(u,t),t,0);
alpha = subs(diff(u,x),x,0);
beta = subs(u,x,1);
end

