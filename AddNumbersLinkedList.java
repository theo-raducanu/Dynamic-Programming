package addnumberslinkedlist;

public class AddNumbersLinkedList 
{
    private class ListNode
    {
        int value;
        ListNode next;
 
        ListNode(int value)
        {
            this.value = value;
        }
    }
     
    ListNode head;
    ListNode tail;
     
    // appends node at the end of the list
    private void appendNode(int value)
    {
        if (head == null)
        {
            head = new ListNode(value);
            tail = head;
            return;
        }
         
        ListNode n = new ListNode(value);
        tail.next = n;
        tail = n;
    }
 
    // creates and returns a new list with node values taken from number[] array
    public ListNode createLinkedList(int[] number)
    {
        // if the head is pointing to some existing list, make it null
        // let the clients handle and store the reference to head
        if (head != null)
        {
            head = null;
        }
         
        for (int i = 0; i < number.length; i++)
        {
            appendNode(number[i]);
        }
        return head;
    }
     
    public void printList(ListNode head)
    {
        ListNode temp = head;
         
        while (temp != null)
        {
            System.out.print(temp.value + "->");
            temp = temp.next;
        }
        System.out.print("null");
    }
     
    private int countDiff(ListNode head1, ListNode head2)
    {
        int extraNodes = 0;
         
        ListNode temp = head1;
        while (temp != null)
        {
            extraNodes += 1;
            temp = temp.next;
        }
         
        temp = head2;
        while (temp != null)
        {
            extraNodes -= 1;
            temp = temp.next;
        }
 
        return extraNodes;
    }
     
    /*
     * Adds the two lists(which represent numbers) starting from node1 and node2-
     * and returns reference to the first node of the resultant list along with generated carry. 
     * extraNodes > 0, indicates that the length of list1 is greater than length of the list2
     * extraNodes > 0, indicates that the length of list2 is greater than length of the list1
     * extraNodes = 0, indicates lists of equal length.
     *  
     * References for head1 and head2 are used to start adding a list from the beginning if the earlier nodes    
     * passed were null.
     */
    private ListNode addLists(ListNode node1, ListNode node2, int extraNodes, int[] carry, ListNode head1, ListNode head2)
    {
        // if both lists are null, both sum and carry are 0. 
        // we don't need to create a new linked list in this case  
        if (node1 == null && node2 == null)
        {
            carry[0] = 0;
            return null;
        }
        ListNode nextNode;
         
        if (extraNodes > 0) // list1 has extra nodes hence pass corresponding node as null node from list2
        {
            nextNode = addLists(node1.next, null, extraNodes-1, carry, head1, head2);
        }
        else if (extraNodes < 0) // list2 has extra nodes hence pass corresponding node as null node from list1
        {
            nextNode = addLists(null, node2.next, extraNodes+1, carry, head1, head2);
        }
        /*
         * If null nodes were being passed earlier(node1 == null or node2 == null) for any of the lists then that must have been because 
         * of the length difference of the lists.
         * If now the lengths have become equal, corresponding node passed of the shorter lists must be  
         * the head node of the list for the first time when lengths became equal.
         * 
         * If node1 and node2 both are non-null, then we are looking at lists of equal length and we need to simply pass 
         * the next nodes for addition. 
         */
        else
        {
            ListNode node1Next = (node1 == null)? head1 : node1.next;
            ListNode node2Next = (node2 == null)? head2 : node2.next;
             
            nextNode = addLists(node1Next, node2Next, 0, carry, head1, head2);
        }
         
        // if a node is null then we consider its value as 0
        int value1 = (node1 != null) ? node1.value : 0;
        int value2 = (node2 != null) ? node2.value : 0;
         
        // create a new node with value as 'sum' and update the carry for the calling function 
        int sum  = (value1 + value2 + carry[0]) % 10;
        carry[0] = (value1 + value2 + carry[0]) / 10;
         
        ListNode currentNode =  new ListNode(sum);
        currentNode.next = nextNode;
         
        return currentNode;
    }
     
    public ListNode addLists(ListNode head1, ListNode head2)
    {
        if (head1 == null)
        {
            return head2;
        }
        else if (head2 == null)
        {
            return head1;
        }
         
        int[] carry = new int[1];
         
        ListNode result;
        /* 
         * Count the differences of number of nodes. For equal length lists, difference would be 0.
         * If list1 is longer, difference would be positive, if list2 is longer difference would be negative
         */
        int extraNodes = countDiff(head1, head2);
         
        if (extraNodes > 0)
        {
            /*
             * If list1 is longer, list2 node corresponding to first node of list1 would be null.
             * Difference in the length of the lists would be reduced by 1
             */
            result = addLists(head1, null, extraNodes - 1, carry, head1, head2);
        }
        else if (extraNodes < 0)
        {
            /*
             * If list2 is longer, list1 node corresponding to first node of list2 would be null.
             * Difference in the length of the lists would be reduced by 1.
             * Since difference is negative in this case, we add 1 to get it closer to 0
             */
            result = addLists(null, head2, extraNodes + 1, carry, head1, head2);
        }
        else
        {
            // if the lengths are equal, we simply pass the first nodes of both lists
            result = addLists(head1, head2, 0, carry, head1, head2);
        }
         
        /*
         * After the lists are added, if there is any carry remaining,
         * we need to create a new node with its value as 'carry'.
         * This new node would be the first node if the resultant list 
         */
        if (carry[0] != 0)
        {
            ListNode tempNode = new ListNode(carry[0]);
            tempNode.next = result;
            result = tempNode;
        }
        return result;
    }
     
     
    public static void main(String[] args) 
    {
        AddNumbersLinkedList solution = new AddNumbersLinkedList();
 
        int[] firstNumber  = {9,8,9,7,6}; // number: 98976
        int[] secondNumber = {1,9,8}; // number: 198
         
        // 9->8->9->7->6->null
        ListNode head1 = solution.createLinkedList(firstNumber);
         
        // 1->9->8->null
        ListNode head2 = solution.createLinkedList(secondNumber);
         
        ListNode result = solution.addLists(head2, head1);
         
        System.out.print("Resultant sum represented as a linked list is: \n");
        solution.printList(result);
    }
}