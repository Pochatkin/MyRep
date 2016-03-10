function y = main(n)

for p=1:1:n
    for r=1:1:n
        temp = Scalar(p,r);
        a(p,r) = temp;
    end
end


for p=1:1:n
   temp = Scalarf(p);
   b(p) = temp;
end

c = b';

y = a\c;



    
end

