import java.io.*;
import java.util.*;


public class Main {
    static int[][] puzzle;
    static boolean[][] row_vtd, col_vtd, squ_vtd;
    static boolean isFind;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        puzzle = new int[9][9];
        row_vtd = new boolean[9][10];
        col_vtd = new boolean[9][10];
        squ_vtd = new boolean[9][10];
        isFind = false;
        for (int i = 0; i < 9; i++) {
            puzzle[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (puzzle[i][j] != 0) {
                    setVtd(i, j, puzzle[i][j], true);
                }
            }
        }
        dfs(0, 0);
        for (int[] p : puzzle) {
            Arrays.stream(p).forEach(sb::append);
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static void dfs(int r, int c) {
        if (r == 8 && c == 9) {
            isFind = true;
            return;
        }
        if (c == 9) {
            r++;
            c %= 9;
        }
        if (puzzle[r][c] != 0) dfs(r, c + 1);
        else {
            for (int n = 1; n < 10; n++) {
                if (!isVtd(r, c, n)) {
                    setVtd(r, c, n,true);
                    dfs(r, c + 1);
                    if (isFind) {break;}
                    setVtd(r, c, n, false);
                };
            }
        }
    }

    static void setVtd(int r, int c, int n, boolean tf) {
        row_vtd[r][n] = tf;
        col_vtd[c][n] = tf;
        squ_vtd[(r/3)*3 + c/3][n] = tf;
        puzzle[r][c] = tf ? n : 0;
    }

    static boolean isVtd(int r, int c, int n) {
        return row_vtd[r][n] || col_vtd[c][n] || squ_vtd[(r/3)*3 + c/3][n];
    }
}
