package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] array;
    private int size;

    public ArrayDeque() {
        this.array = (T[]) new Object[8];
        this.size = 0;
    }

    @Override
    public void addFirst(T item) {
        if (size == array.length) {
            T[] newArray = (T[]) new Object[array.length * 3];
            newArray[0] = item;
            System.arraycopy(array, 0, newArray, 1, size);
            array = newArray;
        } else {
            for (int i = size - 1; i >= 0; i--) {
                array[i + 1] = array[i];
            }
            array[0] = item;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == array.length) {
            T[] newArray = (T[]) new Object[array.length * 3];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
        array[size] = item;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = array[0];
        size--;
        if (array.length != 1) {
            T[] newArray = (T[]) new Object[array.length - 1];
            System.arraycopy(array, 1, newArray, 0, size);
            array = newArray;
        } else {
            array[0] = null;
        }
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T item = array[size - 1];
        array[size - 1] = null;
        size--;
        return item;
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
            if (!deque.get(i).equals(get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public T get(int index) {
        return array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                T item = array[index];
                index++;
                return item;
            }
        };
    }
}
