// problem10.cc
// Solution to problem 10, projec euler

#include <iostream>
#include <vector>
#include <cmath>

std::vector<long> primes;

auto isPrime( long p ) -> bool
{
    long limit = sqrt(p);
    for ( auto elem : primes )
    {
        if ( elem > limit )
        {
            break;
        }

        if ( p % elem == 0 )
        {
            return false;
        }
    }

    primes.push_back( p );

    return true;
}

int main()
{
    primes.push_back(2);
    long total = 2;

    for ( long i = 3; i < 2000000; i += 2 )
    {
        if ( isPrime( i ) )
        {
            total += i;
        }
    }

    std::cout << total << std::endl;
    return 0;
}
