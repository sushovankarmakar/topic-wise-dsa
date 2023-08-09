package src;

/**
 * https://practice.geeksforgeeks.org/problems/largest-prime-factor2601/1
 *
 * https://discuss.geeksforgeeks.org/comment/36b9c7249ec38c704086d91b921fd036 (Handwritten notes. I followed this)
 * https://ung.edu/learning-support/video-transcripts/prime-factorization-numbers.php
 */
public class LargestPrimeFactor {

    public static void main(String[] args) {
        System.out.println(largestPrimeFactor(123480)); // 7
    }

    /**
     * start from the smallest prime factor 2 and divide N by the prime until it's freq is not zero.
     */
    private static long largestPrimeFactor(int n) {

        long prime = 2;

        while (prime * prime <= n) {

            if (n % prime == 0) {
                n = n / (int) prime;
            } else {
                prime++;
            }
        }

        return (long) n;
    }
}
