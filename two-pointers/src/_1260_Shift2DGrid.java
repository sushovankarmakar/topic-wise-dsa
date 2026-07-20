package src;

import java.util.ArrayList;
import java.util.List;

/**
 * _1260_Shift2DGrid
 */
public class _1260_Shift2DGrid {

    public static void main(String[] args) {
        
        _1260_Shift2DGrid obj = new _1260_Shift2DGrid();

        int[][] grid = {{3,8,1,9},{19,7,2,5},{4,6,11,10},{12,0,21,13}}; int k = 4;
        //int[][] grid = {{1},{2},{3},{4},{7},{6},{5}}; int k = 23

        List<List<Integer>> output = obj.shiftGrid(grid, k);
        for(List<Integer> innerList : output) {
            System.out.println(innerList);
        }
    }

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {

        int row = grid.length;
        int col = grid[0].length;
        
        int[] arr = flatten(grid);
        rotate(arr, k);
        
        return convertInto2D(arr, col);
    }

    private void rotate(int[] arr, int k) {

        int n = arr.length;
        k = k % n; // to avoid unnecessary rotation
        k = n - k; // for right rotation

        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
        reverse(arr, 0, n - 1);
    }

    private void reverse(int[] arr, int left, int right) {

        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }

    private int[] flatten(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int[] arr = new int[n * m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i * m + j] = grid[i][j];
            }
        }
        return arr;
    }

    private List<List<Integer>> convertInto2D(int[] arr, int m) {

        List<List<Integer>> list = new ArrayList<>();

        List<Integer> innerList = new ArrayList<>();;
        for (int i = 0; i < arr.length; i++) {

            if (i % m == 0) {
                innerList = new ArrayList<>();
                list.add(innerList);
            }
            innerList.add(arr[i]);
        }
        return list;
    }
}