
package sorting;

import java.lang.*;
import java.util.*;

public class MergeSort<T extends Comparable<T>> extends Sorter<T>
{
    public String sortName() { return "Merge Sort"; }

    public void sort(T array [])
    {
        mergeSort(array,0,array.length-1);
    }
    
    private void mergeSort(T array [], int left, int right)
    {
        
        if(left >= right)
        {
            return;
        }

        int mid = (int)Math.ceil((double)(left + right) / 2); // MUST CONVERT TO DOUBLE OR INTEGER DIVISION IS ALREADY A FLOOR OPERATION
        mergeSort(array, left, mid - 1);
        mergeSort(array, mid, right);
        merge(array, left, mid, right);
    }

    private void merge(T array [], int left, int middle, int right)
    {
        T copy[] = Arrays.copyOfRange(array,left,right+1); // COPY IS EXCLUSIVE OF RIGHTMOST ARRAY ELEMENT

        // perform merge
        int l_len = middle - left;
        int r_len = right - middle + 1;
        int l_index = 0, r_index = l_index + l_len, index = left;
        int l_end = l_index + l_len - 1, r_end = r_index + r_len - 1;
        while(l_index <= l_end && r_index <= r_end)
        {
            if(copy[l_index].compareTo(copy[r_index]) < 0)
            {
                array[index] = copy[l_index];
                l_index++;
            }
            else
            {
                array[index] = copy[r_index];
                r_index++;
            }
            index++;
        }

        // reched the end of either the left or right list

        if (l_index > l_end)
        {
            while(r_index <= r_end)
            {
                array[index] = copy[r_index];
                r_index++;
                index++;
            }
        }
        else
        {
            while(l_index <= l_end)
            {
                array[index] = copy[l_index];
                l_index++;
                index++;
            }
        }
    }
}
