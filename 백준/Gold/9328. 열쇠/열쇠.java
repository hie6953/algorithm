import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static final int KEY_LENGTH = 26;
    static final int UPPERCASE = 65;
    static final int LOWERCASE = 97;
    static final int DOCS = 36;
    static final int WALL = 42;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Point[] dxy = new Point[]{new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1)};
    static Queue<Point> queue;
    static int H, W, count;
    static int[][] building;
    static boolean[][] vtd;
    static boolean[] keys;
    static ArrayList<Point>[] doors;


    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            sb.append(test()).append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }


    static int test() throws IOException {
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        building = new int[H][W];
        vtd = new boolean[H][W];
        keys = new boolean[KEY_LENGTH];
        count = 0;
        doors = new ArrayList[KEY_LENGTH];
        for (int k = 0; k < KEY_LENGTH; k++) doors[k] = new ArrayList<>();
        for (int h = 0; h < H; h++) {
            building[h] = br.readLine().chars().toArray();
        }
        String str = br.readLine();
        if (!str.equals("0")) str.chars().forEach(e -> keys[e-97] = true);

        queue = new LinkedList<>();
        for (int w = 0; w < W - 1; w++) {
            if (check(0, w)) search();
            if (check(H - 1, W - w - 1)) search();
        }
        for (int h = 0; h < H - 1; h++) {
            if (check(H - h - 1, 0)) search();
            if (check(h, W - 1)) search();
        }
        return count;
    }


    static boolean check(int x, int y) {
        int num = building[x][y];
        if (num == WALL || vtd[x][y]) return false;
        else if (num == DOCS) count++;
        else if (num >= LOWERCASE) {
            keys[num - LOWERCASE] = true;
            for (Point p : doors[num - LOWERCASE]) {
                queue.add(p);
            }
            doors[num - LOWERCASE] = new ArrayList<>();
        }
        else if (num >= UPPERCASE && !keys[num - UPPERCASE]) {
            doors[num - UPPERCASE].add(new Point(x, y));
            return false;
        }
        queue.add(new Point(x,y));
        vtd[x][y] = true;
        return true;
    }

    static void search() {
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            for (Point d : dxy) {
                int nx = now.x + d.x;
                int ny = now.y + d.y;
                if (isInRange(nx, ny)) check(nx, ny);
            }
        }
    }

    static boolean isInRange(int x, int y) {
        return (0 <= x && 0 <= y && x < H && y < W) ? true : false;
    }
}
