package sorting;

import java.lang.*;
import java.util.*;

public abstract class Sorter<T extends Comparable<? extends T>>
{
    public abstract void sort(T array []);

    public abstract String sortName();

    // generic swapping function
    protected void swap(T array [], int elem1, int elem2)
    {
        T temp = array[elem1];
        array[elem1] = array[elem2];
        array[elem2] = temp;
        return;
    }
}
