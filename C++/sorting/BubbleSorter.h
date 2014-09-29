// BubbleSorter.cc
// Sorter that implements the bubble sort

#include <vector>
#include "Sorter.h"

template <typename T>
class BubbleSorter : public Sorter<T>
{
    public:
    virtual void sort( std::vector<T> data ) override
    {
        int length = data.length();
        for ( int i = 0; i < length; i++ )
        {
            for ( int j = 1; j < length; j++ )
            {
                if ( data[j] < data[j-1] )
                {
                    swap( data, j, j-1 );
                }
            }
        }
    }
};
