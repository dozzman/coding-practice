#include <iostream>
#include <map>

long collatzLength( long x, std::map<long, long> &collatzMap );

int main()
{
    std::map<long, long> collatzMap;
    long longest_index = 1;
    long longest_length = 1;
    long length;

    for ( int i = 0; i < 1000000; i++ )
    {
        length = collatzLength( i, collatzMap );

        collatzMap[i] = length;
        if ( length > longest_length )
        {
            longest_length = length;
            longest_index = i;
        }
    }

    std::cout << longest_index << std::endl;

    return 0;
}

long collatzLength( long x, std::map<long, long> &collatzMap )
{
    std::map<long,long>::iterator iter;
    if ( (iter = collatzMap.find( x )) != collatzMap.end() )
    {
        return iter->second;
    }
        
    long count = 1;
    while( x > 1 )
    {
        if ( (iter = collatzMap.find( x )) != collatzMap.end() )
        {
            return (iter->second + count);
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
