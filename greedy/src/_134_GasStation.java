/**
 * https://leetcode.com/problems/gas-station/description/
 * 
 * https://www.youtube.com/watch?v=tcOcmNHFTTM (MIK, Best explanation with brute force solution as well)
 * https://www.youtube.com/watch?v=lJwbPZGo05A (NeetCode)
 */
class _134_GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {

        if (getSum(cost) > getSum(gas)) {
            return -1; // base case.
        }

        int totalGas = 0;
        int result = 0;

        for (int i = 0; i < gas.length; i++) {

            totalGas = totalGas + gas[i] - cost[i];

            if (totalGas < 0) {
                totalGas = 0;
                result = i + 1;
            }
        }

        return result;
    }

    private int getSum(int[] arr) {
        int sum = 0;
        for (int a : arr) {
            sum += a;
        }
        return sum;
    }
}