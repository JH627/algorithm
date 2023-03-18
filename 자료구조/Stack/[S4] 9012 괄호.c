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

int empty(StackType* s){
	return (s->curidx != -1);
}

void push(StackType* s1, int n){
	if (isfull(s1)){
		s1->capacity += INCREASE_SIZE;
		s1->data = (element*) realloc(s1->data, s1->capacity * sizeof(element));
	}
	s1->data[++(s1->curidx)] = n;	
}

int pop(StackType* s1){
	if (s1->curidx == -1){
		return -1;
	}
	else{
		return (s1->data[(s1->curidx)--]);
	}
}

int VPS(StackType* s, char PS[]){
	int len = strlen(PS);
	for(int i = 0; i < len; i++){
		if (PS[i] == '('){
			push(s, 0);	
		}
		else{
			if(pop(s)){
				return 1;
			}
		}
	}
	if(empty(s)){
		return 1;
	}
	return 0;
}

int main(void){

	StackType *s;
	s = (StackType*) malloc(sizeof(StackType));
	init(s);
	
	int n, result;
	scanf("%d", &n);
	char PS[50];
	
	for(int i = 0; i < n; i++){
		scanf("%s", &PS);
		result = VPS(s, PS);
		printf("%s", (!result) ? "YES\n" : "NO\n");
		init(s);
	}
	
	free(s);
	return 0;
}
