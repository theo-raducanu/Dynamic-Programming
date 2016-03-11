package longestincreasingsubsequence;

public class LongestIncreasingSubsequence {
 
    public static int longestIncreasingSubsequence(int[] input) {
 
        if(input == null || input.length == 0) {
            return 0;
        }
 
        int n = input.length;
        // lisLength[i] = Length of Longest Increasing Subsequence in input[0..i]
        int[] lisLength = new int[n];
        int maxLength = 1;
        lisLength[0] = 1;
        for (int i = 1; i < n; i++) {
            lisLength[i] = 1;
            for (int j = 0; j < i; j++) {
                if (input[i] > input[j] && lisLength[i] < lisLength[j] + 1) {
                    lisLength[i] = lisLength[j] + 1;
                }
            }
            // Update the length of longest increasing subsequence found till now
            if(maxLength < lisLength[i]) {
                maxLength = lisLength[i];
            }
        }
        return maxLength;
    }
 
    public static void main(String[] args) {
        int[] array = { 12, 18, 7, 34, 30, 28, 90, 88 };
        System.out.println("Length of Longest Increasing Subsequence: " + longestIncreasingSubsequence(array));
    }
}
