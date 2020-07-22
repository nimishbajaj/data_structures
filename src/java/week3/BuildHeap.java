package week3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
            data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
            out.println(swap.index1 + " " + swap.index2);
        }
    }

    private void generateSwaps() {
        swaps = new ArrayList<Swap>();

        for (int i = data.length - 1; i >= 0; i--) {
            siftDown(i, swaps);
        }
    }

    int leftChild(int i) {
        return i * 2 + 1;
    }

    int rightChild(int i) {
        return i * 2 + 2;
    }

    void siftDown(int i, List<Swap> swaps) {
        int minIndex = i;
        int l = leftChild(i);
        if (l < data.length && data[l] < data[minIndex]) {
            minIndex = l;
        }

        int r = rightChild(i);
        if (r < data.length && data[r] < data[minIndex]) {
            minIndex = r;
        }

        if (i != minIndex) {
            swaps.add(new Swap(i, minIndex));
            int tmp = data[minIndex];
            data[minIndex] = data[i];
            data[i] = tmp;

            siftDown(minIndex, swaps);
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
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
