package data_structures;

import java.lang.*;
import java.util.*;

public class Queue<T extends Object>
{
    private static final int DEFAULT_SIZE = 100;
    
    public boolean enqueue(T obj)
    {
        if ( length < array.length )
        {
            if ( tail == array.length - 1 )
            {
                tail = 0;
            }
            else
            {
                tail++;
            }

            array[tail] = obj;
            length++;
            return true;
        }
        
        return false;
    }

    public T dequeue()
    {
        if ( length > 0 )
        {
            T obj = array[head];
            if ( head == array.length - 1 )
            {
                head = 0;
            }
            else
            {
                head++;
            }
            length--;
            return obj;
        }

        return null;
    }
    
    public int length() { return length; }

    public Queue()
    {
        array = (T[]) new Object[DEFAULT_SIZE];
        head = 0;
        tail = -1;
        length = 0;
    }

    public Queue(int size)
    {
        array = (T[]) new Object[size];
        head = 0;
        tail = -1;
        length = 0;
    }

    private T[] array;
    private int head;
    private int tail;
    private int length;
    

}
