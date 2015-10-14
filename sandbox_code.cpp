
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

