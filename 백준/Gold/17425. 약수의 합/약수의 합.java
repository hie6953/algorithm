import java.io.*;

public class Main {
    static final int MAX_NUM = 1000001;
    public static void main(String[] args) throws IOException {
        long[] nums = new long[MAX_NUM];
        for (int i = 1; i < MAX_NUM; i++) {
            int n = 1;
            nums[i] += nums[i-1];
            while (i * n < MAX_NUM) {
                nums[i * n++] += i;
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int n = 0; n < N; n++) {
            sb.append(nums[Integer.parseInt(br.readLine())]).append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}