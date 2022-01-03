package src;

/**
 * https://www.geeksforgeeks.org/search-almost-sorted-array/
 *
 * https://www.youtube.com/watch?v=W3-KgsCVH1U&ab_channel=AdityaVerma
 */
public class SearchingInNearlySortedArray {

  public static void main(String[] args) {
    System.out.println(binarySearch(new int[]{10, 3, 40, 20, 50, 80, 70}, 40)); // 2
    System.out.println(binarySearch(new int[]{10, 3, 40, 20, 50, 80, 70}, 90)); // -1
    System.out.println(binarySearch(new int[]{5, 10, 30, 20, 40}, 40)); // 4
  }

  private static int binarySearch(int[] arr, int target) {

    int n = arr.length;
    int start = 0;
    int end = n - 1;

    while (start <= end) {

      int mid = start + (end - start) / 2;

      if (arr[mid] == target) {
        return mid;
      } else if ((mid - 1 >= start) && arr[mid - 1] == target) {
        return mid - 1;
      } else if ((mid + 1 <= end) && arr[mid + 1] == target) {
        return mid + 1;
      }
      else if (target <= arr[mid]) {
        end = mid - 2;
      } else if (arr[mid] <= target) {
        start = mid + 2;
      }
    }
    return -1;
  }

}
