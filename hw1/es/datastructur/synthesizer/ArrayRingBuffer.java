package es.datastructur.synthesizer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        //  Create new array with capacity elements.
        rb = (T[]) new Object[capacity];

        //  first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // Enqueue the item. Don't forget to increase fillCount and update
        //last.
        if (isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        }

        rb[last] = x;
        last += 1;
        fillCount += 1;
        if (last  == capacity()) {
            last = 0;
        }

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if(isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }

        int marker = first;
        first += 1;
        if (first == capacity()) {
            first = 0;
        }
        fillCount -= 1;
        return rb[marker];
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // Return the first item. None of your instance variables should
        //       change.
        return rb[first];
    }

    @Override
    public boolean equals (Object s) {
        ArrayRingBuffer<T> ss = (ArrayRingBuffer<T>)  s;
        ArrayList<T> a = new ArrayList<>();
        int count = 0;
        for (T item : rb) {
            count ++;
            if (count <= fillCount){
            a.add(item);
            }
        }
        ArrayList<T> b = new ArrayList<>();
        count = 0;
        for (T item : ss) {
            count += 1;
            if (count <= ss.fillCount) {
             b.add(item);
    }
}
        return a.equals(b);
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>(){
            private int count = 0;
            private int pos = first;

            @Override
            public boolean hasNext() {
                return fillCount - count > 0;
            }

            @Override
            public T next() {
                int returnIndex = pos;
                count += 1;
                pos += 1;

                if (pos == capacity()) {
                    pos = 0;
                }
                return rb[returnIndex];
            }
        };
    }




}

