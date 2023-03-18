#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define FIRST_SIZE 50
#define INCREASE_SIZE 10

typedef int element;

typedef struct {
	element* data;
	int capacity;
	int curidx;
} StackType;

void init(StackType *s1){
	s1->capacity = FIRST_SIZE;
	s1->curidx = -1;
	s1->data = (element*)malloc(s1->capacity * sizeof(element));
}

int isfull(StackType* s1){
	return (s1->curidx == (s1->capacity - 1));	
}

void push(StackType* s1, int n){
	if (isfull(s1)){
		s1->capacity += INCREASE_SIZE;
		s1->data = (element*) realloc(s1->data, s1->capacity * sizeof(element));
	}
	s1->data[++(s1->curidx)] = n;	
}

void pop(StackType* s1){
	if (s1->curidx == -1){
		printf("-1\n");
	}
	else{
		printf("%d\n", s1->data[(s1->curidx)--]);
	}
}

void size(StackType* s1){
	printf("%d\n", s1->curidx + 1);
}

void empty(StackType* s1){
	printf("%d\n", (s1->curidx == -1) ? 1 : 0);
}

void top(StackType* s1){
	printf("%d\n", (s1->curidx == -1) ? -1 : s1->data[s1->curidx]);
}

int main(void){

	StackType *s;
	s = (StackType*) malloc(sizeof(StackType));
	init(s);
	
	int n, num;
	scanf("%d", &n);
	char command[10];
	
	for (int i = 0; i < n; i++){
		scanf("%s", &command);
		if (strcmp(command,"push") == 0){
			scanf("%d", &num);
			push(s,num);
		}
		else if (strcmp(command,"pop") == 0){
			pop(s);
		}
		else if(strcmp(command,"size") == 0){
			size(s);
		}
		else if(strcmp(command,"empty") == 0){
			empty(s);
		}
		else if(strcmp(command,"top") == 0){
			top(s);
		}
	}
	free(s);
	return 0;
}
