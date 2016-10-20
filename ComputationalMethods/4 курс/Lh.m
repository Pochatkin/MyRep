function z = Lh(u_input, i, j, h)

z = (u_input(i+1,j) + u_input(i-1,j) + u_input(i,j+1) + u_input(i,j-1) - 4 * u_input(i,j)) / h^2;

end

