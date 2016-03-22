function y = main(n)

for p=1:1:n
    for r=1:1:n
        a(r,p) = Scalar(r,p);
    end
end


for p=1:1:n
   b(p) = Scalarf(p);
end


y = (a')\(b');

temp = 0;
for r=1:1:n
    temp = temp + y(r)*W(r,-1/2);
end

disp(temp)

temp = 0;
for r=1:1:n
    temp = temp + y(r)*W(r,0);
end

disp(temp)

temp = 0;
for r=1:1:n
    temp = temp + y(r)*W(r,1/2);
end

disp(temp)

end

