package graphs;

import java.util.*;
import java.lang.*;

public class Dijkstra
{
    static int white = 0, grey = 1, black = 2;
    
    public static void perform(Graph g, Vertex s)
    {
        Queue<Vertex> queue = new PriorityQueue<>(g.vertices.length, new VertexDistanceComparator());

        for ( Vertex v : g.vertices )
        {
            if (v == s)
                continue;

            v.minDistance = Double.POSITIVE_INFINITY;
            v.previous = null;
            v.colour = white;
            queue.add( v );
        }

        s.minDistance = 0;
        s.previous = null;
        s.colour = grey;
        queue.add( s );

        while (queue.peek() != null)
        {
            Vertex v = queue.remove();

            for ( Edge e : v.adjacent )
            {
                Vertex dest = e.dest;
                if ( dest.colour != black )
                {
                    double new_distance = v.minDistance + e.weight;
                    if( new_distance < dest.minDistance )
                    {
                        queue.remove( dest );
                        dest.minDistance = new_distance;
                        dest.previous = v;
                        queue.add( dest );
                    }

                    v.colour = grey;
                }
            }
            
            v.colour = black;
        }

    }
}
