function y = alpha1(t)


syms x z;

temp = subs(diff(solution(x,z),x),x,0);
y =  subs(temp,z,t);

end

