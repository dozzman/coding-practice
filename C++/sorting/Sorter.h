// Sorter.h
// Template abstract class which contains all sorters

#include <array>

template <typename T>
class Sorter
{
    public:
    virtual void sort( std::vector<T> data );
   
    void swap( std::vector<T> data, int a1, int a2 ) final
    {
        T temp = data[a1];
        data[a1] = data[a2];
        data[a2] = temp;
    }
};
