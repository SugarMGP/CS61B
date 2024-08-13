package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private int length;
    private LinkedListNode<T> point;

    public LinkedListDeque() {
        this.length = 0;
        this.point = new LinkedListNode<>(null);
        this.point.first = this.point;
        this.point.last = this.point;
    }

    private void init(T data) {
        this.point.data = data;
        this.length = 1;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> deque = (Deque<T>) o;
        if (deque.size() != size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (deque.get(i) != get(i)) {
                return false;
            }
        }
        return true;
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
        for (int a = 0; a < index; a++) {
            tmp = tmp.last;
        }
        return tmp.data;
    }

    public T getRecursive(int index) {
        return this.getRecursive(index, this.point);
    }

    private T getRecursive(int index, LinkedListNode<T> node) {
        return index == 0 ? node.data : this.getRecursive(index - 1, node.last);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            LinkedListNode<T> node = point;
            int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < size();
            }

            @Override
            public T next() {
                index++;
                T data = this.node.data;
                this.node = this.node.last;
                return data;
            }
        };
    }
}
