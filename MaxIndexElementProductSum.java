package maxindexelementproductsum;

public class MaxIndexElementProductSum 
{
 
    private int findIndexElementProductSum(int[] array)
    {
        int currValue = 0;
        for (int i = 0; i < array.length; i++)
        {
            currValue   += i*array[i];
        }
         
        return currValue;
    }
     
    private void rotateClockwise(int[] array)
    {
        if (array == null || array.length < 2)
        {
            return;
        }
         
        int n = array.length;
        int temp = array[n-1];
         
        for (int i = n-1; i >= 1; i--)
        {
            array[i] = array[i-1];
        }
        array[0] = temp;
    }
     
     
    public int simpleFindMaxIndexElementProductSum(int[] array)
    {
        // currValue indicates index-element-product sum when no rotation is performed
        int sumElements = 0, currValue = 0;
        for (int i = 0; i < array.length; i++)
        {
            sumElements += array[i];
            currValue   += i*array[i];
        }
 
 
        int maxValue = currValue, n = array.length;
         
        for (int i = 1; i < n; i++)
        {
            rotateClockwise(array);
            currValue = findIndexElementProductSum(array);
            if (currValue > maxValue)
            {
                maxValue = currValue;
            }
        }
         
        return maxValue;
    }
 
    public int findMaxIndexElementProductSum(int[] array)
    {
        // currValue indicates index-element-product sum when no rotation is performed
        int sumElements = 0, currValue = 0;
        for (int i = 0; i < array.length; i++)
        {
            sumElements += array[i];
            currValue   += i*array[i];
        }
 
        /* 
         * If 'n' indicates length of array then there could be maximum 'n-1' rotations. 
         * n'th rotation restores the original array.
         * 
         * If 'currValue' is the index-element-product sum after 'i-1' rotations then 
         * the index-element-product sum after 'i' rotations would be currValue + sumElements - n*array[n-i] 
         */
        int maxValue = currValue, n = array.length;
         
        for (int i = 1; i < n; i++)
        {
            currValue += sumElements - n*array[n-i];
            if (currValue > maxValue)
            {
                maxValue = currValue;
            }
        }
         
        return maxValue;
    }
     
    public static void main(String[] args) 
    {
        int[] array = {24, 26, 25, 22};
         
        MaxIndexElementProductSum solution = new MaxIndexElementProductSum();
         
        System.out.println(solution.findMaxIndexElementProductSum(array));
    }
}