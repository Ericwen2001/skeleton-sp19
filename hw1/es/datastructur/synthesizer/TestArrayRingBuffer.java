package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(3);
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertTrue(arb.isFull());
        assertEquals(1, arb.dequeue());
        assertFalse(arb.isFull());
        assertEquals(2, arb.peek());
        arb.enqueue(4);
        assertTrue(arb.isFull());

    }
    @Test
    public void testEquals() {
        ArrayRingBuffer arb1 = new ArrayRingBuffer(4);
        arb1.enqueue(1);
        arb1.enqueue(2);
        arb1.enqueue(3);
        ArrayRingBuffer arb2 = new ArrayRingBuffer(4);
        arb2.enqueue(1);
        arb2.enqueue(1);
        arb2.enqueue(2);
        arb2.enqueue(3);
        arb2.dequeue();
        arb1.equals(arb2);
        assertTrue(arb1.equals(arb2));
    }

}
