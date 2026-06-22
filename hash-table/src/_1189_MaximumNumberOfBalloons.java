package src;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/maximum-number-of-balloons/description/?envType=daily-question&envId=2026-06-22
 * https://leetcode.com/problems/rearrange-characters-to-make-target-string/
 */

public class _1189_MaximumNumberOfBalloons {

    public int maxNumberOfBalloons(String text) {

        Map<Character, Integer> freq = new HashMap<>();
        Set<Character> needed = new HashSet<>();
        needed.add('b');
        needed.add('a');
        needed.add('l');
        needed.add('o');
        needed.add('n');

        // counting the freq of each needed characters
        for (char ch : text.toCharArray()) {
            if (needed.contains(ch)) {
                freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            }
        }

        // we do not have enough characters
        if (freq.size() < 5) return 0;

        int freqB = freq.get('b');
        int freqA = freq.get('a');
        int freqL = freq.get('l') / 2;
        int freqO = freq.get('o') / 2;
        int freqN = freq.get('n');

        // finding the bottleneck
        return Math.min(freqB, Math.min(freqA, Math.min(freqL, Math.min(freqO, freqN))));
    }
}
