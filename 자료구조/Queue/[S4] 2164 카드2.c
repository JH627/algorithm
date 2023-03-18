#include <stdio.h>
#include <stdlib.h>

#define QUEUE_SIZE 500001

typedef int element;

typedef struct{
	int front;
	int rear;
	element data[QUEUE_SIZE];
} QueueType;

void init(QueueType* q){
	q->front = 0;
	q->rear = 0;
}

int empty(QueueType* q){
	return (q->front == q->rear) ? 1 : 0;
}

int full(QueueType* q){
	return (q->front == (q->rear + 1) % QUEUE_SIZE) ? 1 : 0;	
}

int cirQue(int n){
	return (n == QUEUE_SIZE - 1) ? 0 : n + 1;
}

void add_rear(QueueType* q, element num){
	if (!full(q)){
		q->rear = cirQue(q->rear);	
		q->data[q->rear] = num;
	}
}

int get_front(QueueType* q){
	if (!empty(q)){
		q->front = cirQue(q->front);
		return q->data[q->front];
	}
}

int main(void){
	
	QueueType* q1;
	q1 = (QueueType*) malloc(sizeof(QueueType));
	init(q1);
	
	int n, tem;
	scanf("%d", &n);
	for(int i = 1; i <= n; i++){
		add_rear(q1, i);
	}
	for(int i = 0; i < n-1; i++){
		get_front(q1);
		tem = get_front(q1);
		add_rear(q1, tem);
	}
	printf("%d", get_front(q1));
	free(q1);
	return 0;
}
