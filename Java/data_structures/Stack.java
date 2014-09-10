package data_structures;

import java.lang.*;
import java.util.*;

public class Stack<T extends Object>
{
    // constants
    public static final int DEFAULT_SIZE = 100;
    
    // push operation
    // returns true if successful, false otherwise
    public boolean push(T obj)
    {
        if (top < array.length-1)
        {
            top++;
            array[top] = obj;
            return true;
        }
        
        return false;
    }

    // pop operation
    // returns top of stack, or null otherwise
    public T pop()
    {
        if(top >= 0)
        {
            T obj = array[top];
            top--;
            return obj;
        }

        return null;
    }

    // default constructor makes a stack size DEFAULT_SIZE
    public Stack()
    {
        array = (T[]) new Object[DEFAULT_SIZE];
        top = -1;
    }

    public Stack(int size)
    {
        array = (T[]) new Object[size];
        top = -1;
    }

    private T array[];  // the stack implemented as an array
    private int top;    // top of the stack, -1 meaning it is empty
    public int size() { return top + 1; };

}
