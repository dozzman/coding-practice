package graphs;

import java.util.*;
import java.lang.*;

public class BreadthFirstSearch
{
    static int white = 0, grey = 1, black = 2;     // easier than making an enum which I have to type the fully qualified name of every single time
    public static void perform(Graph g, Vertex s)
    {
        Queue<Vertex> queue = new LinkedList<>();

        for (Vertex v : g.vertices)
        {
            if ( v == s )
            {
                continue;
            }

            v.colour = white;
            v.minDistance = Double.POSITIVE_INFINITY;
            v.previous = null;
        }

        s.minDistance = 0;
        s.previous = null;
        s.colour = grey;
        queue.add(s);

        while(queue.peek() != null)
        {
            Vertex v = queue.remove();

            for ( Edge e : v.adjacent )
            {
                if ( e.dest.colour == white ) // dont check grey nodes in BFS since grey nodes have already been accessed & BFS inherently reaches them via minimum number of hops. Check greys with dijkstra's because edges have weights
                {
                    double length = v.minDistance + 1.0;
                    if ( length < e.dest.minDistance )
                    {
                        e.dest.minDistance = length;
                        e.dest.previous = v;
                    }

                    e.dest.colour = grey;
                    queue.add(e.dest);
                }
            }

            v.colour = black;
        }
    }
}
