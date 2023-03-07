import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        List<Integer> arr = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int[] result = new int[N];
        Stack<Integer> stack = new Stack<>();
        for (int i = N - 1; i >= 0; i--) {
            while(!stack.isEmpty()) {
                if (arr.get(i) >= stack.peek()) stack.pop();
                else break;
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.add(arr.get(i));
        }
        for (int i = 0; i < N; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
    }
}