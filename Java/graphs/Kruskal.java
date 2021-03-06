package graphs;

import java.util.*;
import java.lang.*;

public class Kruskal
{
    public static Graph perform( Graph g )
    {
        List< Set<Vertex> > sets = new LinkedList<>();
        Edge [] edges = g.edges();
        List<Edge> finalEdges = new LinkedList<>();
        Queue<Edge> edgeQueue = new PriorityQueue<>(edges.length, new Comparator<Edge>()
        {
            public int compare(Edge e1, Edge e2)
            {
                return Double.compare(e1.weight, e2.weight);
            }
        });

        for ( int i = 0; i < g.vertices.length; i++ )
        {
            Set<Vertex> currentSet = new HashSet<Vertex>();
            currentSet.add( g.vertices[i] );
            sets.add( currentSet );
        }

        for ( Edge e : edges )
        {
            edgeQueue.add( e );
        }
        
        int numOfSets = sets.size();
        while ( edgeQueue.peek() != null && numOfSets > 1 )
        {
            Edge e = edgeQueue.remove();
            Set<Vertex> sourceSet = null;
            Set<Vertex> destSet = null;

            for ( Set<Vertex> s : sets )
            {
                if ( s.contains( e.source ) )
                {
                    sourceSet = s;
                }

                if ( s.contains( e.dest ) )
                {
                    destSet = s;
                }
            }

            if ( sourceSet == destSet )
            {
                continue;
            }
            
            // union the two sets together
            sourceSet.addAll( destSet );
            sets.remove( destSet );
           
            numOfSets = sets.size();

            finalEdges.add( e );
        }

        // go through final edges and turn it into a graph, and return
        
        Edge [] finalEdgesArray = finalEdges.toArray(new Edge[0]);

        return new Graph( finalEdgesArray );
    }
}
