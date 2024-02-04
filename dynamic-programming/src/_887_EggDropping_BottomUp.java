package src;

public class _887_EggDropping_BottomUp {

    public static void main(String[] args) {
        System.out.println(eggDrop(1, 2));  // 2
        System.out.println(eggDrop(2, 10)); // 4
        System.out.println(eggDrop(2, 6));  // 3
        System.out.println(eggDrop(3, 14)); // 4
        System.out.println(eggDrop(100, 100)); // 7
        System.out.println(eggDrop(100, 200)); // 8
        System.out.println(eggDrop(200, 200)); // 8
    }

    private static int eggDrop(int eggs, int floors) {

        int[][] dp = new int[eggs + 1][floors + 1];

        for (int i = 0; i <= eggs; i++) {
            for (int j = 0; j <= floors; j++) {

            }
        }

        return dp[eggs][floors];
    }
}
