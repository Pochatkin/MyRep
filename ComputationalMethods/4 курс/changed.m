function  changed( N, M )


syms x  y;
eps = 0.05;


h_x = pi/N;
h_y = pi/M;

tau = 2 * h_x^2 / sin(pi * h_x);
A = tau / (2 * h_x^2);
C = A;
B = 2 * A + 1;

for i=1:1:N
    if i == 1
        x(i) = 0;
        y(i) = 0;
    else
        x(i) = i * h_x;
        y(i) = i * h_y;
    end
end

%u_arr = zeros(N,M);

for i=1:1:N
    for j=1:1:N
        u_arr(i,j) = 0;
    end
end

for i=1:1:N
    u_arr(i, 1) = u(x(i), 0);
    u_arr(i, N) = u(x(i), pi);
    u_arr(1, i) = u(0, y(i));
    u_arr(N, i) = u(pi, y(i));
end


for i=1:1:N
    for j=1:1:N
        u_tochnoe(i,j) = u(x(i), y(j));
        val_f(i,j) = f(x(i), y(j));
    end
end


%L1 = bigLambda1(u_arr, N, M, h_x);
%L2 = bigLambda2(u_arr, N, M, h_y);

new_u_arr = u_arr;
disp(u_arr);

disp('tau:');
disp(tau);
sigma = 8/(h_x)^2 * (sin((h_x)/2))^2;
delta = 8/(h_x)^2 * (cos((h_x)/2))^2;


disp('Мера аппроксимации');
%disp(eval(aprox(u_tochnoe, h_x, N, M)));

disp('Спектральный радиус');
%radius = (delta - sigma)/(delta + sigma);
%disp(radius);

%while(k == 0)
for k=1:1:10
    %For x
    G = setGx(u_arr, N, M, tau, h_x, val_f);
    disp('Gx');
    disp(eval(G));
    semi_u_arr = ProgonX(A, B, C, G, N);
    
    %For y
    G = setGy(semi_u_arr, N, M, tau, h_y, val_f);
    new_u_arr = ProgonY(A, B, C, G, M);
    %if(norma(new_u_arr - u_arr) < eps)
     %   k = k+1;
    %end
    u_arr = new_u_arr;
    disp(u_arr);
    disp('__________________________');
end

disp(new_u_arr);

end

