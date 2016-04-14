function z = LyambdaMin(  )

syms x;


u1 = 0.8004;
u2 = 1.9677;

c1 = 4.85847;
c2 = 0.73684;

temp(1) = vpa(int((sqrt(1/10.4902)*(cos(u1*x)+c1*sin(u1*x)))^2,-1,1) + 0.8*p(-1)*y1(-1)*y1(-1) + 0.85*p(1)*y1(1)*y1(1));
lyambdaMin(1) = u1^2 * p(1) + q(-1); 
temp(2) = vpa(int((sqrt(1/1.4601)*(cos(u2*x)+c2*sin(u2*x)))^2,-1,1) + 0.8*p(-1)*y2(-1)*y2(-1) + 0.85*p(1)*y2(1)*y2(1));
lyambdaMin(2) = u2^2 * p(1) + q(-1);

lyambdaMax(1) = u1^2 * p(-1) + q(1);
lyambdaMax(2) = u2^2 * p(-1) + q(1);



disp('lyambda1: ');
disp(lyambdaMin);
disp(vpa(sqrt(1/10.4902)*(cos(u1*x)+c1*sin(u1*x))));
disp('lyambda2: ');
disp(lyambdaMax);
disp(vpa(sqrt(1/1.4601)*(cos(u2*x)+c2*sin(u2*x))));
disp(temp);

z = lyambdaMin;

end

