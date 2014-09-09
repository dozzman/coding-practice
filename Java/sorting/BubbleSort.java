package sorting;

import java.lang.*;
import java.util.*;

public class BubbleSort extends Sorter
{
    public <T extends Comparable<T>> void sort(T array [])
    {
        boolean swapped = true;
        while (swapped == true)
        {
            swapped = false;
            for(int i = 1; i < array.length; i++)
            {
                // javadocs gives the relation as { (x,y) s.t. x.compareTo(y) <= 0}, however I am making
                // it { (x,y) s.t. x.compareTo(y) < 0 } so that were not swapping equal values
                if (array[i].compareTo(array[i-1]) < 0)
                {
                    // swap the values
                    swap(array, i, i-1);
                    swapped = true;
                }
            }
        }
    }
}


