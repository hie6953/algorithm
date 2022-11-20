import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] sum = new int[N];
        long[] count = new long[M];
        long result = 0;
        sum[0] = Integer.parseInt(st.nextToken())%M;
        for (int n = 1; n < N; n++) {
            sum[n] += (Integer.parseInt(st.nextToken()) + sum[n-1])%M;
        }
        for (int n = 0; n < N; n++) {
            if (sum[n] == 0) result++;
            result += count[sum[n]]++;
        }
        System.out.println(result);
    }
}
