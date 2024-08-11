package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] array;
    private int size;

    public ArrayDeque() {
        this.array = (T[]) new Object[100];
        this.size = 0;
    }

    @Override
    public void addFirst(T item) {
        T[] newArray = (T[]) new Object[size * 2];
        newArray[0] = item;
        System.arraycopy(array, 0, newArray, 1, size);
        array = newArray;
    }

    @Override
    public void addLast(T item) {
        if (size == array.length) {
            T[] newArray = (T[]) new Object[size * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
        this.array[size] = item;
        this.size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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
        T item = array[0];
        T[] newArray = (T[]) new Object[size];
        System.arraycopy(array, 1, newArray, 0, size - 1);
        array = newArray;
        return item;
    }

    @Override
    public T removeLast() {
        T item = array[size - 1];
        array[size - 1] = null;
        size--;
        return item;
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
                return index < size - 1;
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
