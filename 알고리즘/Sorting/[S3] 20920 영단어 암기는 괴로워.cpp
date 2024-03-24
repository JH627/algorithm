#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <vector>
#include <map>
#include <string>
#include <algorithm>

using namespace std;

vector<string> v;
map<string, int> cnt;

bool myCompare(string a, string b) {
	int alen = a.length();
	int blen = b.length();
	if (cnt[a] != cnt[b]) {
		return cnt[a] > cnt[b];
	}
	else {
		if (alen != blen) {
			return alen > blen;
		}
		else {
			return a < b;
		}
	}
}


int main(void) {
	int n, m;
	string word;
	
	cin >> n >> m;

	int len;
	for (int i = 0; i < n; i++) {
		cin >> word;
		len = word.length();
		if (len >= m) {
			if (cnt.find(word) == cnt.end()) {
				v.push_back(word);
				cnt.insert(make_pair(word, 0));
			}
			cnt[word]++;
		}
	}

	sort(v.begin(), v.end(), myCompare);

	int length = cnt.size();
	for (int i = 0; i < length; i++) {
		cout << v[i] << "\n";
	}

	return 0;
}