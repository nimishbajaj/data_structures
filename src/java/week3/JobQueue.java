package week3;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private long[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        assignedWorker = new long[jobs.length];
        startTime = new long[jobs.length];

        // save the thread index, and the last job executed in the priority queue
        PriorityQueue<long[]> pq = new PriorityQueue<long[]>(new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                if (o1[1] == o2[1]) return Long.compare(o1[0], o2[0]);
                else return Long.compare(o1[1], o2[1]);
            }
        });

        for (int i = 0; i < numWorkers; i++) {
            pq.add(new long[]{i, 0});
        }

        for (int i = 0; i < jobs.length; i++) {
            long[] freeThread = pq.poll();
            assignedWorker[i] = freeThread[0];
            startTime[i] = freeThread[1];
            freeThread[1] += jobs[i];
            pq.offer(freeThread);
        }

    }


    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
