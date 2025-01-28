% 
% 
% clc 
% clear all
% 
% A = [1 1 1 3; 2 -1 1 4; 1 3 2 7];
% rref(A)
% 
% clc 
% clear all
% A = [1 1 -1 4; 2 3 2 4; 3 4 1 8];
% 
% syms x y z
% eqn1 = x + y - z == 4;
% eqn2 = 2 * x + 3 * y + 2 * z == 4;
% eqn3 = 3 * x + 4 * y + z == 8;

rref(A)

x = linspace(-2, 2, 20);
y = linspace(2, 6, 20);
[x , y] = mes
z = x + y - 4;
surf()