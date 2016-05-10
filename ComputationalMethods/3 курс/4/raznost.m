function raznost( N,M )

if(N>M)
    k = N/M;
    small = main(M,M);
    big = main(N,N);
else
    k = M/N;
    small = main(N,N);
    big = main(M,M);
end


h1=0;
h2=0;

for i=1:k
    for j=1:k
        temp_arr(i,j) = big(i+(k-1)*h1,j+(k-1)*h2);
        h2 = h2 + 1;
    end
    h1 = h1 + 1;
    h2 = 0;
end
disp(temp_arr);
disp(big);
disp(small);
disp(abs(temp_arr-small));

end

