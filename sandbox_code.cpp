
//DP
//f[i, j]：表示从(i, j)出发的最小路径和
//状态转移方程：f[i, j] = min(f[i, j+1], f[i + 1, j + 1]) + (i, j)
int minSum(vector<vector<int> > &triggers) {
	for(int row = trggers.size() - 2; row <= 0; row--) {
		for(int col = 0; col < triggers[row].size(); col++) {
			triggers[row][col] += min(triggers[row + 1][col], triggers[row + 1][col + 1]);
		}
	}
	return triggers[0][0];
}

//DP
//f[i, j]：表示s1的前i个字符与s2的前j个字符的交织结果与s3的前i+j个字符匹配
//状态转移方程：f[i, j] = (s1[i] == s3[i + j] && f[i - 1, j]) || (s2[j] == s3[i + j] && f[i, j - 1])
bool isInterleave(string s1, string s2, string s3) {
	if(s3.size() != s1.size() + s2.size())
		return false;
	
	return isInterleave(s1, 0, s2, 0, s3, 0);
}

bool isInterleave(string &s1, int i1, string &s2, int i2, string &s3, int i3) {
	if(i1 == s1.size() && i2 == s2.size() && i3 == s3.size())
		return true;
	
	bool match = false;
	if(i1 < s1.size() && s1[i1] == s3[i3]) {
		match = isInterleave(s1, i1 + 1, s2, i2, s3, i3 + 1);
	}
	
	if(!match && i2 < s2.size() && s2[i2] == s3[i3]) {
		match = isInterleave(s1, i1, s2, i2 + 1, s3, i3 + 1);
	}
	
	return match;
}


bool isInterleave(string s1, string s2, string s3) {
	if(s3.size() != s1.size() + s2.size())
		return false;
	//create indicator
	vector<bool> match(s1.size() + 1, vector(s2.size() + 1, false));
	//initialization the first row and the first column
	match[0][0] = true;

	for(int l1 = 1; l1 <= s1.size(); ++ l1 ) {
		char c1 = s1[l1 - 1];
		char c3 = s3[l1 - 1];
		if (c1 == c3) {
			match[l1][0] = true;
		} else {
			break;
		}
	}

	for( int l2 = 1; l2 <= s2.size(); ++ l2 ) {
		char c2 = s2[l2 - 1];
		char c3 = s3[l2 - 1];
		if (c2 == c3) {
			match[0][l2] = true;
		} else {
			break;
		}
	}

	//work through the rest of matrix using the formula
	for(int l1 = 1; l1 <= s1.size(); ++ l1 ) {
		char c1 = s1[l1 - 1];
		for( int l2 = 1 ; l2 <= s2.size() ; ++ l2 ) {
			char c2 = s2[l2 - 1];
			int l3 = l1 + l2;
			char c3 = s3[l3 - 1];
			if (c1 == c3) {
				match[l1][l2] = match[l1 - 1][l2] || match[l1][l2];
			}
			if (c2 == c3) {
				match[l1][l2] = match[l1][l2 - 1] || match[l1][l2];
			}
		}
	}

	//the last element is the result
	return match[s1.size()][s2.size()];
}