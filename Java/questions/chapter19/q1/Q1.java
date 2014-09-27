package questions.chapter19;

import java.lang.*;
import java.util.*;

public class Q1
{
    public static void main(String [] args )
    {
        int a = (int)(Math.random()*1000);
        int b = (int)(Math.random()*1000);
        System.out.println("a = " + a + ", b = " + b );
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.println("a = " + a + ", b = " + b );
    }
}
