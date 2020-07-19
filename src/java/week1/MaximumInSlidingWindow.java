package week1;
/* Created by Nimish.Bajaj on 19/07/20 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class MaximumInSlidingWindow {
    public static void main(String[] args) throws IOException {
        new MaximumInSlidingWindow().solve();
    }

    private void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        int window = scanner.nextInt();

        Deque<Integer> maxWindow = new LinkedList<>();
        maxWindow.offer(0);

        for (int i = 1; i < n; i++) {
            if (i >= window) {
                if (!maxWindow.isEmpty()) System.out.println(nums[maxWindow.peek()]);

                // remove old elements if present
                if (!maxWindow.isEmpty() && maxWindow.peek() <= i - window) maxWindow.poll();
            }

            // remove all smaller elements
            while (!maxWindow.isEmpty() && nums[maxWindow.getLast()] < nums[i]) maxWindow.removeLast();
            maxWindow.offer(i);
        }

        if (!maxWindow.isEmpty()) System.out.println(nums[maxWindow.peek()]);
    }

    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
