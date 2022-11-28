import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<int[]>[] children;
    static boolean[] vtd;
    static int branch;
    static int trunk;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        trunk = 0;
        branch = 0;
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        vtd = new boolean[N + 1];
        children = new ArrayList[N + 1];
        for (int n = 1; n <= N; n++) children[n] = new ArrayList<>();
        for (int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            children[a].add(new int[]{b, d});
            children[b].add(new int[]{a, d});

        }
        vtd[R] = true;
        if (children[R].size() == 1) {
            vtd[children[R].get(0)[0]] = true;
            trunk += children[R].get(0)[1];
            findBranch(findTrunk(children[R].get(0)[0]), 0);
        } else findBranch(R, 0);
        System.out.println(trunk + " " + branch);
    }

    static int findTrunk(int root) {
        while (children[root].size() == 2) {
            for (int[] child : children[root]) {
                if (!vtd[child[0]]) {
                    vtd[child[0]] = true;
                    trunk += child[1];
                    root = child[0];
                }
            }
        }
        return root;
    }

    static void findBranch(int node, int length) {
        if (children[node].size() == 1) branch = Math.max(branch, length);
        for (int[] child : children[node]) {
            if (!vtd[child[0]]) {
                vtd[child[0]] = true;
                findBranch(child[0], child[1] + length);
                vtd[child[0]] = false;
            }
        }
    }
}
