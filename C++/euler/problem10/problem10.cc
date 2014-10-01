// problem10.cc
// Solution to problem 10, projec euler

#include <iostream>
#include <vector>

std::vector<long> primes;

auto isPrime( long p ) -> bool
{
    for ( auto elem : primes )
    {
        if ( p % elem == 0 )
        {
            return false;
        }
    }

    // std::cout << p << " is prime" << std::endl;
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
