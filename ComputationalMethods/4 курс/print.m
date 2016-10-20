function print( u_arr )

for i=1:1:5
    for j=1:1:5
        if j == 1 || i == 1
            if i == 1 && j == 1
                temp(i, j) = u_arr(i, j);
            else if j == 1
                    temp(i, j) = u_arr(i*2, j);
                else if i == 1
                        temp(i, j) = u_arr(i, j*2);
                    end
                end
            end
        else 
            temp(i, j) = u_arr(i * 2, j * 2);
        end
    end
end
disp(temp);

end

