package sorting;

import java.lang.*;
import java.util.*;

public abstract class Sorter
{
    abstract void sort(Comparable<? super Object> array []);

    // generic swapping function
    void swap(Comparable<? super Object> array [], int elem1, int elem2)
    {
        Comparable<? super Object> temp = array[elem1];
        array[elem1] = array[elem2];
        array[elem2] = temp;
        return;
    }
}
