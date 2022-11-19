import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>();

        int Z = Integer.parseInt(br.readLine());
        left.add(Z);
        sb.append(Z).append("\n");
        for (int n = 1; n < N; n++) {
            Z = Integer.parseInt(br.readLine());
            if (left.peek() > Z) {
                left.add(Z);
                if (left.size() > right.size() + 1) {
                    right.add(left.poll());
                }
            } else {
                right.add(Z);
                if (right.size() > left.size()) {
                    left.add(right.poll());
                }
            }
            sb.append(left.peek()).append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}
