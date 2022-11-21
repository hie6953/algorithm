import java.io.*;
import java.util.*;

public class Main {
    static int[] parents;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];
        for (int n = 1; n <= N; n++) {
            parents[n] = n;
        }
        sb = new StringBuilder();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (k == 0) {
                union(a, b);
            } else {
                addResult(a, b);
            }
        }
        System.out.println(sb);
    }

    static int findParent(int x) {
        if (x == parents[x]) return x;
        return parents[x] = findParent(parents[x]);
    }

    static void union(int x, int y) {
        int px = findParent(x);
        int py = findParent(y);
        if (px != py) parents[py] = px;
    }

    static void addResult(int a, int b) { sb.append(findParent(a) == findParent(b) ? "YES" : "NO").append("\n"); }

}
