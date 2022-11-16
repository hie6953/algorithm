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
        int[] prev = new int[MAX_MEMORY];
        int index = 0;
        for (int n = 0; n < N; n++) {
            if (costs[n] == 0) {
                M -= bites[n];
                continue;
            }
            int[] now = new int[MAX_MEMORY];
            int cost = costs[n], bite = bites[n];
            for (int i = index; i > 0; i--) {
                if (prev[i] != 0) {
                    now[i + cost] = Math.max(now[i + cost], prev[i] + bite);
                    now[i] = prev[i];
                }
            }
            now[cost] = Math.max(now[cost], bite);
            index += cost;
            prev = now;
        }
        for (int i = 0; i < MAX_MEMORY; i++) {
            if (prev[i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }
}
