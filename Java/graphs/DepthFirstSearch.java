package graphs;

import java.util.*;
import java.lang.*;

public class DepthFirstSearch
{
    static int white = 0, grey = 1, black = 2;
    public static void perform(Graph g, Vertex s)
    {
        for ( Vertex v : g.vertices )
        {
            v.colour = white;
            v.minDistance = Double.POSITIVE_INFINITY;
            v.previous = null;
        }
        
        depthFirstSearch(s);
        
        for ( Vertex v : g.vertices )
        {
            if ( v.colour == white )
            {
                depthFirstSearch( v );
            }
        }
    }

    public static void depthFirstSearch(Vertex s)
    {
        s.colour = grey;
        for ( Edge e : s.adjacent )
        {
            Vertex v = e.dest;
            if ( v.colour == white )
            {
                v.previous = s;
                DepthFirstSearch.depthFirstSearch(v);
            }
        }

        s.colour = black;
    }
}
