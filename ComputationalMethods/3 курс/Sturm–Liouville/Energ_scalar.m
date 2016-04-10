function y = Energ_scalar(i, j)

syms x;





if(i == 1)
    if(j ==1)
        y = sqrt(0.5)*2 + 0.8*p(-1)*W(i,-1)*W(j,-1) + 0.85*p(1)*W(i,1)*W(j,1);
    else
        g = W(i,x)*W(j,x);
        y = int(g,-1,1) + 0.8*p(-1)*W(i,-1)*W(j,-1) + 0.85*p(1)*W(i,1)*W(j,1);
    end
else
    g = W(i,x)*W(j,x);
    y = int(g,-1,1) + 0.8*p(-1)*W(i,-1)*W(j,-1) + 0.85*p(1)*W(i,1)*W(j,1);
end


end

