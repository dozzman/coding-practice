package sorting;

import java.lang.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class UnitTester
{
    private static final int DEFAULT_START = 0;
    private static final int DEFAULT_END = 1000;
    private static final int DEFAULT_SIZE = 1000;

    // bubbleSort tester
    @Test
    public void bubbleSortTest()
    {
        Sorter bubbleSorter = new BubbleSort();
        Integer [] array = randomIntArray(DEFAULT_START, DEFAULT_END, DEFAULT_SIZE); 

        bubbleSorter.sort( array );

        assertTrue( "Failure - bubble test failed", testOrder( array ) );
        System.out.println("Bubble sort test success!");
    }

    // insertionSort tester
    @Test
    public void insertionSortTest()
    {
        Sorter insertionSorter = new InsertionSort();
        Integer [] array = randomIntArray(DEFAULT_START, DEFAULT_END, DEFAULT_SIZE); 

        insertionSorter.sort( array );

        assertTrue( "Failure - insertion sort test failed", testOrder( array ) );
        System.out.println("Insertion sort test success!");
    }

    // mergeSort tester
    @Test
    public void mergeSortTest()
    {
        Sorter mergeSorter = new MergeSort();
        Integer [] array = randomIntArray(DEFAULT_START, DEFAULT_END, DEFAULT_SIZE); 

        mergeSorter.sort( array );

        assertTrue( "Failure - merge sort test failed", testOrder( array ) );
        System.out.println("Merge sort test success!");
    }

    // quickSort tester
    @Test
    public void quickSortTest()
    {
        Sorter quickSorter = new QuickSort();
        Integer [] array = randomIntArray(DEFAULT_START, DEFAULT_END, DEFAULT_SIZE); 

        quickSorter.sort( array );

        assertTrue( "Failure - quick test failed", testOrder( array ) );
        System.out.println("Quick sort test success!");
    }

    public Integer[] randomIntArray(int min, int max, int length)
    {
        Integer array[] = new Integer[length];
        for( int i = 0; i < length; i++)
        {
            array[i] = Integer.valueOf((int)Math.floor(min + Math.random()*(max - min)));
        }

        return array;
    }

    public boolean testOrder(Integer array[])
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
