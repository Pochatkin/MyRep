function y = Psi1(x)

syms z t;

temp = subs(diff(solution(z,t),t),t,0);
y =  subs(temp,z,x);

end

