package questions.chapter20.q1;

import java.lang.*;
import java.util.*;

public class Q1
{
    public static void main( String [] args )
    {
        int a = 12;
        int b = 15;

        System.out.println( sum2( a, b ) );
    }
    
    // function creates a vector of iniital length a and increment b, so that when the array is filled over capacity,
    // the new capacity will be a + b. Then I just return the capacity of the vector.

    public static int sum1( int a, int b )
    {
        Vector<Integer> vectorSum = new Vector<>(a,b);
        int [] arrayA = new int[a];

        for ( int i : arrayA )
        {
            vectorSum.add(Integer.valueOf( i ));
        }

        vectorSum.add(Integer.valueOf( 0 ));

        return vectorSum.capacity();
    }

    // just construct a full adder in software
    public static int sum2( int add, int carry )
    {
        if ( carry == 0 )
        {
            return add;
        }

        int add2 = add ^ carry;
        int carry2 = (add & carry) << 1;

        return sum2( add2, carry2 );
    }
}
