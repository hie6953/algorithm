class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int[] mons = new int[200001];
        for (int n : nums) {
            if (mons[n] == 0) answer++;
            mons[n]++;
        }
        return Math.min(answer, nums.length/2);
    }
}