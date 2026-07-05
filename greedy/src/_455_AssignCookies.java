import java.util.Arrays;

/**
 * https://leetcode.com/problems/assign-cookies/
 * 
 * https://leetcode.com/problems/maximum-matching-of-players-with-trainers/description/ (similar question)
 * 
 * https://www.youtube.com/watch?v=DIX2p7vb9co&list=PLgUwDviBIf0rF1w2Koyh78zafB0cz7tea&index=1 - Striver
 */
public class _455_AssignCookies {
    
    public int findContentChildren(int[] g, int[] s) {
        
        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0, j = 0, count = 0;

        while (i < g.length && j < s.length) {

            if (g[i] <= s[j]) {
                i++;
                count++;
            }
            j++;
        }
        return count++;
    }
}
