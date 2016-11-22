function [ Lu ] = L(p, q, f, U, h)

    xi = 0:h:1;
    yi = 0:h:1;
    
    %Lhu1 = @(i,j,w) p(xi(i)+h/2,yi(j))*(w(i+1,j)-w(i,j))/h^2 - p(xi(i)-h/2,yi(j))*(w(i,j)-w(i-1,j))/h^2;
    %Lhu2 = @(i,j,w) (w(i,j+1)-w(i,j))/h^2 - (w(i,j)-w(i,j-1))/h^2;
    %Lhu = @(i,j,w) Lhu1(i,j,w) + Lhu2(i,j,w);
    
    Lu = U;
    for i = 2 : size(U) - 1
        for j = 2 : size(U) - 1
            Lu(i,j) = 1 / h^2 * ( ...
                          p(xi(i)+h/2, xi(j)) * (U(i+1,j) - U(i,j)) ...
                        - p(xi(i)-h/2, xi(j)) * (U(i,j) - U(i-1,j)) ... 
                        + q(xi(i), xi(j)+h/2) * (U(i,j+1) - U(i,j)) ...
                        - q(xi(i), xi(j)-h/2) * (U(i,j) - U(i,j-1)) ...                      
                    ) + f(xi(i),xi(j));
        end
    end

%     for i = 2 : size(U) - 1
%         for j = 2 : size(U) - 1
%             Lu(i,j) = Lhu(i,j,U) + f(xi(i),yi(j));
%         end
%     end

end