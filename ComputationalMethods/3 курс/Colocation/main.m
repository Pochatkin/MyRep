
function y = main( k )

for p=1:1:k
    t(p) = cos(pi*(2*p-1)/2*k);
end

syms n x;


Lu(x) = (-(5-x)/(7-3*x) * diff(W(n,x),x,2) - (1-x)/2 * diff(W(n,x),x) + (1+1/2*sin(x))*W(n,x));
f(x) = 1/2 + x/2;

for r=1:1:k
    f1(r) = f(t(r));
end

for p =1:1:k
    for r=1:1:k
        n = r;
        Lu1(r,p) = Lu(t(p));
    end
end

y = Lu1\(f1');

end



