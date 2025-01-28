clc
clear all
syms x y z
eq1=x+y-z==4;
eq2=2*x+3*y-2*z==4;
eq3=3*x+4*y+z==8;
sol=solve([eq1,eq2,eq3],[x,y,z]);
xSol=sol.x
ySol=sol.y
zSol=sol.z
A=[1 1 -1 4;2 3 2 4;3 4 1 8];
rref(A)
x=linspace(-2,2,20);
y=linspace(2,6,20);
[X,Y]=meshgrid(x,y);
Z=X+Y-4;
surf(X,Y,Z,'FaceColor','blue')
hold on
Z=(4-2*X-3*Y)/2;
surf(X,Y,Z,'FaceColor','red')
Z=8-3*X-4*Y;
surf(X,Y,Z,'FaceColor','green')
z=linspace(-2,2);
x=8+5*z;
y=-4-4*z;
plot3(x,y,z,'Linewidth',4,'Color','white')
hold off
hold off
rotate3d on