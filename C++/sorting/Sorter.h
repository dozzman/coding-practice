// Sorter.h
// Template abstract class which contains all sorters
#pragma once
#include <vector>

template <typename T>
class Sorter
{
    public:
    virtual void sort( std::vector<T> &data ) = 0;

    void swap( std::vector<T> &data, int a1, int a2 )
    {
        T temp = data[a1];
        data[a1] = data[a2];
        data[a2] = temp;
    }
};
