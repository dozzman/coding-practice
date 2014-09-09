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
            System.out.print(i +", " );
            System.out.println();
        }

        Sorter bubbleSorter = new BubbleSort();
        Sorter insertionSorter = new InsertionSort();
        Sorter mergeSorter = new MergeSort();
        mergeSorter.<Integer>sort(array);

        System.out.println("After sorting");

        for( Integer i : array)
        {
            System.out.print(i +", " );
            System.out.println();
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

    public static boolean testOrder(Integer array[])
    {
        int length = array.length;
        for(int i = 1; i < length; i++)
        {
            if(array[i].compareTo(array[i-1]) < 0)
            {
                return false;
            }
        }

        return true;
    }
}
