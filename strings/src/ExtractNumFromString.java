package src;

public class ExtractNumFromString {

    // https://www.geeksforgeeks.org/problems/extract-the-number-from-the-string3428/1
    // https://stackoverflow.com/a/29331473

    /**
     * solution using regex OR
     * solution using exception, try/catch was giving TLE.
     * To see, TLE solution check TLE submissions
     */
    public static void main(String[] args) {
        String sentence = "This is alpha 5097 and 79";
        String[] wordsAndNumbers = sentence.trim().split("\\s+");

        long maxNum = -1;

        for (String val : wordsAndNumbers) {

            long num = isNumber(val);
            maxNum = Math.max(maxNum, num);
        }

        System.out.println(maxNum);
    }

    private static long isNumber(String val) {
        if (val == null || val.isBlank()) return -1L;

        char[] digits = val.toCharArray();

        for (char digit : digits) {

            if (digit < '0' || digit > '8') { // question says : digit should not contain '9'
                return -1L;
            }
        }
        return Long.parseLong(val);
    }


    /*if (!val.contains("9") && val.matches("\\d+")) {
        long number = Long.parseLong(val);
        maxNum = Math.max(maxNum, number);
    }*/
}
