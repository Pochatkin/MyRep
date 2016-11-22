function [norm] = Norm(A)
    norm = abs(A(1,1));
    for i = 2 : size(A)-1
        for j = 2 : size(A)-1
            norm = max(norm, abs(A(i,j)));
        end
    end
end
