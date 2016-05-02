function  Norma(n,x)


n = n + 1;
syms y;

tempX = TeylorX(n-1,x);
tempY = TeylorY(n-1,y);
temp = 0;

for j=1:1:n
    temp = temp + tempX(j)*tempY(j);
   
end

disp(vpa(int(abs(H(x,y) - temp),0,1)));
end

