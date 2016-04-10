function y = func( x )

a(1,1) = 0.85*cos(x)-x*sin(x);
a(1,2) = x*cos(x)+0.85*sin(x);
a(2,1) = x*sin(x)-0.8*cos(x);
a(2,2) = x*cos(x)+0.8*sin(x);

b(1)=0;
b(2)=0;

y=a\(b');
disp(y);
end

