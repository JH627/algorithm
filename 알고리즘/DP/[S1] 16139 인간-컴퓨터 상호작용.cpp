#include <iostream>
#include <string>

#define MAX 200002

int alpha[26][MAX]{};

using namespace std;

int main(void) {
	ios_base::sync_with_stdio(false);
	cout.tie(NULL);
	cin.tie(NULL);
	
	string s;
	cin >> s;
	int len = s.length();
	
	for (int i = 1; i <= len; i++) {
		for (int j = 0; j < 26; j++) {
			alpha[j][i] = alpha[j][i-1];
		} 
		alpha[s[i-1]-'a'][i]++;
	}
	
	char c;
	int t, start, end;	
	
	cin >> t;
	for (int i = 0; i < t; i < i++) {
		cin >> c >> start >> end;
		cout << alpha[c-'a'][end+1] - alpha[c-'a'][start] << '\n';
	}

	return 0;
}
