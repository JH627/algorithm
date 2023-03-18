#include <stdio.h>
#include <stdlib.h>

typedef char element;

typedef struct tree{
	element data;	
	struct tree* left;
	struct tree* right;	
} TreeType;

TreeType* makeNode(element c){
	TreeType* n = (TreeType*)malloc(sizeof(TreeType));
	n->data = c;
	n->left = NULL;
	n->right = NULL;
	
	return n;	
}

TreeType* searchNode(TreeType* t, element c){
	if (t != NULL){
		if (t->data == c){
			return t;
		}
		else{
			TreeType* snode = searchNode(t->left, c);
			if (snode != NULL){
				return snode;
			}
			else{
				return searchNode(t->right, c);
			}
		}
	}
	else{
		return NULL;
	}
}

void setNode(TreeType* t, element a, element b, element c){
	t->data = a;
	if (b != '.'){
		t->left = makeNode(b);
	}
	if (c != '.'){
		t->right = makeNode(c);
	}	
}

void preorder(TreeType* t){
	if (t){
		printf("%c", t->data);
		preorder(t->left);
		preorder(t->right);
	}
}

void inorder(TreeType* t){
	if (t){
		inorder(t->left);
		printf("%c", t->data);
		inorder(t->right);
	}
}

void postorder(TreeType* t){
	if (t){	
		postorder(t->left);
		postorder(t->right);
		printf("%c", t->data);
	}
}

int main(void){
	
	int n;
	scanf("%d\n", &n);
	char a, b, c;
	
	TreeType* tree1;
	TreeType* tree2;
	tree1 = makeNode('\0');
	
	
	for (int i = 0; i < n; i++){
		scanf("%c %c %c", &a, &b, &c);
		getchar();
		
		tree2 = searchNode(tree1, a);
		
		if (tree2 == NULL){
			setNode(tree1, a, b, c);
		}
		else{
			setNode(tree2, a, b, c);
		}	
	}
	
	preorder(tree1);
	printf("\n");
	inorder(tree1);
	printf("\n");
	postorder(tree1);
	printf("\n");
	
	free(tree1);
	free(tree2);
	return 0;
}
