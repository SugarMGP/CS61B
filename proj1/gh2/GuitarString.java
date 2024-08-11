package gh2;

import deque.ArrayDeque;
import deque.Deque;

public class GuitarString {

    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    private final Deque<Double> buffer;
    private final int capacity;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayDeque<>();
        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        for (int i = 0; i < capacity; i++) {
            buffer.removeLast();
        }
        for (int i = 0; i < capacity; i++) {
            buffer.addLast(Math.random() - 0.5);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double newSample = (buffer.get(0) + buffer.get(1)) * 0.5 * DECAY;
        buffer.removeFirst();
        buffer.addLast(newSample);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }
}
