package data_structures;

import java.lang.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class UnitTester
{
    // Stack Tests
    // This particular test is very verbose and tests most corner cases of the stack,
    // in the interest of my own time, I won't be making such rigorous tests in the future.
    // Although it's good practise for now.
    @Test
    public void stackTests()
    {
        Stack<Integer> stack = new Stack<>(100);
        assertEquals("Failure - Stack size is not 0 at startup", stack.size(), 0);

        Integer random = Integer.valueOf((int)(Math.random() * 100));
        assertTrue("Failure - push returned false", stack.push(random));
        assertEquals("Failure - Stack size did not increase with push command", 1, stack.size());

        Integer random_returned = stack.pop();

        assertNotNull("Failure - Pop returned null pointer", random_returned);
        assertEquals("Failure - popped value different from initial value", random_returned.intValue(), random.intValue());
        assertEquals("Failure - size is not 0 after pop", 0, stack.size());

        random_returned = stack.pop();

        assertNull("Failure - should be null", random_returned);

        for(int i = 0; i < 100; i++)
        {
            // fill up the stack
            random = Integer.valueOf((int)(Math.random() * 100));
            assertTrue("Failure - push retuened false", stack.push(random));
            assertEquals("Failure - stack size incorrect", i+1, stack.size());
        }
        
        assertEquals("Failure - stack size is wrong", 100, stack.size());
        
        random = Integer.valueOf((int)(Math.random() * 100));
        assertFalse("Failure - succeeded to push an empty stack", stack.push(random));
    }
}
