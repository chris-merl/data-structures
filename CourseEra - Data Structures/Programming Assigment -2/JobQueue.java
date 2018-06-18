import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.*;

public class JobQueue {
   private int noWorkers;
   private int[] jobs;

   private int[] assgndWorker;
   private long[] strtTime;

   private FastScanner in;
   private PrintWriter out;

   public static void main(String[] args) throws IOException {
       new JobQueue().solve();
   }

   private void readData() throws IOException {
       noWorkers = in.nextInt();
       int m = in.nextInt();
       jobs = new int[m];
       for (int i = 0; i < m; ++i) {
           jobs[i] = in.nextInt();
       }
   }

   private void writeResponse() {
       for (int i = 0; i < jobs.length; ++i) {
           out.println(assgndWorker[i] + " " + strtTime[i]);
       }
   }
   
   private class Worker {
       int id;
       long nxtFreeTime;
       public Worker (int id) {
           this.id = id;
           nxtFreeTime = 0;
       }
   }

   private void assignJobs() {
   	assgndWorker = new int[jobs.length];
    strtTime = new long[jobs.length];
		PriorityQueue<Worker> prq = new PriorityQueue<Worker>(noWorkers, new Comparator<Worker>(){
			@Override
            public int compare (Worker w1, Worker w2) {
				if(w1.nxtFreeTime == w2.nxtFreeTime)
					return w1.id - w2.id;
				else
					return (int) (w1.nxtFreeTime - w2.nxtFreeTime);
            }
		});
		for (int i = 0; i < noWorkers; i++)
           prq.offer(new Worker(i));
       for (int i = 0; i < jobs.length; i++) {
           Worker freeThread = prq.poll();
           assgndWorker[i] = freeThread.id;
           strtTime[i] = freeThread.nxtFreeTime;
           freeThread.nxtFreeTime += jobs[i];
           prq.offer(freeThread);
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

       public FastScanner() throws FileNotFoundException{
        reader = new BufferedReader(new InputStreamReader(System.in));
       	//File file=new File("D:\\CourseEra\\Data Structues & Algorithm\\Programming Assigment -2\\Programming-Assignment-2\\job_queue\\tests\\02.txt");
       	//reader = new BufferedReader(new FileReader(file));
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
