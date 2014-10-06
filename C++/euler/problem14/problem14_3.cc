#include <iostream>
#include <thread>
#include <atomic>
#include <mutex>
#include <vector>

const int POOL_SIZE = 8;

long collatzLength( long x );
void collatzWorker();

std::atomic_long collatz_ticket;
long final_length = 0l;
long final_index = 0l;
std::mutex longest_mutex;

int main()
{
    std::vector<std::thread> thread_pool;
    std::atomic_store( &collatz_ticket, 0l );

    for ( int i = 0; i < POOL_SIZE; i++ )
    {
       thread_pool.push_back( std::thread(collatzWorker) );
    }

    for ( auto& thread : thread_pool )
    {
        thread.join();
    }
    
    // locking for brevity rather than safety
    longest_mutex.lock();
    std::cout << final_index << std::endl;
    longest_mutex.unlock();

    return 0;
}

long collatzLength( long x )
{
    long count = 1;
    while( x > 1 )
    {
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

void updateCollatzLongest( long index, long length )
{
    longest_mutex.lock();
    if ( length > final_length )
    {
        final_length = length;
        final_index = index;
    }
    longest_mutex.unlock();
}

void collatzWorker()
{
    long longest_index = 0;
    long longest_length = 0;
    long my_ticket;

    while( (my_ticket = std::atomic_fetch_add( &collatz_ticket, 1l ) ) < 1000000 )
    {
        long length = collatzLength( my_ticket );

        if ( length > longest_length )
        {
            longest_length = length;
            longest_index = my_ticket;
        }
    }
    updateCollatzLongest(longest_index, longest_length);
}
