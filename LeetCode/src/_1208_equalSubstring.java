public class _1208_equalSubstring {
    public int equalSubstring(String s, String t, int maxCost) {
        if(s == null || t == null || s.length() == 0 || t.length() == 0)
            return 0;
        char[] chs = s.toCharArray();
        char[] cht = t.toCharArray();
        int maxTrans = 0;
        int cost = 0;
        for(int left = 0, right = 0; right < cht.length; right++){
            cost += Math.abs(cht[right] - chs[right]);
            while(cost > maxCost){
                cost -= Math.abs(cht[left] - chs[left]);
                left++;
            }
            maxTrans = Math.max(maxTrans, right - left + 1);
        }
        return maxTrans;
    }
}
