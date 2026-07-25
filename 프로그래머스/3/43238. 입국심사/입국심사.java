class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long end = (long) times[0] * n;
        long start = 0;
        while(start <= end) {
            long mid = (start + end) / 2;
            if(canTest(n, times, mid)) {
                end = mid - 1;
                answer = mid;
            } else {
                start = mid + 1;
            }
        }
        return answer;
    }
    
    public boolean canTest(int n, int[] times, long time) {
        // time에 해당 입국 심사 가능?
        long count = 0;
        for(int man: times) {
            count += (time / man);
        // n명 이상 입국심사 가능
            if (count >= n) return true;
        }
        
        return false;
    }
}