package euler.problem8;

import java.io.*;
import java.util.*;
import java.lang.*;

public class Problem8
{
    public static void main( String [] args )
    {
        char [] longArray = new char[1000];
        BufferedReader in;
        int read = 0;
        int width = 13;
        try
        {
            in = new BufferedReader( new FileReader("data.txt") );
            read = in.read( longArray, 0, 1000 );
        }
        catch (IOException e)
        {
            System.out.println("Failed to read file !");
            System.exit(-1);
        }

        System.out.println(read);

        long old_total = 1;
        long total = 1;
        int pos = width-1;

        // set up initial totoal
        for ( int i = 0; i < width; i++ )
        {
            total *= Character.getNumericValue( longArray[i] );
        }
    
        old_total = total;

        // go through each substring of 13 numbers by multiplying by the next number
        // and deleting the last number. Like a pipeline.
        for ( int i = width; i < read; i++ )
        {
            try
            {
                total *= Character.getNumericValue( longArray[i] );
                total /= Character.getNumericValue( longArray[i - width] );
            }
            catch ( ArithmeticException e )
            {
                // zero has just left the pipeline, need to totally recalculate
                total = 1;
                for ( int j = i - (width - 1); j <= i; j++ )
                {
                    total *= Character.getNumericValue( longArray[j] );
                }
            }

            if ( total > old_total )
            {
                pos = i;
                old_total = total;
            }
        }

        System.out.println( old_total );

    }
}
