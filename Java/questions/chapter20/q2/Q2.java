package questions.chapter20.q2;

import java.lang.*;
import java.util.*;

public class Q2
{
    public static void main( String [] args )
    {
        int [] cardArray = new int[52];

        for ( int i = 0; i < 52; i++ )
        {
            cardArray[i] = i;
        }

        for ( int i = 0; i < 52; i++ )
        {
            int random = (int)(Math.random()*(51.0 - i));

            int temp = cardArray[51 - i];
            cardArray[51 - i] = cardArray[random];
            cardArray[random] = temp;
        }
    }
}
