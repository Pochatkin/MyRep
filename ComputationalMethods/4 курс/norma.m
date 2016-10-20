function z = norma( arr )

max = 0;

for i=1:1:10
    for j=1:1:10
        if abs(arr(i,j)) > max 
            max = abs(arr(i,j));
        end
    end
end

z=max;

end

