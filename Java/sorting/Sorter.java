package sorting;

import java.lang.*;
import java.util.*;

public abstract class Sorter
{
    public abstract <T extends Comparable<T>> void sort(T array []);

    // generic swapping function
    protected <T extends Comparable<T>> void swap(T array [], int elem1, int elem2)
    {
        T temp = array[elem1];
        array[elem1] = array[elem2];
        array[elem2] = temp;
        return;
    }
}
