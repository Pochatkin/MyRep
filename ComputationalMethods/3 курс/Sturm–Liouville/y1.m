function z = y1( x )

u = 0.8004;

c = 4.85847;

temp = vpa(sqrt(1/10.4902)*(cos(u*x)+c*sin(u*x)));


z = temp;

end

