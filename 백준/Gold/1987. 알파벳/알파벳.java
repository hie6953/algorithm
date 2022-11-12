import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int count, X, Y;
    static boolean[] vtd;
    static int[][] chart;
    static Point[] dir = new Point[]{new Point(1, 0), new Point(-1, 0), new Point(0, -1), new Point(0, 1)};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        vtd = new boolean[26];
        chart = new int[X][Y];
        count = 0;
        for (int x = 0; x < X; x++) {
            chart[x] = br.readLine().chars().map(e -> e-65).toArray();
        }
        vtd[chart[0][0]] = true;
        dfs(0, 0, 1);
        System.out.println(count);
    }
    static void dfs(int x, int y, int cnt) {
        if (cnt > count) count = cnt;
        for (Point p : dir) {
            int nx = p.x + x, ny = p.y + y;
            if (isRange(nx, ny) && !vtd[chart[nx][ny]]) {
                vtd[chart[nx][ny]] = true;
                dfs(nx, ny, cnt + 1);
                vtd[chart[nx][ny]] = false;
            }
        }
    }

    static boolean isRange(int x, int y) {
        return (x >= 0 && y >= 0 && x < X && y < Y) ? true : false;
    }
}