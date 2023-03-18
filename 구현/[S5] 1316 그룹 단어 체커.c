#include <stdio.h>
#include <string.h>

int check(char* word){
	int alpha[26] = {0,};
	int len = strlen(word);
	for(int i = 0; i < len;){
		int alp = word[i] - 'a';
		if(!alpha[alp]){
			alpha[alp]++;
			for(; (word[i] - 'a') == alp && (i < len); i++){
			}
		}
		else{
			return 0;
		}
	}
	return 1;
}

int main(void){
	
	int n, count = 0;
	scanf("%d", &n);
	char word[100];
	for(int i = 0; i < n; i++){
		scanf("%s", &word);
		count += check(word);
	}
	printf("%d", count);
	
	return 0;
}
