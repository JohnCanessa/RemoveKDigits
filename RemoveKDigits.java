import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


/**
 * 
 */
public class RemoveKDigits {


    /**
     * Given string num representing a non-negative integer num, and an integer k, 
     * return the smallest possible integer after removing k digits from num.
     * Using Greedy algorithm with stack and string builder.
     * 
     * 40 / 40 test cases passed.
     * Status: Accepted
     * Runtime: 7 ms
     * Memory Usage: 39.4 MB
     * 
     * Runtime: 7 ms, faster than 70.37% of Java online submissions.
     * Memory Usage: 39.3 MB, less than 71.43% of Java online submissions.
     * 
     * Execution: O(n) - Space: O(2 * n)
     */
    static public String removeKdigits0(String num, int k) {

        // **** sanity checks ****
        int len = num.length();
        if (len == k) return "0";

        // **** initialization ****
        Stack<Character> stack  = new Stack<>();
        StringBuilder sb        = new StringBuilder();

        // **** populate the stack - O(n) ****
        for (int i = 0; i < len; i++) {

            // **** for ease of use ****
            char ch = num.charAt(i);

            // **** char in stack > ch in num (greedy selection) - O(k) ****
            while (k > 0 && !stack.isEmpty() && stack.peek() > ch) {
                stack.pop();
                k--;
            }

            // **** push this character into the stack ****
            stack.push(ch);
        }

        // **** remove from stack characters (if needed) - O(k) ****
        for ( ; k > 0; k--) stack.pop();

        // **** move stack contents to string builder - O(n) ****
        while (!stack.isEmpty()) sb.append(stack.pop());

        // **** reverse string builder ****
        sb.reverse();

        // **** remove leading 0s from string builder - O(n) ****
        while (sb.length() > 1 && sb.charAt(0) == '0') sb.deleteCharAt(0);

        // **** return string ****
        return sb.toString();
    }


    /**
     * Given string num representing a non-negative integer num, and an integer k, 
     * return the smallest possible integer after removing k digits from num.
     * Using Greedy algorithm with array and string builder.
     * 
     * 40 / 40 test cases passed.
     * Status: Accepted
     * Runtime: 4 ms
     * Memory Usage: 40.5 MB
     * 
     * Runtime: 4 ms, faster than 95.04% of Java online submissions.
     * Memory Usage: 40.5 MB, less than 26.78% of Java online submissions.
     * 
     * Runtime: O(n) - Space: O(n)
     */
    static public String removeKdigits1(String num, int k) {

        // **** sanity checks ****
        int len = num.length();
        if (len == k) return "0";

        // **** initialization ****
        StringBuilder sb        = new StringBuilder();
        char[] numChars         = num.toCharArray();
        boolean[] flags         = new boolean[len];
        int j                   = 0;

        // **** traverse numChars array (greedy algorithm) - O(n) ****
        for (int i = 0; i < len; i++) {

            // **** for ease of use ****
            char ch = numChars[i];

            // **** check current character ch ****
            while (k > 0 && j >= 0 && numChars[j] > ch) {
                flags[j] = false;
                k--;
                for ( ; j >= 0 && flags[j] == false; j--);
            }

            // **** use this digit ****
            flags[i] = true;

            // **** last digit for future comparison ****
            j = i;
        }

        // **** remove additional characters (k needs to be 0) - O(k) ****
        for ( ; k > 0; k--) {
            flags[j] = false;
            for ( ; j >= 0 && flags[j] == false; j--);  
        }

        // **** populate string builder removing leading 0s - O(n) ****
        boolean start = true;
        for (int i = 0; i < len; i++) {

            // **** skip leading 0s and disable start flag (if needed) ****
            if (start == true) {
                if (flags[i] == true) {
                    if (numChars[i] == '0') continue;
                    else start = false;
                }
            }

            // **** add this character to the string builder ****
            if (flags[i] == true) sb.append(num.charAt(i));
        }

        // **** append 0 to empty string builder (if needed) ****
        if (sb.length() == 0) sb.append('0');

        // **** return string ****
        return sb.toString();
    }


    /**
     * Given string num representing a non-negative integer num, and an integer k, 
     * return the smallest possible integer after removing k digits from num.
     * Using Greedy algorithm with array and string builder.
     * 
     * 40 / 40 test cases passed.
     * Status: Accepted
     * Runtime: 2 ms
     * Memory Usage: 39.2 MB
     * 
     * Runtime: 2 ms, faster than 99.70% of Java online submissions.
     * Memory Usage: 39.2 MB, less than 84.50% of Java online submissions.
     *
     * Execution: O(n) - Space: O(2 * n)
     */
    static public String removeKdigits(String num, int k) {

        // **** sanity checks ****
        int len = num.length();
        if (len == k) return "0";

        // **** initialization ****
        StringBuilder sb        = new StringBuilder();
        boolean[] flags         = new boolean[len];
        int j                   = 0;

        // **** greedy algorithm - O(n) ****
        for (int i = 0; i < len; i++) {

            // **** ****
            while (k > 0 && j >= 0 && num.charAt(j) > num.charAt(i)) {
                flags[j] = false;
                k--;
                for ( ; j >= 0 && flags[j] == false; j--);
            }

            // **** use this digit ****
            flags[i] = true;

            // **** last digit for future comparison ****
            j = i;
        }

        // **** remove characters (if needed) - O(k) ****
        for ( ; k > 0; k--) {
            flags[j] = false;
            for ( ; j >= 0 && flags[j] == false; j--);  
        }

        // **** populate string builder ****
        for (int i = 0; i < len; i++)
            if (flags[i] == true) sb.append(num.charAt(i));

        // **** remove leading 0s from string builder ****
        while (sb.length() > 1 && sb.charAt(0) == '0') sb.deleteCharAt(0);

        // **** return string ****
        return sb.toString();
    }


    /**
     * Test scaffold
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read string nums ****
        String nums = br.readLine().trim();

        // **** read int k ****
        int k = Integer.parseInt(br.readLine().trim());

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<<   nums: " + nums);
        System.out.println("main <<<      k: " + k);

        // **** call function of interest and display result ****
        System.out.println("main <<< output ==>" + removeKdigits0(nums, k) + "<==");

        // **** call function of interest and display result ****
        System.out.println("main <<< output ==>" + removeKdigits1(nums, k) + "<==");

        // **** call function of interest and display result ****
        System.out.println("main <<< output ==>" + removeKdigits(nums, k) + "<==");
    }

}