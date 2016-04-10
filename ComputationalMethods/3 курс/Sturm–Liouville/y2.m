function z = y2( x )

u = 1.9677;

c = 0.73684;

temp = vpa(sqrt(1/1.4601)*(cos(u*x)+c*sin(u*x)));

z = temp;

end

