package graphs;

import java.util.*;
import java.lang.*;
import org.junit.*;
import static org.junit.Assert.*;

public class UnitTester
{
    // test graph
    Graph g;

    public UnitTester()
    {
        Vertex [] vs = new Vertex[9];

        for(int i = 0; i < vs.length; i++ )
        {
            vs[i] = new Vertex(new String(Integer.toString(i)));
            System.out.println(vs[i]);
        }

        Edge [] es = new Edge[17];

        es[0] = new Edge(vs[0], vs[1], 4.0);
        es[1] = new Edge(vs[0], vs[2], 7.0);
        es[2] = new Edge(vs[0], vs[3], 4.0);
        es[3] = new Edge(vs[1], vs[2], 2.0);
        es[4] = new Edge(vs[2], vs[3], 3.0);
        es[5] = new Edge(vs[1], vs[4], 4.0);
        es[6] = new Edge(vs[2], vs[4], 6.0);
        es[7] = new Edge(vs[3], vs[4], 7.0);
        es[8] = new Edge(vs[1], vs[5], 14.0);
        es[9] = new Edge(vs[3], vs[7], 10.0);
        es[10] = new Edge(vs[4], vs[5], 4.0);
        es[11] = new Edge(vs[4], vs[6], 9.0);
        es[12] = new Edge(vs[4], vs[7], 7.0);
        es[13] = new Edge(vs[5], vs[6], 5.0);
        es[14] = new Edge(vs[6], vs[7], 8.0);
        es[15] = new Edge(vs[5], vs[8], 10.0);
        es[16] = new Edge(vs[7], vs[8], 16.0);

        g = new Graph(vs, es);
    }
}
