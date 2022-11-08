import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int[] degree = new int[N + 1];
        ArrayList<Integer>[] next = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) next[i] = new ArrayList<>();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            degree[B]++;
            next[A].add(B);
        }
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) queue.add(i);
        }
        while (!queue.isEmpty()) {
            int now = queue.poll();
            sb.append(now).append(" ");
            for (int n : next[now]) {
                degree[n]--;
                if (degree[n] == 0) queue.add(n);
            }
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}
