function [ A, iter ] = ChangeDirect(U_0, U_ex, p,q,f, eps, kmax, N)
    format short;
    
    h = 1/N;
    tau = Tau(p,q,h);
    
    xi = 0:h:1;
    yi = 0:h:1;
    
    Lhu1 = @(i,j,w) p(xi(i)+h/2,yi(j))*(w(i+1,j)-w(i,j))/h^2 - p(xi(i)-h/2,yi(j))*(w(i,j)-w(i-1,j))/h^2;
    Lhu2 = @(i,j,w) (w(i,j+1)-w(i,j))/h^2 - (w(i,j)-w(i,j-1))/h^2;

    Axij = @(i,j)     tau / 2 / h^2 * p(xi(i)-h/2, yi(j));
    Bxij = @(i,j) 1 + tau / 2 / h^2 *(p(xi(i)-h/2, yi(j)) + p(xi(i)+h/2, yi(j)));
    Cxij = @(i,j)     tau / 2 / h^2 * p(xi(i)+h/2, yi(j));
    Ayij = @(i,j)     tau / 2 / h^2 * q(xi(i), yi(j)-h/2);
    Byij = @(i,j) 1 + tau / 2 / h^2 *(q(xi(i), yi(j)-h/2) + q(xi(i), yi(j)+h/2));
    Cyij = @(i,j)     tau / 2 / h^2 * q(xi(i), yi(j)+h/2);
    
    G = zeros(N-1); s = zeros(N+1,1); t = zeros(N+1,1);
    
    Z = U_0;
    U_k = U_0;
    U_k1 = U_0;
    iter = 0;

    while Norm(U_k1 - U_ex)/Norm(U_0 - U_ex) >= eps && iter < kmax        
        iter = iter + 1;
        
        for j = 2 : N
            B0 = - 1;
            G0 = U_0(1, j);
            s(1) = 0;
            t(1) = - G0 / B0;
            for i = 2 : N
                G(i-1,j-1) = - U_k1(i, j) - tau/2 * (Lhu2(i,j,Z) + f(xi(i),xi(j)));
                s(i) = Cxij(i,j) / (Bxij(i,j) - Axij(i,j) * s(i-1));
                t(i) = (Axij(i,j) * t(i-1) - G(i-1, j-1)) / (Bxij(i,j) - Axij(i,j) * s(i-1));
            end
            for i = N : -1 : 2
                U_k1(i, j) = s(i) * U_k1(i + 1, j) + t(i);
            end
        end
        
        Z = U_k1;
        
        for i = 2 : N
            B0 = - 1;
            G0 = U_k1(i, 1);
            s(1) = 0;
            t(1) = - G0 / B0;
            for j = 2 : N
                G(i-1,j-1) = - U_k1(i, j) - tau/2 * (Lhu1(i,j,Z) + f(xi(i),xi(j)));
                s(j) = Cyij(i,j) / (Byij(i,j) - Ayij(i,j) * s(j-1));
                t(j) = (Ayij(i,j) * t(j-1) - G(i-1,j-1)) / (Byij(i,j) - Ayij(i,j) * s(j-1));
            end
            for j = N : -1 : 2
                U_k1(i, j) = s(j) * U_k1(i, j + 1) + t(j);
            end
        end
        
        mytable(iter,1) = iter;
        mytable(iter,2) = Norm(L(p,q,f, U_k1, h));
        mytable(iter,3) = Norm(L(p,q,f, U_k1, h))/Norm(L(p,q,f, U_0, h));
        mytable(iter,4) = Norm(U_k1 - U_ex);
        mytable(iter,5) = Norm(U_k1 - U_ex)/Norm(U_0 - U_ex);
        mytable(iter,6) = Norm(U_k1 - U_k);
        
        U_k = U_k1;
        Z = U_k1;
    end
    
    disp('      k    ||L(Uk)+F||   rel.d  ||Uk-u*||  rel.err  ||Uk - Uk1||');
    disp(mytable);
 
    A = Big_lattice(U_k);
end