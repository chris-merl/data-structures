import java.util.*;
import java.io.*;

public class tree_height {
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

	public class TreeHeight {
		int n;
		int parent[];
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

        private void maxDp(int parent[], int i, int dp[]){
            
            if(dp[i] != 0){return;}
            if(parent[i] == -1){dp[i] = 1; return;}
            if(dp[parent[i]] == 0){maxDp(parent, parent[i], dp);}
            dp[i] = dp[parent[i]] + 1;
        }
            
		int computeHeight() {
            
			int dp[] = new int[n];
            int height = dp[0];
            
			for (int i = 0; i < n; i++) {dp[i] = 0;}
            for (int i = 0; i < n; i++) {maxDp(parent, i, dp);}
			for (int i = 1; i < n; i++) {
                if (height < dp[i]) {
                height = dp[i];
            }
          }
            return height;
      }
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
    
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
