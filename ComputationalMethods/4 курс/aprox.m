function z = aprox (u_input, h, N, M)


max = 0;

for i=1:1:N
    if i == 1
        x(i) = 0;
        y(i) = 0;
    else
        x(i) = i * h;
        y(i) = i * h;
    end
end

for i = 2:1:N-1
    for j = 2:1:M-1
        if(abs(Lh(u_input, i, j, h) + f(x(i), y(j))) > max)
            max = abs(Lh(u_input, i, j, h) + f(x(i), y(j)));
        end
    end
end

z = max;


end

