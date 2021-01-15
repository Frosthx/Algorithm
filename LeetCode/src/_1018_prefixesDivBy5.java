import java.util.ArrayList;
import java.util.List;

class _1018_prefixesDivBy5 {
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list = new ArrayList<>(A.length);
        int prefix = 0;
        for (int i : A) {
            prefix = ((prefix << 1) | i) % 5;
            list.add(prefix == 0);
        }
        return list;
    }
}