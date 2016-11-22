function w = Lattice( u, N )
    syms x y;
    xi = 0:(1/N):1;
    
    w = zeros(N+1);
    for i = 1 : N + 1
        w(i,1) = u(xi(i),0);
        w(1,i) = u(0,xi(i));
        w(N+1,i) = u(1,xi(i));
        w(i,N+1) = u(xi(i),1);
    end
end