package deque;

public class LinkedListNode<T> {
    public T data;
    public LinkedListNode<T> first, last;

    public LinkedListNode(T data) {
        this.data = data;
    }
}
