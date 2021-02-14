import java.util.Deque;
import java.util.LinkedList;

public class _394_decodeString {
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        Deque<Integer> multiStack = new LinkedList<>();
        Deque<StringBuilder> resStack = new LinkedList<>();
        int multi = 0;
        for(char c : s.toCharArray()){
            if(Character.isDigit(c))
                multi = multi * 10 + c - '0';
            else if(c == '['){
                multiStack.offerLast(multi);
                multi = 0;
                resStack.offerLast(new StringBuilder());
            }
            else if(Character.isAlphabetic(c)){
                if(resStack.isEmpty())  res.append(c);
                else    resStack.peekLast().append(c);
            }
            else if(c == ']'){
                String last = resStack.pollLast().toString().repeat(multiStack.pollLast());
                if(multiStack.isEmpty())    res.append(last);
                else    resStack.peekLast().append(last);
            }
        }
        return res.toString();
    }
}
