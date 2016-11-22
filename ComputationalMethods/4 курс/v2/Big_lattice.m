function [Ai] = Big_lattice(W)
%N = 5, 10, 20
%TODO
    N = size(W,1) - 1;
    Ai = zeros(6);
    
    h = 1 / N;
    xi = 0:h:1;
    n = 1;
    m = 1;
    
    for i = 1 : N + 1
        for j = 1 : N + 1
            if (mod(xi(j),0.2) == 0) && (mod(xi(i),0.2) == 0)
                Ai(n,m) = W(j,i); n = n + 1; if mod(n,7) == 0
                    n = 1; m = m + 1;
                end
            end
        end
    end
    
end

