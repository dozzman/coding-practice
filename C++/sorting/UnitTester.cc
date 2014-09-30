// UnitTester.h
// Contains the main function and runs the unit tests
// using google test

#include <gtest/gtest.h>
#include <iostream>
#include <ctime>
#include "BubbleSorter.h"
#include "MergeSorter.h"

namespace
{
    // The fixture for testing class Foo.
    class SortTest : public ::testing::Test
    {
        private:
        static const int MAX_VALUE = 1000;
        static const int VECTOR_SIZE = 1000;
        protected:
            // You can remove any or all of the following functions if its body
            // is empty.

            SortTest()
            {
                // constructor fills up the test vector with random integer values
                std::srand(time(nullptr));
                for ( int i = 0; i < VECTOR_SIZE; i++ )
                {
                    int newValue = std::rand() % MAX_VALUE;
                    test_data.push_back( newValue );
                }
            }

            std::vector<int> test_data;

            bool testSort()
            {
                std::vector<int>::iterator iter = test_data.begin();
                std::advance( iter, 1 );
                for( ; iter != test_data.end(); iter++ )
                {
                    if ( *iter < *std::prev(iter) )
                    {
                        return false;
                    }
                }
                
                return true;
            }

            void printData()
            {
                std::cout << "Test Data:\n";
                for ( auto elem : test_data )
                {
                    std::cout << elem << std::endl;
                }
            }

            void testSorter( Sorter<int> &sorter )
            {
                sorter.sort( test_data );
                ASSERT_EQ( true, testSort() ) << "Elements not sorted!\n";
            }
    };
    
    TEST_F(SortTest, BubbleSort)
    {
        BubbleSorter<int> sorter;
        testSorter( sorter );
    }

    TEST_F(SortTest, MergeSort)
    {
        MergeSorter<int> sorter;
        testSorter( sorter );
    }
}

int main(int argc, char **argv) {
    ::testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}
