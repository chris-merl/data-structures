import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Scanner;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    public void readData() throws IOException {
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

    public void generateSwaps() {
        swaps = new ArrayList<Swap>();
        int pos = data.length;
        for (int i = pos/2; i >= 0; i--){
            minHeapify(i);
        }
    }

    public void minHeapify(int i){
        int minInd = i;
        int leftChild = (2*i) + 1;
        int rightChild = (2*i) + 2;

        if(leftChild < data.length && data[leftChild] < data[minInd]){
            minInd = leftChild;
        }

        if(rightChild < data.length && data[rightChild] < data[minInd]){
            minInd = rightChild;
        }

        if(i != minInd){
            swaps.add(new Swap(i,minInd));

            int tmp = data[i];
            data[i] = data[minInd];
            data[minInd] = tmp;

            minHeapify(minInd);
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

        public FastScanner() throws FileNotFoundException {
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
