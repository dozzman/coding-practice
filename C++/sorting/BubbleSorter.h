// BubbleSorter.cc
// Sorter that implements the bubble sort

#pragma once

#include "Sorter.h"

template <typename T>
class BubbleSorter : public Sorter<T>
{
    public:
    virtual void sort( std::vector<T> &data ) override
    {
        int length = data.size();
        for ( int i = 0; i < length; i++ )
        {
            for ( int j = 1; j < length; j++ )
            {
                if ( data[j] < data[j-1] )
                {
                    Sorter<T>::swap( data, j, j-1 );
                }
            }
        }
    }
};
