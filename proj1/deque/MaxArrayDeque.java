package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        T max = array[0];
        for (int i = 1; i < size; i++) {
            if (comparator.compare(max, array[i]) < 0) {
                max = array[i];
            }
        }
        return max;
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        T max = array[0];
        for (int i = 1; i < size; i++) {
            if (c.compare(max, array[i]) < 0) {
                max = array[i];
            }
        }
        return max;
    }
}
