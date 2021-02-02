public class _424_characterReplacement {
    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        char[] chs = s.toCharArray();
        int left = 0;
        int right = 0;
        int maxCount = 0;
        for(right = 0; right < chs.length; right++){
            int ch = chs[right] - 'A';
            count[ch]++;
            maxCount = Math.max(maxCount, count[ch]);
            if(right - left + 1 > maxCount + k){
                count[chs[left] - 'A']--;
                left++;
            }
        }
        return chs.length - left;
    }
}
