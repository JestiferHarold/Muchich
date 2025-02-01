x = 5;
y = 6;
disp(x + y);

row_vector = [1, 2, 3, 4, 5]; %row vector 
column_vector = [1; 2; 3; 4; 5]; %column vector

A = [1 2 3; 4 5 6; 7 8 9];
B = [10 11 12; 13 14 15; 16 17 18];
C = [10; 11; 12]; %non square matrix for pinv function

disp(A);
disp('Transpose of a ');
disp(A');
disp('Multiplying a and b');
disp(A * B);
disp('inverse of B');
disp(inv(B));
disp('Pseudo inverse of C');
disp(pinv(C));

x = 10; %for a if loop

if x > 5
    disp("what the hell");
else
    disp("Okay this shit is soo shit");
end