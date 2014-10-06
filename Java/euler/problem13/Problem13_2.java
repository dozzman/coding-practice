package euler.problem13;

import java.lang.*;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.io.*;
import java.math.*;

public class Problem13_2
{
    public static void main( String [] args )
    {
        try
        {
            BufferedReader reader = Files.newBufferedReader( FileSystems.getDefault().getPath("data.txt"), Charset.forName("UTF-8") );
            BigInteger result = BigInteger.ZERO;
            String line = null;
            
            int num = 0;
            while( ( line = reader.readLine() ) != null )
            {
                result = result.add( new BigInteger(line) ) ;
            }
            System.out.println(result.toString());
        }
        catch (IOException e)
        {
            System.out.println("Failed to read whole file!");
            return;
        }
    }
}
