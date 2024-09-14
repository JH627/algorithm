#include <iostream>

using namespace std;

int c[1000][5];
int score[1000];

int main(void) {
	int input;  
	cin >> input;
  
	for (int i = 0; i < input; i++) {
		for (int j = 0; j < 5; j++){
			cin >> c[i][j]; 
		}
	}
  
	int max = 0; 
	int num = 0; 
    
	for (int i = 0; i < input; i++) {
		for (int j = 0; j < 5; j++) {
			for (int k = j + 1; k < 5; k++) {
				for (int l = k + 1; l < 5; l++) {
					if (score[i] < (c[i][j] + c[i][k] + c[i][l]) % 10)
						score[i] = (c[i][j] + c[i][k] + c[i][l]) % 10;
				}
			}
		}
	}

	for (int i = 0; i < input; i++) {
		if (max < score[i]) {
			max = score[i];
			num = i;
		}
		if (max == score[i]) {
			if (i > num) {
        num = i;
      }
		}
	}
	cout << num + 1 << endl;

	return 0;
}
