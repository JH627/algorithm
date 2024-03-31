#include <iostream>
#include <map>
#include <string>

using namespace std;

map<string, int> m;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int n;
	string a, b;
	bool danceTime = false;
	cin >> n;
	
	for (int i = 0; i < n; i++) {
		cin >> a >> b;
		if (!danceTime) {
			if ((a == "ChongChong" || b == "ChongChong")) {
				m[a] = 1;
				m[b] = 1;
				danceTime = true;
			}
		}
		else {
			if (m.find(a) != m.end() || m.find(b) != m.end()) {
				m[a] = 1;
				m[b] = 1;
			}
		}		
	} 
	
	cout << m.size();
	
	return 0;
}
