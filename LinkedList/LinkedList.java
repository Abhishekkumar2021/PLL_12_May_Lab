package Lab.LinkedList;

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void add(T data) {
        Node<T> node = new Node<T>(data);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void print() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public T getHead() {
        return head.data;
    }

    public void removeHead() {
        head = head.next;
    }

    public void remove(T data) {
        Node<T> current = head;
        Node<T> previous = null;
        while (current != null) {
            if (current.data == data) {
                if (previous == null) {
                    head = head.next;
                } else {
                    previous.next = current.next;
                }
                break;
            }
            previous = current;
            current = current.next;
        }
    }

    public Node<T> getHeadNode() {
        return head;
    }

    public Node<T> getTailNode() {
        return tail;

    }

    public Node<T> get(int index) {
        Node<T> current = head;
        int i = 0;
        while (current != null) {
            if (i == index) {
                return current;
            }
            current = current.next;
            i++;
        }
        return null;
    }

    public int size() {
        Node<T> current = head;
        int i = 0;
        while (current != null) {
            current = current.next;
            i++;
        }
        return i;
    }

    public void sort(){
        // Applying merge sort
        head = mergeSort(head);
    }

    private Node<T> mergeSort(Node<T> head){
        if(head == null || head.next == null)
            return head;
        Node<T> middle = getMiddle(head);
        Node<T> nextOfMiddle = middle.next;
        middle.next = null;
        Node<T> left = mergeSort(head);
        Node<T> right = mergeSort(nextOfMiddle);
        Node<T> sortedList = merge(left, right);
        return sortedList;
    }
 
    private Node<T> merge(Node<T> left, Node<T> right){
        Node<T> result = null;
        if(left == null)
            return right;
        if(right == null)
            return left;
        
        if((int)left.data <= (int)right.data){
            result = left;
            result.next = merge(left.next, right);
        } else {
            result = right;
            result.next = merge(left, right.next);
        }
        return result;
    }

    private Node<T> getMiddle(Node<T> head){
        if(head == null)
            return head;
        Node<T> slow = head, fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public void reverse(){
        Node<T> current = head;
        Node<T> previous = null;
        Node<T> next = null;
        while(current != null){
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }
}

