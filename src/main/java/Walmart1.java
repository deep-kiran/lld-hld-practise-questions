import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Walmart1 {


    public static void main(String[] args) {
        int arr[][] =new int[][]
            {{0, 0, 1, 1},
            {0, 0, 1, 1},
            {0, 1, 1, 1},
            {0, 0, 0, 0}};
        System.out.println(getMaxOnesRow(arr));
    }

    private static int getMaxOnesRow(int[][] arr) {
        int i =0;
        int ans=0;
        int j = arr[0].length-1;
        while(i!=arr.length){
            if(j==0){
                return i;
            }
            if(j!=0 && arr[i][j-1]==1){
                j--;
                ans =i;
            }else{
                i++;
            }
        }
        return ans;
    }

    /**
     * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
     * Return the max sliding window.
     *
     * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
     * Output: [3,3,5,5,6,7]
     *
     * Explanation:
     * Window position                Max
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3                [1,2]
     *  1 [3  -1  -3] 5  3  6  7       3                [1,2,3]
     *  1  3 [-1  -3  5] 3  6  7       5                [4]
     *  1  3  -1 [-3  5  3] 6  7       5                [4,5]
     *  1  3  -1  -3 [5  3  6] 7       6                [6]
     *  1  3  -1  -3  5 [3  6  7]      7                [7]
     */

    public ArrayList<Integer> maximumWindow(int arr[], int k){
        ArrayList<Integer> ans = new ArrayList<>();
        Deque<Integer> indices = new LinkedList<>();
        for(int i=0;i<k;i++) {
            while (!indices.isEmpty() && arr[indices.peekFirst()] < arr[i]) {
                indices.pollFirst();
            }
            indices.addLast(i);      //   5, 3
        }

        for(int i =k;i<arr.length;i++){
            ans.add(arr[indices.peekFirst()]); // [3, 3, 5, 6, 7]
            while(!indices.isEmpty() && arr[indices.peekFirst()]< arr[i]){
                indices.pollFirst();
            }
            indices.addLast(i);
            while (!indices.isEmpty() && indices.peekFirst()< i-k+1){
                indices.pollFirst();
            }
        }
        ans.add(arr[indices.peekFirst()]);
        return  ans;
    }

}
