import java.io.*;
import java.util.*;

public class Main {
    static int[] minTree;
    static int[] maxTree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        minTree = new int[N * 4];
        maxTree = new int[N * 4];
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());
        makeTree(0, N - 1, 1, arr, true);
        makeTree(0, N - 1, 1, arr, false);

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb
                .append(findMinimum(0, N - 1, 1, a - 1, b - 1))
                .append(" ")
                .append(findMaximum(0, N - 1, 1, a - 1, b - 1))
                .append("\n");
        }

        System.out.println(sb);
    }

    static int makeTree(int start, int end, int index, int[] arr, boolean isMin) {
        if (start == end) {
            if (isMin) minTree[index] = arr[start];
            else maxTree[index] = arr[start];
            return arr[start];
        }
        int mid = (start + end) / 2;
        if (isMin) minTree[index] = Math.min(makeTree(start, mid, index * 2, arr, isMin), makeTree(mid + 1, end, index * 2 + 1, arr, isMin));
        else maxTree[index] = Math.max(makeTree(start, mid, index * 2, arr, isMin), makeTree(mid + 1, end, index * 2 + 1, arr, isMin));
        return isMin ? minTree[index] : maxTree[index];
    }

    static int findMinimum(int start, int end, int index, int left, int right) {
        if (left > end || right < start) return 1000000001;
        if (left <= start && right >= end) return minTree[index];
        int mid = (start + end) / 2;
        return Math.min(findMinimum(start, mid, index * 2, left, right), findMinimum(mid + 1, end, index * 2 + 1, left, right));
    }

    static int findMaximum(int start, int end, int index, int left, int right) {
        if (left > end || right < start) return 0;
        if (left <= start && right >= end) return maxTree[index];
        int mid = (start + end) / 2;
        return Math.max(findMaximum(start, mid, index * 2, left, right), findMaximum(mid + 1, end, index * 2 + 1, left, right));
    }
}
