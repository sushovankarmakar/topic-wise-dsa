import java.util.Arrays;

/** 
 * https://leetcode.com/problems/maximum-matching-of-players-with-trainers/description/
 * 
 * https://leetcode.com/problems/assign-cookies/ (similar question)
 * 
 * https://www.youtube.com/watch?v=DIX2p7vb9co&list=PLgUwDviBIf0rF1w2Koyh78zafB0cz7tea&index=1 - Striver
 */
public class _2410_MaximumMatchingOfPlayersWithTrainers {
    

    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        
        Arrays.sort(players);
        Arrays.sort(trainers);

        int i = 0, j = 0, count = 0;

        while (i < players.length && j < trainers.length) {

            if (players[i] <= trainers[j]) {
                i++;
                count++;
            }
            j++;
        }
        return count++;
    }
}
