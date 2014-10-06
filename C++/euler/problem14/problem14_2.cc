#include <iostream>
#include <map>

long collatzLength( long x, long collatzArray[] );

int main()
{
    long collatzArray[1000000];
    memset( collatzArray, 0, sizeof(collatzArray) );
    long longest_index = 1;
    long longest_length = 1;
    long length;

    for ( int i = 0; i < 1000000; i++ )
    {
        length = collatzLength( i, collatzArray );

        collatzArray[i] = length;
        if ( length > longest_length )
        {
            longest_length = length;
            longest_index = i;
        }
    }

    std::cout << longest_index << std::endl;

    return 0;
}

long collatzLength( long x, long collatzArray[] )
{
    if ( x < 1000000 && collatzArray[x] != 0 )
    {
        return collatzArray[x];
    }

    long count = 1;
    while( x > 1 )
    {
        if ( x < 1000000 && collatzArray[x] != 0 )
        {
            return collatzArray[x] + count;
        }

        if ( x % 2 == 0 )
        {
            x = x >> 1;
        }
        else
        {
            x = 3*x + 1;
        }
        count++;
    }

    return count;
}
