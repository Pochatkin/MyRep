function max = Norma( A, U )
max = -999;
for i = 1 : 6
    for j = 1 : 6
        %disp(abs(A(i,j) - U(i,j)));
        if abs(A(i,j) - U(i,j)) > max
            max = abs(A(i,j) - U(i,j));
            l = i;
            k = j;
        end
    end
end
end

