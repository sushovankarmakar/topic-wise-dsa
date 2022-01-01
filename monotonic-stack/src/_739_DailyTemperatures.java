package src;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/daily-temperatures/
 * https://www.youtube.com/watch?v=WGm4Kj3lhRI&ab_channel=AlexanderLe (Leetcode Visualized: 739. Daily Temperatures)
 * https://www.youtube.com/watch?v=m4hvxzLoN_I&ab_channel=SDESkills (Monotonic Stack)
 */
public class _739_DailyTemperatures {

  public int[] dailyTemperatures(int[] temperatures) {

    int len = temperatures.length;
    int lastIndex = len - 1;

    Deque<Pair> stack = new LinkedList<>();
    int[] outputs = new int[len];

    for (int i = lastIndex; i >= 0; i--) {
      int currElement = temperatures[i];

      while (!stack.isEmpty() && currElement >= stack.peek().val) {
        stack.pop();
      }
      outputs[i] = stack.isEmpty() ? 0 : (stack.peek().index - i);
      stack.push(new Pair(currElement, i));
    }

    return outputs;
  }

  class Pair {

    int val;
    int index;

    Pair(int val, int index) {
      this.val = val;
      this.index = index;
    }
  }

}
