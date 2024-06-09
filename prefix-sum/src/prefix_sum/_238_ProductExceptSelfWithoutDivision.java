package prefix_sum;

/**
 * This problem was asked by Uber.
 * <p>
 * Given an array of integers,
 * return a new array such that each element at index i of the new array is the product of all the numbers
 * in the original array except the one at i.
 * <p>
 * For example,
 * if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24].
 * If our input was [3, 2, 1], the expected output would be [2, 3, 6].
 * <p>
 * Follow-up: what if you can't use division?
 */

import java.util.Arrays;

/**
 * https://leetcode.com/problems/product-of-array-except-self/
 */
public class _238_ProductExceptSelfWithoutDivision {

    // https://chatgpt.com/share/93f29d8b-da1f-4015-ba02-77cbf65c0a41

    /**
     * TWO APPROACHES :
     * --------------
     * 1. first think of productExceptSelf_TwoArr solution - Space Complexity : O(n) -
     * 2. then optimise productExceptSelf_OneArr solution - Space Complexity : O(1) - excluding the output array
     */

    /**
     * First Pass: Iterate through the array from left to right.
     * For each element, store the product of all previous elements in the output array.
     * <p>
     * Second Pass: Iterate through the array from right to left.
     * For each element, update the output array by multiplying it with the product of all elements to its right
     */
    public static int[] productExceptSelf_OneArr(int[] nums) {

        int n = nums.length;
        int[] products = new int[n];

        // Step 1: Initialize the output array with the left product
        products[0] = 1;
        for (int i = 1; i < n; i++) {
            products[i] = products[i - 1] * nums[i - 1];
        }

        // Step 2: Update the output array with the right product
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            products[i] *= rightProduct;
            rightProduct *= nums[i];
        }

        return products;
    }


    /**
     * To solve this problem optimally in Java without using division,
     * we can approach it by constructing two auxiliary arrays (leftProducts and rightProducts)
     * to keep track of the cumulative products of elements to the left and right of each index
     */
    public static int[] productExceptSelf_TwoArr(int[] nums) {

        int n = nums.length;
        int[] products = new int[n];
        int[] leftProducts = new int[n];
        int[] rightProducts = new int[n];

        leftProducts[0] = 1;
        for (int i = 1; i < n; i++) {
            leftProducts[i] = leftProducts[i - 1] * nums[i - 1];
        }

        rightProducts[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            rightProducts[i] = rightProducts[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            products[i] = leftProducts[i] * rightProducts[i];
        }

        return products;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5};
        int[] nums2 = {3, 2, 1};

        System.out.println(Arrays.toString(productExceptSelf_OneArr(nums1))); // Output: [120, 60, 40, 30, 24]
        System.out.println(Arrays.toString(productExceptSelf_OneArr(nums2))); // Output: [2, 3, 6]
    }
}
