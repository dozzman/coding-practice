package sorting;

import java.util.*;
import java.lang.*;

public class MaxHeap<T extends Comparable<T>> extends Sorter<T>
{
    private T [] heapArray;
    private int size;

    public String sortName() { return "Max Heap (sort)"; } 

    public int size() { return size; }

    private int left( int index )
    {
        return (index << 1) + 1;
    }

    private int right ( int index )
    {
        return (index << 1) + 2;
    }

    private int parent ( int index )
    {
        if ( index % 2 == 0 )
        {
            return (index - 2) >> 2;
        }
        else 
        {
            return (index - 1) >> 2;
        }
    }

    private void heapify( int index )
    {
        int left = left( index );
        int right = right ( index );
        int largest;

        if ( left < size && heapArray[index].compareTo( heapArray[left] ) < 0 )
        {
            largest = left;
        }
        else
        {
            largest = index;
        }
        if ( right < size && heapArray[largest].compareTo( heapArray[right] ) < 0 )
        {
            largest = right;
        }
        
        if ( index != largest )
        {
            swap(heapArray, index, largest);
            heapify( largest );
        }
    }

    private void buildMaxHeap( T [] array )
    {
        heapArray = array;
        size = heapArray.length;

        for ( int i = (size/2); i >= 0; i-- )
        {
            heapify( i );
        }
    }

    @Override
    public void sort( T [] array )
    {
        buildMaxHeap( array );

        for ( int i = size - 1; i > 0; i-- )
        {
            swap( heapArray, i, 0 );
            size--;
            heapify( 0 );
        }
    }
}
