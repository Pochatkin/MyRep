function [ f,fi,psi,alpha,beta ] = First( u )
syms x;
syms t;
f = diff(diff(u,t),t) - diff((x+2).*diff(u,x),x);
fi = subs(u,t,0);
psi = subs(diff(u,t),t,0);
alpha = subs(u,x,0);
beta = subs(u,x,1) + subs(diff(u,x),x,1);
end

