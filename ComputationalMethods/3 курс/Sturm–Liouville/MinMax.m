function z = MinMax(  )

syms l;


z = dsolve('-3/5 * D2y + e^(-1/5) * y - l * y = 0','Dy(-1) = 0.8* y(-1)','Dy(1) = -0.85*y(1)');

disp(z);
end

