// UnitTester.h
// Contains the main function and runs the unit tests
// using google test

#include <gtest/gtest.h>
#include <iostream>
#include "BubbleSorter.h"

namespace
{
    // The fixture for testing class Foo.
    class SortTest : public ::testing::Test
    {
        protected:
            // You can remove any or all of the following functions if its body
            // is empty.

            SortTest()
            {
                // constructor fills up the test vector with random integer values
                for ( int i = 0; i < 1000; i++ )
                {
                    int newValue = std::rand() % 1000;
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
    };

    // Tests that the Foo::Bar() method does Abc.
    TEST_F(SortTest, BubbleSort)
    {
        BubbleSorter<int> bubbleSorter;
        bubbleSorter.sort( test_data );
        ASSERT_EQ( 0, testSort() ) << "Elements not sorted!\n";
    }
}

int main(int argc, char **argv) {
    ::testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}
