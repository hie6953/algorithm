import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            sb.append(dualQueue()).append("\n");
        }
        System.out.println(sb);
    }

    static String dualQueue() throws IOException {
        String result = "EMPTY";
        int Q = Integer.parseInt(br.readLine());
        Queue<Integer> maxQueue = new PriorityQueue<>(((o1, o2) -> o2 > o1 ? 1 : -1));
        Queue<Integer> minQueue = new PriorityQueue<>();
        Map<Integer, Integer> count = new HashMap<>();
        for (int q = 0; q < Q; q++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            int k = Integer.parseInt(st.nextToken());
            if (str.equals("I")) {
                if (count.containsKey(k)) count.replace(k, count.get(k) + 1);
                else {
                    count.put(k, 1);
                    minQueue.add(k);
                    maxQueue.add(k);
                }
            } else {
                if (k == 1) {
                    while (!count.isEmpty() && count.getOrDefault(maxQueue.peek(), 0) == 0) maxQueue.poll();
                    if (!count.isEmpty()) {
                        int peek = maxQueue.peek();
                        if (count.get(peek) == 1) {
                            maxQueue.poll();
                            count.remove(peek);
                        }
                        else count.replace(peek, count.get(peek) - 1);
                    }
                } else {
                    while (!count.isEmpty() && count.getOrDefault(minQueue.peek(), 0) == 0) minQueue.poll();
                    if (!count.isEmpty()) {
                        int peek = minQueue.peek();
                        if (count.get(peek) == 1) {
                            minQueue.poll();
                            count.remove(peek);
                        }
                        else count.replace(peek, count.get(peek) - 1);
                    }
                }
            }
        }
        while (!count.isEmpty() && count.getOrDefault(maxQueue.peek(), 0) == 0) maxQueue.poll();
        while (!count.isEmpty() && count.getOrDefault(minQueue.peek(), 0) == 0) minQueue.poll();
        return count.isEmpty() ? result : String.format("%d %d", maxQueue.peek(), minQueue.peek());
    }
}
