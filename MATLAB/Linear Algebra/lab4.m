% % The right slash / => conventinal method, 8 / 4 = 2, A / B = A * inv(B)
% % The left slash \ => psycopathic method, 8\4 => 4 / 8, A \ B = inv(A) * B
% % clc 
% % clear all
% % 
% % A = [1 1 1; 2 2 2; 3 3 3];
% % B = [2 2 2; 3 3 3; 4 4 4];
% % 
% % f = 8 / 4;
% % disp(f);
% % 
% % disp(A / B);
% % 
% % A = [1 1 1; 2 2 2; 3 3 3];
% % B = [2 2 2; 3 3 3; 4 4 4];
% % 
% % f = 8 \ 4;
% % disp(f);
% % 
% % disp(A \ B);
% % 
% % clear all
% % clc
% 
% %2 divisions
% %right division represented by slash(/)
% %a/b=a*inv(b)
% %left division (\)
% %a\b=inv(a)*b
% 
% % A=[1 2;2 3];
% % B=[2 4;6 8];
% % x=A/B;
% % disp(x);
% 
% %A = [1,2;3,4;5,6];
% %leftinv = inv(A' * A) * (A');
% %display(leftinv);
% 
% %rightinv= (A')* inv(A*A');
% % % disp(rightinv);
% 
% A = [1, 2;
%     3, 4;
%     5, 6];
% 
% Apseudo = pinv(A);
% disp(Apseudo);
% 
% A = [2 1; 1 -1; 1 3];
% B = [10; 3; 2];
% 
% solution = A \ B;
% solution2 = lsqr(A, B);
% disp(solution);
% disp(solution2);
% 
% %Write a program on scatter plot of the span
% 
% v1 = [1, 0];
% v2 = [2, 1];
% 
% %Create a grid of scalar values for linear combinations
% c1 = linspace(-5, 5, 100); %Range
% c2 = linspace(-5, 5, 100); %Range
% 
% %Create a mesh grid to generate linear combination
% 
% [c1, c2] = meshgrid(c1, c2);
% %Compute 
% x = c1 .* v1(1) + c2 .* v2(1);
% y = c1 .* v1(2) + c2 .* v2(2);
% 
% figure;
% scatter(x(:), y(:), 10, 'filled');
% xlabel('x');
% ylabel('y');
% title('Scatter plot of the span of vectors v1 and v2');
% axis equal;
% grid on;
% 
% v1 = [1 0 0];
% v2 = [0 1 0];
% v3 = [0 0 1];
% 
% c1 = linspace(-5, 5, 100);
% c2 = linspace(-5, 5, 100);
% c3 = linspace(-5, 5, 100);
% 
% [c1, c2, c3] = meshgrid(c1, c2, c3);
% x = c1 .* v1(1) + c2 .* v2(1) + c3 .* v3(1);
% y = c1 .* v1(2) + c2 .* v2(2) + c3 .* v3(2);
% z = c1 .* v1(3) + c2 .* v2(3) + c3 .* v3(3);
% figure;
% scatter(x(:), y(:), z(:), 10, 'filled');
% xlabel('x');
% ylabel('y');
% zlabel('z');
% axis equal;
% grid on;
% 
% V = [1 0 0; 0 1 0; 0 0 1]; 
% 
% c = linspace(-5, 5, 100);
% [C1, C2, C3] = ndgrid(c, c, c); 
% 
% points = V' * [C1(:) C2(:) C3(:)]'; 
% x = points(1, :);
% y = points(2, :);
% z = points(3, :);
% 
% figure;
% scatter3(x, y, z, 5, 'filled'); 
% xlabel('x');
% ylabel('y');
% zlabel('z');
% axis equal;
% grid on;
% title('3D Lattice of Points');




v1=[1 0 0];
v2=[0 1 0];
v3=[0 0 1];
c1=linspace(-5,5,100);
c2=linspace(-5,5,100);
c3=linspace(-5,5,100);
[c1,c2,c3]=meshgrid(c1,c2,c3);
x=c1.*v1(1)+c2.*v2(1)+c3.*v3(1);
y=c1.*v1(2)+c2.*v2(2)+c3.*v3(2);
z=c1.*v1(3)+c2.*v2(3)+c3.*v3(3);
figure;
scatter3(x(:),y(:),z(:),10,'filled');