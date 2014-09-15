package graphs;

import java.util.*;
import java.lang.*;

public class Prims
{
    public static void perform( Graph g, Vertex s )
    {
        Queue<Vertex> queue = new PriorityQueue<>( g.vertices.length, new Comparator<Vertex>()
        {
            public int compare( Vertex v1, Vertex v2 )
            {
                return Double.compare( v1.minDistance, v2.minDistance );
            }
        });

        for ( Vertex v : g.vertices )
        {
            if ( v == s )
                continue;

            v.previous = null;
            v.minDistance = Double.POSITIVE_INFINITY;
            queue.add( v );
        }

        s.minDistance = 0;
        s.previous = null;
        queue.add( s );

        while( queue.peek() != null )
        {
            Vertex source = queue.remove();

            for ( Edge e : source.adjacent )
            {
                Vertex dest = e.dest;
                
                if ( e.weight < dest.minDistance )
                {
                    dest.minDistance = e.weight;
                    dest.previous = source;

                    // refresh position in priority queue
                    queue.remove( dest );
                    queue.add( dest );
                }
            }
        }

    }
}

