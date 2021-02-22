import java.util.PriorityQueue;

public class _23_mergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                (ListNode n1, ListNode n2) -> n1.val - n2.val
        );
        for(ListNode node : lists)
            if(node != null)
                pq.offer(node);
        ListNode dummyHead = new ListNode(-1);
        ListNode pre = dummyHead;
        while(!pq.isEmpty()){
            pre.next = pq.poll();
            pre = pre.next;
            if(pre.next != null)
                pq.offer(pre.next);
        }
        return dummyHead.next;
    }
}
