function z = y( x )



u1 = 0.8004;
u2 = 1.9677;

c1 = 4.85847;
c2 = 0.73684;

temp(1) = vpa(sqrt(1/10.4902)*(cos(u1*x)+c1*sin(u1*x)));
temp(2) = vpa(sqrt(1/1.4601)*(cos(u2*x)+c2*sin(u2*x)));


z = temp;

end

