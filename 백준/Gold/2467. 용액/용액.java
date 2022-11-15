import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] solutions = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int min = Math.abs(solutions[0] + solutions[N - 1]);
        Point point = new Point(solutions[0], solutions[N - 1]);

        int l = 0, r = N - 1;
        while (l < r) {
            int now = solutions[l] + solutions[r];
            if (min > Math.abs(now)) {
                min = Math.abs(now);
                point = new Point(solutions[l], solutions[r]);
            }
            if (now < 0) l++;
            else if (now > 0) r--;
            else break;
        }

        System.out.println(point.x + " " + point.y);
    }
}
