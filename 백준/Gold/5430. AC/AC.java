import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String[] func = br.readLine().split("");
            int N = Integer.parseInt(br.readLine());
            String str = br.readLine();
            int[] arr = N != 0 ? Arrays.stream(str.substring(1, str.length() - 1).split(",")).mapToInt(Integer::parseInt).toArray() : new int[]{};
            int left = 0, right = N - 1;
            boolean isReversed = false;
            boolean isBreak = false;
            for (int f = 0; f < func.length; f++) {
                if (func[f].equals("R")) isReversed = !isReversed;
                else {
                    if (left > right) {
                        isBreak = true;
                        break;
                    }
                    if (isReversed) right--;
                    else left++;
                }
            }
            if (isBreak) sb.append("error");
            else {
                int[] ar = Arrays.copyOfRange(arr, left, right + 1);
                List<Integer> list = Arrays.stream(ar).boxed().collect(Collectors.toList());
                if (isReversed) Collections.reverse(list);
                sb.append(Arrays.toString(list.toArray()).replace(" ", ""));
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
}

