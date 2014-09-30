// Problem9.cc
// Solution to project euler, problem 9

#include <tuple>
#include <iostream>

bool testPythagTriple( long i, long j, long k )
{
    long i_sq = i*i;
    long j_sq = j*j;
    long k_sq = k*k;

    if( i_sq + j_sq == k_sq || i_sq + k_sq == j_sq || j_sq + k_sq == i_sq )
    {
        return true;
    }

    return false;
}

// brute force!
std::tuple<long,long,long> testTuples()
{
    for ( long i = 0; i < 1000; i++ )
    {
        for ( long j = 0; j < 1000; j++ )
        {
            for ( long k = 0; k < 1000; k++ )
            {
                if ( i + j + k != 1000 || i == j || j == k || i == k )
                {
                    continue;
                }

                // std::cout << "Testing tuple " << i << " " << j << " " << k << std::endl;
                if ( testPythagTriple( i, j, k ) )
                {
                    return std::make_tuple( i, j, k );
                }
            }
        }
    }

    return std::make_tuple (0l,0l,0l);
}

int main()
{
    auto result = testTuples();

    std::cout << std::get<0>(result) * std::get<1>(result) * std::get<2>(result) << std::endl;
    return 0;
}
