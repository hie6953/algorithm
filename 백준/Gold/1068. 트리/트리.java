import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nodes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int del = Integer.parseInt(br.readLine());
        int root = 0, result = 0;
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer>[] children = new ArrayList[N];
        for (int n = 0; n < N; n++) children[n] = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            if (nodes[n] == -1) root = n;
            else if (n != del) children[nodes[n]].add(n);
        }
        if (del != root) queue.add(root);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (children[node].size() == 0) result++;
            else {
                for (int child : children[node]) {
                    queue.add(child);
                }
            }
        }
        System.out.println(result);
    }
}
