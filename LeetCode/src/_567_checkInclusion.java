public class _567_checkInclusion {
    public boolean checkInclusion(String s1, String s2) {
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();

        int len1 = chs1.length;
        int len2 = chs2.length;
        if(len1 > len2)
            return false;

        int[] fre = new int[26];
        for(char ch : chs1)
            fre[ch - 'a']--;

        for(int left = 0, right = 0; right < len2; right++){
            int ch = chs2[right] - 'a';
            fre[ch]++;
            while(fre[ch] > 0){
                fre[chs2[left] - 'a']--;
                left++;
            }
            if(right - left + 1 == len1)
                return true;
        }
        return false;
    }
}
