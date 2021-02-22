public class _1004_longestOnes {
    public int longestOnes(int[] A, int K) {
        if(A == null || A.length == 0)
            return 0;
        int zero = 0;
        int res = 0;
        for(int i = 0, j = 0; j < A.length; j++){
            if(A[j] == 0)
                zero++;
            while(zero > K){
                if(A[i] == 0)
                    zero--;
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
