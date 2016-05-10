function tochnoe(N,M)

h = 1/N;
tau = 1/M;

for i=1:1:N+1
    x(i) = (i - 1) * h;
    t(i) = (i - 1) * tau;
end

for i=1:N+1
    for j=1:N+1
        arr(i,j) = x(i)^2+t(j);
    end
end

disp(arr);

end

