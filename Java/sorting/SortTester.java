package sorting;

import java.lang.*;
import java.util.*;

public class SortTester
{
    public static void main(String [] args)
    {
        Integer array[] = SortTester.randomIntArray(0,10,10);    
        for( Integer i : array)
        {
            System.out.println(i);
        }

        Sorter bubbleSorter = new BubbleSort();
        bubbleSorter.<Integer>sort(array);
        for( Integer i : array)
        {
            System.out.println(i);
        }
    }

    public static Integer[] randomIntArray(int min, int max, int length)
    {
        Integer array[] = new Integer[length];
        for( int i = 0; i < length; i++)
        {
            array[i] = Integer.valueOf((int)Math.floor(min + Math.random()*(max - min)));
        }

        return array;
    }
}
