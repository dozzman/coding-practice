package euler.problem13;

import java.lang.*;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.io.*;

public class Problem13
{

    public static void main ( String [] args )
    {
        System.out.println( "Hello, World!" );
        long [] result;
        long [][] data;

        try
        {
            data = Problem13.loadData( "data.txt" );
        }
        catch (IOException e)
        {
            System.out.println("Failed to load data from file");
            return;
        }

        result = calculateSum( data );
        
        for ( long i : result )
        {
            System.out.print(i);
        }

        System.out.println();
        return;
    }

    public static long[][] loadData( String filename ) throws IOException
    {
        Charset charset = Charset.forName("UTF-8");
        Path path = FileSystems.getDefault().getPath("data.txt");
        BufferedReader reader = Files.newBufferedReader( path, charset );
        
        String line = null;

        long [][] data = new long[100][50];
        int num = 0;

        while (( line = reader.readLine()) != null )
        {
            for( int cNum = 0; cNum < line.length(); cNum++ )
            {
                int digit = Character.digit( line.charAt( cNum ), 10 );
                data[num][cNum] = digit;   
            }
            num++;
        }

        return data;
    }

    public static long[] calculateSum(long [][] data )
    {
        long [] result = new long[data[0].length];
        long [] carry = new long[data[0].length];
        boolean did_carry = true; 

        // do initial sum
        for( int i = 0; i < data[0].length; i++ )
        {
            for ( int j = 0; j < data.length; j++ )
            {
                result[i] += data[j][i];
            }
        }

        System.out.println("Initial sum = ");
        for (long i : result)
        {
            System.out.print( i + ", " );
        }

        System.out.println();
        System.out.println();

        while ( did_carry )
        {   
            did_carry = false;
            // add the carry's
            for ( int i = 0; i < result.length; i++ )
            {
                result[i] += carry[i];
                carry[i] = 0;
            }

            System.out.println("New sums = ");
            for( long i : result )
            {
                System.out.print( i + ", " );
            }
            System.out.println();


            // calculate the new carry's right to left
            for ( int i = result.length - 1; i > 0; i-- )
            {
                if ( result[i] >= 10 )
                {
                    did_carry = true;
                }

                while( result[i] >= 100 && i >= 2 )
                {
                    result[i] -= 100;
                    carry[i-2]++;
                }

                while( result[i] >= 10 )
                {
                    result[i] -= 10;
                    carry[i-1]++;
                }
            }

            System.out.println("Carry's at this point = ");
            for( long i : carry )
            {
                System.out.print( i + ", " );
            }
            System.out.println();
            System.out.println();

            // if leftmost value has overflown, shift everything down to the right
            if( result[0] >= 10 )
            {
                long final_carry = 0;
                while( result[0] >= 10 )
                {
                    result[0] -= 10;
                    final_carry++;
                }

                for( int i = result.length - 1; i > 0; i-- )
                {
                    result[i] = result[i-1];
                    carry[i] = carry[i-1];
                }

                result[0] = final_carry;
                carry[0] = 0;
            }
        }

        return result;
    }
}
    
