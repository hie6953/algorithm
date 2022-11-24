import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        int[] times = new int[N + 1];
        int[] weights = new int[N + 1];
        ArrayList<Integer>[] childs = new ArrayList[N + 1];
        for (int n = 1; n <= N; n++) childs[n] = new ArrayList<>();
        for (int n = 1; n <= N; n++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            times[n] = arr[0];
            weights[n] += arr.length - 2;
            for (int i = 1; i < arr.length - 1; i++) {
                childs[arr[i]].add(n);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int n = 1; n <= N; n++) {
            if (weights[n] == 0) {
                queue.add(n);
                dp[n] = times[n];
            }
        }
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int child : childs[now]) {
                dp[child] = Math.max(dp[child], dp[now] + times[child]);
                weights[child]--;
                if (weights[child] == 0) queue.add(child);
            }
        }
        for (int n = 1; n <= N; n++) sb.append(dp[n]).append("\n");
        System.out.println(sb);
    }
}
