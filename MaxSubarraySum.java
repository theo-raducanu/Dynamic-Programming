package maxsubarraysum;

public class MaxSubarraySum {
 
    public static int findMaxSubarraySum(int[] input) {
        int curSum = 0;
        int maxSum = 0;
        boolean hasAllNegativeNumbers = true;
        int maxNegativeSum = Integer.MIN_VALUE;
        for(int i = 0; i < input.length; i++) {
            if(hasAllNegativeNumbers && input[i] >= 0) {
                hasAllNegativeNumbers = false;
            } else if(hasAllNegativeNumbers && input[i] < 0 && maxNegativeSum < input[i]) {
                maxNegativeSum = input[i];
            }
             
            curSum += input[i];
            if(curSum < 0) {
                curSum = 0;
            }
            if(maxSum < curSum) {
                maxSum = curSum;
            }
        }
        return hasAllNegativeNumbers ? maxNegativeSum : maxSum;
    }
     
    public static void main(String[] args) {
        int[] input = {2, -9, 5, 1, -4, 6, 0, -7, 8};
        System.out.println("Maximum subarray sum is " + findMaxSubarraySum(input));
    }
}
