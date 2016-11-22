function [ t ] = Tau(p, q, h)
    hx = h; hy = h;
    lx = 1; ly = 1;
    
    c1b = p(0);     %min p
    c2b = p(1);     %max p
    d1b = q(0);     %min q
    d2b = q(1);     %max q
    
    d1 = c1b * 4 / hx^2 * (sin (pi * hx / 2 / lx)) ^ 2;
    D1 = c2b * 4 / hx^2 * (cos (pi * hx / 2 / lx)) ^ 2;
    d2 = d1b * 4 / hy^2 * (sin (pi * hy / 2 / ly)) ^ 2;
    D2 = d2b * 4 / hy^2 * (cos (pi * hy / 2 / ly)) ^ 2;
    
    t = 4/sqrt(min(d1,d2)*max(D1,D2));
end