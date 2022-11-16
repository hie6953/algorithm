import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_MEMORY = 10001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] bites = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] costs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[MAX_MEMORY];
        int index = 0;
        for (int n = 0; n < N; n++) {
            if (costs[n] == 0) {
                M -= bites[n];
                continue;
            }
            int cost = costs[n], bite = bites[n];
            for (int i = index; i > 0; i--) {
                if (dp[i] != 0) dp[i + cost] = Math.max(dp[i + cost], dp[i] + bite);
            }
            dp[cost] = Math.max(dp[cost], bite);
            index += cost;
        }
        for (int i = 0; i < MAX_MEMORY; i++) {
            if (dp[i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }
}
