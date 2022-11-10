import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine().trim());
        StringTokenizer st;
        int[][] arrs = new int[N][2];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine().trim());
            arrs[n][0] = Integer.parseInt(st.nextToken());
            arrs[n][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arrs, Comparator.comparingInt(o1 -> o1[0]));
        int[] arr = Arrays.stream(arrs).mapToInt(o -> o[1]).toArray();
        int[] D = new int[N];
        int[] result = new int[N];
        D[0] = arr[0];
        int size = 1;
        for (int i = 1; i < N; i++) {
            int index = Arrays.binarySearch(D, 0, size, arr[i]);
            index = index >= 0 ? index : Math.abs(index) - 1;
            D[index] = arr[i];
            result[i] = index;
            if (index == size) size++;
        }

        int s = size-1, i = N-1;
        boolean[] vtd = new boolean[N];
        while (s >= 0) {
            if (result[i] == s) {
                vtd[i] = true;
                s--;
            }
            i--;
        }
        int count = 0;
        for (int n = 0; n < N; n++) {
            if (!vtd[n]) {
                count++;
                sb.append(arrs[n][0]).append("\n");
            }
        }
        System.out.println(count);
        if (count > 0) {
            sb.setLength(sb.length()-1);
            System.out.println(sb);
        }
    }
}
