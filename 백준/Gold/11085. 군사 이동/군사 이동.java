import java.io.*;
import java.util.*;

public class Main {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int P = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        parents = new int[P];
        for (int p = 0; p < P; p++) parents[p] = p;
        Queue<int[]> queue = new PriorityQueue<>(((o1, o2) -> o2[2] - o1[2]));
        for (int w = 0; w < W; w++) {
            queue.add(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        }
        int result = 0;
        while (find(c) != find(v)) {
            int[] arr = queue.poll();
            if (find(arr[0]) != find(arr[1])) {
                union(arr[0], arr[1]);
                result = arr[2];
            }
        }
        System.out.println(result);
    }

    static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px != py) parents[px] = py;
    }
}
