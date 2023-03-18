#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define QUEUE_SIZE 2000000

typedef int element;

typedef struct{
	int front;
	int rear;
	element data[QUEUE_SIZE];
} QueueType;

int full(QueueType* q);
int empty(QueueType* q);

void init(QueueType* q){
	q->front = 0;
	q->rear = 0;
}

int cirQue(int n){
	return (n == QUEUE_SIZE - 1) ? 0 : n + 1;
}

void push(QueueType* q, element num){
	if (!full(q)){
		q->rear = cirQue(q->rear);	
		q->data[q->rear] = num;
	}
	else{
		//printf("queue is full!\n");
	}
}

void pop(QueueType* q){
	if (empty(q)){
		printf("-1\n");
	}
	else{
		q->front = cirQue(q->front);
		printf("%d\n", q->data[q->front]);
	}
}

void size(QueueType* q){
	if (q->rear >= q->front){
		printf("%d\n", q->rear - q->front);
		}
	else {
		printf("%d\n", (q->rear + QUEUE_SIZE - q->front));
	}
}

int empty(QueueType* q){
	return (q->front == q->rear) ? 1 : 0;
}

void front(QueueType* q){
	if (empty(q)){
		printf("-1\n");
	}
	else{
		printf("%d\n", q->data[cirQue(q->front)]);	
	}	
}

void back(QueueType* q){
	if (empty(q)){
		printf("-1\n");
	}
	else{
		printf("%d\n", q->data[q->rear]);	
	}	
}

int full(QueueType* q){
	return (q->front == (q->rear + 1) % QUEUE_SIZE) ? 1 : 0;	
}

int main(void){
	
	int n, j;
	scanf("%d", &n);
	char command[10];

	QueueType* q1;
	q1 = (QueueType*) malloc(sizeof(QueueType));
	init(q1);
	
	for(int i = 0; i < n; i++){
		scanf("%s", &command);
		if (strcmp(command, "push") == 0){
			scanf(" %d", &j);
			push(q1, j);			
		}
		else if (strcmp(command, "pop") == 0){
			pop(q1);
		}
		else if (strcmp(command, "size") == 0){
			size(q1);
		}
		else if (strcmp(command, "empty") == 0){
			printf("%d\n", empty(q1));
		}
		else if (strcmp(command, "front") == 0){
			front(q1);
		}
		else if (strcmp(command, "back") == 0){
			back(q1);
		}
	}
	
	free(q1);
	return 0;
}
