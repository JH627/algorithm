#include <stdio.h>
#include <stdlib.h>
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

int pop(StackType* s1){
	return (s1->data[(s1->curidx)--]);
}

int size(StackType* s1){
	return (s1->curidx + 1);
}

int main(void){

	StackType *s;
	s = (StackType*) malloc(sizeof(StackType));
	init(s);
		
	int n, num;
	int ans = 0;
	scanf("%d", &n);
	
	for(int i = 0; i < n; i++){
		scanf("%d", &num);
		if(num != 0){
			push(s, num);
		}
		else{
			pop(s);
		}
	}
	
	int j = size(s);
	for(int i = 0; i < j; i++){
		ans += pop(s);
	}
	
	printf("%d\n", ans);
	free(s);
	return 0;
}
