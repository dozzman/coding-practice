package sorting;

import java.lang.*;
import java.util.*;

public class InsertionSort extends Sorter
{
    public <T extends Comparable<T>> void sort(T array [])
    {
        int length = array.length;

        for(int i = 1; i < array.length; i++)
        {
            for(int j = i; j > 0; j--)
            {
                if(array[j].compareTo(array[j-1]) < 0)
                {
                    swap(array,j-1,j);
                }
            }
        }
    }
}
