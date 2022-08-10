package src;

/**
 * https://practice.geeksforgeeks.org/problems/e47329920b4e75869ea7b0e9b7c59ea145ccc22c/1
 * <p>
 * https://www.youtube.com/watch?v=IhIpreSnEhI (I learnt from this)
 * https://www.geeksforgeeks.org/policemen-catch-thieves/
 * https://www.youtube.com/watch?v=t354mo-bASI (Police and Thieves | Problem of the day | GeeksforGeeks Practice)
 */
public class PoliceAndThieves {

    public static void main(String[] args) {
        System.out.println(catchThieves(new char[]{'P', 'P', 'T', 'T', 'T', 'T', 'P', 'T', 'T', 'T', 'P', 'T', 'P',
                'P', 'T', 'P', 'T', 'P', 'T', 'P', 'P', 'T', 'P', 'P', 'T', 'T', 'P', 'T', 'P', 'P', 'P', 'T', 'P',
                'T', 'P', 'P', 'T', 'T', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'T', 'T', 'P', 'P', 'P', 'T', 'P', 'P',
                'T', 'T', 'P', 'P', 'T', 'P', 'P', 'P', 'P', 'T', 'P', 'T'}, 65, 4));
        System.out.println(catchThieves(new char[]{'P', 'T', 'T', 'P', 'T'}, 5, 1));
        System.out.println(catchThieves(new char[]{'T', 'T', 'P', 'P', 'T', 'P'}, 5, 1));
    }

    static int catchThieves(char[] arr, int n, int k) {

        int numOfThievesCaught = 0;

        for (int i = 0; i < n; i++) {

            if (arr[i] == 'P') {

                int start = Math.max(0, i - k); // (i - k) < 0 ? 0 : (i - k);
                int end = Math.min(i + k, n - 1); // (i + k) > n ? (n - 1) : (i + k);

                for (int j = start; j <= end && j < n; j++) {

                    if (i == j) continue;

                    if (arr[j] == 'T') {
                        numOfThievesCaught++;
                        arr[j] = 'C';   // marking the current thief as Caught.
                        break;
                    }
                }
            }
        }
        return numOfThievesCaught;
    }
}
