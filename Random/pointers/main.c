#include <stdio.h>

void main(){

	int m[3];
	m[1] = 100;
	int *i = m;
	printf("%p",i);
	printf("\n%a\n",m);
	printf("\n");
}
