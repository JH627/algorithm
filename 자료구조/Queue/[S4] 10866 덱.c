#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_QUEUE_SIZE 10001

typedef int element;

typedef struct{
	element data[MAX_QUEUE_SIZE];
	int front;
	int rear;	
} dequeType;

void init(dequeType* d){
	d->front = 0;
	d->rear = 0;
}

int curque(int n){
	return (n == MAX_QUEUE_SIZE - 1) ? 0 : n + 1;
}

int Rcurque(int n){
	return (n == 0) ? MAX_QUEUE_SIZE - 1 : n - 1;
}

int isfull(dequeType *d){
	return (curque(d->rear) == d->front);
}

int isempty(dequeType *d){
	return d->front == d->rear;
}

void push_front(dequeType *d, int x){
	if(!isfull(d)){
		d->data[d->front] = x;
		d->front = Rcurque(d->front);
	}
	else{
		//printf("deque is full!\n");
	}
}

void push_back(dequeType *d, int x){
	if(!isfull(d)){
		d->rear = curque(d->rear);
		d->data[d->rear] = x;
	}
	else{
		//printf("deque is full!\n");
	}	
}

int pop_front(dequeType *d){
	if(!isempty(d)){
		d->front = curque(d->front);
		return d->data[d->front];
	}
	return -1;
}

int pop_back(dequeType *d){
	if(!isempty(d)){
		int tem = d->data[d->rear];
		d->rear = Rcurque(d->rear);
		return tem;
	}
	return -1;
}

int size(dequeType *d){
	return (d->front <= d->rear) ? d->rear - d->front : d->rear + MAX_QUEUE_SIZE - d->front;
}

int front(dequeType *d){
	return (isempty(d)) ? -1 : d->data[curque(d->front)];
}

int back(dequeType *d){
	return (isempty(d)) ? -1 : d->data[d->rear];
}

int main(void){
	
	dequeType *d1;
	d1 = (dequeType*) malloc(sizeof(dequeType));
	init(d1);
	
	int n, k;
	char command[11];
	scanf("%d", &n);
	for(int i = 0; i < n; i++){
		scanf("%s", &command);
		if(!strcmp(command, "push_front")){
			scanf("%d", &k);
			push_front(d1, k);
		}
		else if(!strcmp(command, "push_back")){
			scanf("%d", &k);
			push_back(d1, k);
		}
		else if(!strcmp(command, "pop_front")){
			printf("%d\n", pop_front(d1));
		}
		else if(!strcmp(command, "pop_back")){
			printf("%d\n", pop_back(d1));
		}
		else if(!strcmp(command, "size")){
			printf("%d\n", size(d1));
		}
		else if(!strcmp(command, "empty")){
			printf("%d\n", isempty(d1));
		}
		else if(!strcmp(command, "front")){
			printf("%d\n", front(d1));
		}
		else if(!strcmp(command, "back")){
			printf("%d\n", back(d1));
		}
	}
	
	free(d1);
	return 0;
}
