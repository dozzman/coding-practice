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
        testSorter( new BubbleSort<Integer>() );
    }

    // insertionSort tester
    @Test
    public void insertionSortTest()
    {
        testSorter( new InsertionSort<Integer>() );
    }

    // mergeSort tester
    @Test
    public void mergeSortTest()
    {
        testSorter( new MergeSort<Integer>() );
    }

    // quickSort tester
    @Test
    public void quickSortTest()
    {
        testSorter( new QuickSort<Integer>() );
    }

    // max-heap sort tester
    @Test
    public void maxHeapTest()
    {
        testSorter( new MaxHeap<Integer>() );
    }

    public void testSorter( Sorter<Integer> sorter )
    {
        System.out.println( "Now testing " + sorter.sortName() + "..." );
        Integer [] array = randomIntArray( DEFAULT_START, DEFAULT_END, DEFAULT_SIZE );

        sorter.sort( array );

        assertTrue( "Failure - " + sorter.sortName() + " test failed!", testOrder( array ) );
        System.out.println( sorter.sortName() + " test succeeded!" );
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
