package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    int length;
    private LinkedListNode<T> point;

    public LinkedListDeque() {
        this.point = null;
        this.length = 0;
    }

    private void init(T data) {
        this.point = new LinkedListNode<>(data);
        this.point.first = this.point;
        this.point.last = this.point;
        this.length++;
    }

    public void addFirst(T item) {
        if (this.isEmpty()) {
            this.init(item);
        } else {
            LinkedListNode<T> tmp = new LinkedListNode<>(item);
            tmp.first = this.point.first;
            tmp.last = this.point;
            this.point.first.last = tmp;
            this.point.first = tmp;
            this.point = tmp;
            this.length++;
        }

    }

    public void addLast(T item) {
        if (this.isEmpty()) {
            this.init(item);
        } else {
            LinkedListNode<T> tmp = new LinkedListNode<>(item);
            tmp.first = this.point.first;
            tmp.last = this.point;
            this.point.first.last = tmp;
            this.point.first = tmp;
            this.length++;
        }

    }

    public int size() {
        return this.length;
    }

    public void printDeque() {
        LinkedListNode<T> node = this.point;
        for (int t = this.length; t > 0; --t) {
            System.out.print(node.data + " ");
            node = node.last;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        } else {
            T first = this.point.data;
            this.point.first.last = this.point.last;
            this.point.last.first = this.point.first;
            this.point = this.point.last;
            this.length--;
            return first;
        }
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        } else {
            T last = this.point.first.data;
            this.point.first.first.last = this.point;
            this.point.first = this.point.first.first;
            this.length--;
            return last;
        }
    }

    public T get(int index) {
        LinkedListNode<T> tmp = this.point;
        for (int a = index; a > 0; --a) {
            tmp = tmp.last;
        }
        return tmp.data;
    }

    public T getRecursive(int index) {
        return this.getRecursive(index, this.point);
    }

    private T getRecursive(int index, LinkedListNode<T> node) {
        return index == 0 ? node.data : this.getRecursive(index - 1, this.point.last);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator<>(this.point);
    }

    static class LinkedListNode<E> {
        public E data;
        public LinkedListNode<E> first, last;

        public LinkedListNode(E data) {
            this.data = data;
        }
    }

    class LinkedListDequeIterator<E> implements Iterator<E> {
        LinkedListNode<E> node;

        public LinkedListDequeIterator(LinkedListNode<E> n) {
            this.node = n;
        }

        public boolean hasNext() {
            return this.node.last != LinkedListDeque.this.point;
        }

        public E next() {
            E data = this.node.data;
            this.node = this.node.last;
            return data;
        }
    }
}
