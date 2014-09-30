// MergeSorter.h
// implements merge sort

#pragma once
#include <iostream>
#include "Sorter.h"

template <typename T>
class MergeSorter : public Sorter<T>
{
    public:
    virtual void sort( std::vector<T> &data ) override
    {
        mergeSort( data, 0, data.size()-1 );        
    }

    void mergeSort( std::vector<T> &data, int left, int right )
    {
        if ( left >= right )
            return;

        int middle = (right+left)/2;
        mergeSort( data, left, middle );
        mergeSort( data, middle+1, right );
        merge( data, left, middle, right );
    }

    void merge( std::vector<T> &data, int left, int middle, int right )
    {
        int left_length = middle - left + 1;
        int left_pointer = 0;
        int right_pointer = middle + 1;
        int current_pointer = left;

        std::vector<T> left_copy;

        // copy the left side of the array
        for ( int i = left; i < left + left_length; i++ )
        {
            left_copy.push_back( data[i] );
        }

        while ( left_pointer < left_length && right_pointer <= right )
        {
            if( left_copy[left_pointer] < data[right_pointer] )
            {
                data[current_pointer] = left_copy[left_pointer];
                left_pointer++;
            }
            else
            {
                data[current_pointer] = data[right_pointer];
                right_pointer++;
            }
            current_pointer++;
        }

        while ( left_pointer < left_length )
        {
            data[current_pointer] = left_copy[left_pointer];
            left_pointer++;
            current_pointer++;
        }
    }
};
