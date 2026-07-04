import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/problems/maximum-meetings-in-one-room/1
 * https://takeuforward.org/plus/dsa/problems/n-meetings-in-one-room?subject=dsa&approach=optimal
 * https://www.youtube.com/watch?v=mKfhTotEguk&list=PLgUwDviBIf0rF1w2Koyh78zafB0cz7tea&index=8 - Striver
 * MaximumMeetingsInOneRoom
 */
class MaximumMeetingsInOneRoom {

    public static void main(String[] args) {
        
        MaximumMeetingsInOneRoom o = new MaximumMeetingsInOneRoom();

        int[] s = {34, 45, 44, 41, 31, 11, 44, 42, 4};
        int[] f = {70, 48, 93, 44, 56, 55, 92, 45, 42};

        ArrayList<Integer> meetingsPossible = o.maxMeetings(s, f);
		for (Integer integer : meetingsPossible) {
			System.out.println(integer);
		}
    }

    public ArrayList<Integer> maxMeetings(int[] s, int[] f) {
		
		List<Meeting> meetings = new ArrayList<>();
		
		for (int i = 0; i < s.length; i++) {
			meetings.add(new Meeting(s[i], f[i], i + 1));
		}
		Collections.sort(meetings);
		
		ArrayList<Integer> meetingsPossible = new ArrayList<>();
		meetingsPossible.add(meetings.get(0).idx);
		
		int freeTime = meetings.get(0).end;
		
		for (int i = 1; i < meetings.size(); i++) {
			
			Meeting currMeeting = meetings.get(i);
			
			if (currMeeting.start > freeTime) {
				meetingsPossible.add(currMeeting.idx);
				freeTime = currMeeting.end;
			}
		}
		
		Collections.sort(meetingsPossible);
		
		return meetingsPossible;
	}
	
	class Meeting implements Comparable<Meeting> {
		int start;
		int end;
		int idx;
		
		Meeting(int start, int end, int idx) {
			this.start = start;
			this.end = end;
			this.idx = idx;
		}
		
		@Override
		public int compareTo(Meeting other) {
			
			if (this.end != other.end) {
				return Integer.compare(this.end, other.end);
			}
			return Integer.compare(this.idx, other.idx);
		}
	}
}