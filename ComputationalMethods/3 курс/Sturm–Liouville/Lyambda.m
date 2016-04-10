function z = Lyambda(  )

syms x;


u1 = 0.8004;
u2 = 1.9677;

c1 = 4.85847;
c2 = 0.73684;

temp = vpa(int((sqrt(1/10.4902)*(cos(u1*x)+c1*sin(u1*x)))^2,-1,1) + 0.8*p(-1)*y1(-1)*y1(-1) + 0.85*p(1)*y1(1)*y1(1));
lyambda(1) = temp;
temp = vpa(int((sqrt(1/1.4601)*(cos(u2*x)+c2*sin(u2*x)))^2,-1,1) + 0.8*p(-1)*y2(-1)*y2(-1) + 0.85*p(1)*y2(1)*y2(1));
lyambda(2) = temp;

disp('lyambda1: ');
disp(lyambda(1));
disp(vpa(sqrt(1/10.4902)*(cos(u1*x)+c1*sin(u1*x))));
disp('lyambda2: ');
disp(lyambda(2));
disp(vpa(sqrt(1/1.4601)*(cos(u2*x)+c2*sin(u2*x))));

z = lyambda;

end

