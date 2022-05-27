package src;

/**
 * https://practice.geeksforgeeks.org/problems/find-number-of-times-a-string-occurs-as-a-subsequence3020/1/
 * https://leetcode.com/problems/distinct-subsequences/
 * https://www.geeksforgeeks.org/find-number-times-string-occurs-given-string/
 * <p>
 * <p>
 * Similar concept like this problem : Count of Subsets Sum with a Given Sum
 * https://www.youtube.com/watch?v=F7wqWbqYn9g&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=9&ab_channel=AdityaVerma
 *
 * https://leetcode.com/discuss/general-discussion/1276555/find-number-of-times-a-string-occurs-as-a-subsequence-in-given-string
 *
 * companies :
 * amazon, mathworks, google, bloomberg, apple, adobe, goldman sachs
 *
 * choice diagram :
 * https://drive.google.com/file/d/1PSJa3WNpQm-GI0_BSEUYoWdH0qoGH0_s/view?usp=sharing
 */
public class _115_NumOfTimesStringOccursAsSubSequence_BottomUp {

    public static void main(String[] args) {

        System.out.println(numDistinct(
                "xslledayhxhadmctrliaxqpokyezcfhzaskeykchkmhpyjipxtsuljkwkovmvelvwxzwieeuqnjozrfwmzsylcwvsthnxujvr" +
                        "kszqwtglewkycikdaiocglwzukwovsghkhyidevhbgffoqkpabthmqihcfxxzdejletqjoxmwftlxfcxgxgvpperwbq" +
                        "vhxgsbbkmphyomtbjzdjhcrcsggleiczpbfjcgtpycpmrjnckslrwduqlccqmgrdhxolfjafmsrfdghnatexyanldr" +
                        "dpxvvgujsztuffoymrfteholgonuaqndinadtumnuhkboyzaqguwqijwxxszngextfcozpetyownmyneehdwqmtpjloz" +
                        "tswmzzdzqhuoxrblppqvyvsqhnhryvqsqogpnlqfulurexdtovqpqkfxxnqykgscxaskmksivoazlducanrqxynxlgvw" +
                        "onalpsyddqmaemcrrwvrjmjjnygyebwtqxehrclwsxzylbqexnxjcgspeynlbmetlkacnnbhmaizbadynajpibepbu" +
                        "acggxrqavfnwpcwxbzxfymhjcslghmajrirqzjqxpgtgisfjreqrqabssobbadmtmdknmakdigjqyqcruujlwmfoa" +
                        "grckdwyiglviyyrekjealvvigiesnvuumxgsveadrxlpwetioxibtdjblowblqvzpbrmhupyrdophjxvhgzclidzy" +
                        "bajuxllacyhyphssvhcffxonysahvzhzbttyeeyiefhunbokiqrpqfcoxdxvefugapeevdoakxwzykmhbdytjbhigf" +
                        "fkmbqmqxsoaiomgmmgwapzdosorcxxhejvgajyzdmzlcntqbapbpofdjtulstuzdrffafedufqwsknumcxbschdy" +
                        "bosxkrabyfdejgyozwillcxpcaiehlelczioskqtptzaczobvyojdlyflilvwqgyrqmjaeepydrcchfyftjighntqzoo"
                ,"rwmimatmhydhbujebqehjprrwfkoebcxxqfktayaaeheys")); // 543744000

        System.out.println(numDistinct("rabbbit", "rabbit")); // 3
        System.out.println(numDistinct("babgbag", "bag"));  // 5
        System.out.println(numDistinct("geeksforgeeks","gks")); // 4
        System.out.println(numDistinct("adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeab"+
                        "cddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc",
                "bcddceeeebecbc")); // 700531452
    }

    /**
     * time  complexity : O(m * n)
     * space complexity : O(m * n)
     */
    private static int numDistinct(String s, String t) {

        int m = s.length();
        int n = t.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {

                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                if (i == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if (s.charAt(i - 1) == t.charAt(j - 1)) {

                    int includingCurrChar = dp[i - 1][j - 1];
                    int excludingCurrChar = dp[i - 1][j];

                    dp[i][j] = includingCurrChar + excludingCurrChar;

                } else {

                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }
}
