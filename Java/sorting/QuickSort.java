package sorting;

import java.lang.*;
import java.util.*;

public class QuickSort<T extends Comparable<T>> extends Sorter<T>
{
    public String sortName() { return "Quick Sort"; }

    public void sort(T array [])
    {
        quickSort(array,0,array.length - 1);
    }

    private void quickSort(T array[], int left, int right)
    {
        if (left >= right)
            return;

        int mid = partition(array, left, right);
        quickSort(array, left, mid - 1);
        quickSort(array, mid + 1, right);
    }

    private int partition(T array[], int left, int right)
    {
        int pivot = right;
        int small_pointer = left;
        for(int i = left; i < right; i++)
        {
            if (array[i].compareTo(array[pivot]) < 0)
            {
                swap(array, small_pointer, i);
                small_pointer++;
            }
        }

        swap(array, pivot, small_pointer);

        return small_pointer;
    }
}
