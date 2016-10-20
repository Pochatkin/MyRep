function simpleIter( N, M )

syms x  y;
eps = 0.05;

h_x = pi/N;
h_y = pi/M;


for i=1:1:N
    if i == 1
        x(i) = 0;
        y(i) = 0;
    else
        x(i) = i * h_x;
        y(i) = i * h_y;
    end
end




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
    end
end


sigma = 8/(h_x)^2 * (sin((h_x)/2))^2;
delta = 8/(h_x)^2 * (cos((h_x)/2))^2;


disp('Мера аппроксимации');
disp(eval(aprox(u_tochnoe, h_x, N, M)));

disp('Спектральный радиус');
radius = (delta - sigma)/(delta + sigma);
disp(radius);


start_norma = norma(u_tochnoe - u_arr);

new_u_arr = u_arr;

k = 1;

disp('Итерация ');
disp(k);
disp('||F-AU(k)||');
disp(eval(aprox(new_u_arr, h_x, N, M)));
disp('||U(k) - u*||');
disp(eval(norma(new_u_arr - u_tochnoe)));
disp('||U(k) - U(k-1)||');
raznicha = (norma(new_u_arr - u_arr));
disp(raznicha);
disp('_________________________________');
disp('');

for i=2:1:N-1
    for j=2:1:N-1
        new_u_arr(i, j) = (u_arr(i-1, j) + u_arr(i+1, j) + u_arr(i, j-1) + u_arr(i, j+1) + h_x^2 * f(x(i),y(j))) / 4; 
    end
end

k = k + 1;

while((norma(new_u_arr - u_tochnoe) / start_norma) > eps)
    disp('Итерация ');
    disp(k);
    disp('||F-AU(k)||');
    disp(eval(aprox(new_u_arr, h_x, N, M)));
    disp('||U(k) - u*||');
    disp(eval(norma(new_u_arr - u_tochnoe)));
    disp('||U(k) - U(k-1)||');
    raznicha = (norma(new_u_arr - u_arr));
    disp(raznicha);
    disp('_________________________________');
    disp('');
    u_arr = new_u_arr;
    for i=2:1:N-1
        for j=2:1:N-1
            new_u_arr(i, j) = (u_arr(i-1, j) + u_arr(i+1, j) + u_arr(i, j-1) + u_arr(i, j+1) + h_x^2 * f(x(i),y(j))) / 4; 
        end
    end
    k=k+1;
end
disp('Апостариорная оценка');
disp(radius * raznicha / (1 - radius));

print(new_u_arr);


end

