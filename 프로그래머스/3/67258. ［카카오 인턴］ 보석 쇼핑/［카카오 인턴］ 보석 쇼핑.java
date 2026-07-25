import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        HashMap<String, Integer> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        for(String gem: gems) {
            set.add(gem);
        }
        
        int start = 0;
        int end = 0;
        // 정석 슬라이딩 윈도우로 푸셈..ㅋ
        map.put(gems[start], 1);
        int[] answer = new int[] {0, gems.length};
        while(start <= end && end < gems.length) {
            // 1. map.size와 set.size가 같지 않으면 end를연다
            if(map.size() != set.size()) {
                end++;
                if (end >= gems.length) break;
                map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);
            } else {
            // 2. 같다면 이떄 길이를 측정한다. 그리고, start를 줄인다. 
                if(answer[1] - answer[0] > end - start) {
                    answer[0] = start;
                    answer[1] = end;
                }
                map.put(gems[start], map.get(gems[start]) - 1);
                
                if (map.get(gems[start]) == 0) map.remove(gems[start]);
                start++;
            }
        }
        answer[0]++;
        answer[1]++;
        return answer;
    }
}