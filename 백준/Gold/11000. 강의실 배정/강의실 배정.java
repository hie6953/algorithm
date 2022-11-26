import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Point> queue = new PriorityQueue<>(((o1, o2) -> o1.x > o2.x ? 1 : -1));
        Queue<Integer> end = new PriorityQueue<>();
        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            queue.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int result = 0, room = 0;
        while (!queue.isEmpty()) {
            while (!end.isEmpty() && queue.peek().x >= end.peek()) {
                end.poll();
                room--;
            }
            Point point = queue.poll();
            end.add(point.y);
            room++;
            result = Math.max(result, room);
        }
        System.out.println(result);
    }
}
