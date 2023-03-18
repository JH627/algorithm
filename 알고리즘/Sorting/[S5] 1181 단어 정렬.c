#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int compare(const void *a, const void *b){
	int alen = strlen(a);
	int blen = strlen(b);
	
	if (alen > blen){
        return 1;
    }
	else if (alen < blen){
        return -1;
    }
	else{
        return strcmp(a, b);
    }
}
	
int main(void){
	int n;
	scanf("%d", &n);
	char word[n][51];
	for(int i = 0; i < n; i++){
		scanf("%s", &word[i]);
	}
	qsort(word, n, sizeof(word[0]), compare);
	printf("%s\n", word[0]);
	for(int i = 1; i < n; i++){
		if (strcmp(word[i-1], word[i])){
			printf("%s\n", word[i]);
		}
	}
	return 0;
}
