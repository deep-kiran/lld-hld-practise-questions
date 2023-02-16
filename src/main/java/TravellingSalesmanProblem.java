import java.util.Arrays;
import java.util.HashMap;

public class TravellingSalesmanProblem {
    
    int dp[][];

    public int tsp(int node, int mask, int A, HashMap<Integer, HashMap<Integer, Integer>> adj) {
        if (mask == ((1 << A) - 1) && adj.get(node).get(0) > 0) { //all visited
            return adj.get(node).get(0);
        }
        if(dp[node][mask]!=-1){
            return dp[node][mask];
        }
        int mincost = 2000000007;
        HashMap<Integer, Integer> mp = adj.get(node);
        for (int j : mp.keySet()) {
            if ((mask & (1 << j)) == 0  && adj.get(node).get(j)>0) {
                int cost = adj.get(node).get(j) + tsp(j, (mask | (1 << j)), A, adj);
                mincost = Math.min(cost, mincost);
            }
        }
        dp[node][mask]=mincost;
        return mincost;
    }

    public int solve(int A, int[][] B) {
        dp = new int[A][(int) Math.pow(2, A)];
        for (int i = 0; i < A; i++)
            Arrays.fill(dp[i], -1);
        HashMap<Integer, HashMap<Integer, Integer>> adj = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            HashMap<Integer, Integer> mp = new HashMap<>();
            //TODO : cover edge cases

            for (int j = 0; j < B[0].length; j++) {

                mp.put(j, B[i][j]);

            }
            adj.put(i, mp);
        }

        int ans = tsp(0, 1, A, adj);
        return (ans == 2000000007 )? 0 :  ans;
    }

}
