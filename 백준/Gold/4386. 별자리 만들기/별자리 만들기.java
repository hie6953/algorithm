import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = N;
        double result = 0;
        StringTokenizer st;
        double[][] stars = new double[N][2];
        double[][] dist = new double[N][N];
        boolean[] vtd = new boolean[N];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            stars[n][0] = Double.parseDouble(st.nextToken());
            stars[n][1] = Double.parseDouble(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                double len = Math.sqrt(Math.pow(stars[i][0] - stars[j][0], 2)
                        + Math.pow(stars[i][1] - stars[j][1], 2));
                dist[i][j] = len;
                dist[j][i] = len;
            }
        }
        PriorityQueue<double[]> queue = new PriorityQueue<>((a,b) -> (int) (a[0] - b[0]));
        queue.add(new double[]{0, 0});
        while (!queue.isEmpty() && cnt > 0) {
            double[] now = queue.poll();
            int n = (int) now[1];
            if (vtd[n]) continue;
            vtd[n] = true;
            cnt--;
            result += now[0];
            for (int i = 0; i < N; i++) {
                if (vtd[i]) continue;
                queue.add(new double[]{dist[n][i], i});
            }
        }
        System.out.printf("%.2f", result);
    }
}
