import java.io.*;
import java.util.*;

public class Main {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        parents = new int[N+1];
        for (int n = 1; n <= N; n++) parents[n] = n;
        for (int i = 0; i < N; i++) {
            int[] cities = getArr(br.readLine());
            for (int j = i + 1; j < N; j++) {
                if (cities[j] == 1) {
                    union(i + 1, j + 1);
                }
            }
        }
        int[] plan = getArr(br.readLine());
        String result = "YES";
        int parent = find(plan[0]);
        for (int m = 1; m < M; m++) {
            if (parent != find(plan[m])) {
                result = "NO";
                break;
            }
        }
        System.out.println(result);
    }

    static int[] getArr(String str) {
        return Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px > py) parents[px] = py; else parents[py] = px;
    }

}
