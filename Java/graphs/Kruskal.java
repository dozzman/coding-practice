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

        int numOfSets = sets.size();
        for ( int i = 0; i < numOfSets; i++ )
        {
            Set<Vertex> currentSet = new HashSet<Vertex>();
            currentSet.add( g.vertices[i] );
            sets.add( currentSet );
        }

        for ( Edge e : edges )
        {
            edgeQueue.add( e );
        }
        
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

            Set<Vertex> unionSet = union( sourceSet, destSet );
            sourceSet = unionSet;
            sets.remove( destSet );
           
            numOfSets = sets.size();

            finalEdges.add( e );
        }

        // go through final edges and turn it into a graph, and return
        
        Edge [] finalEdgesArray = finalEdges.toArray(new Edge[0]);

        return new Graph( finalEdgesArray );
    }

    private static Set<Vertex> union( Set<Vertex> set1, Set<Vertex> set2 )
    {
        Set<Vertex> newSet = new HashSet<>();

        Iterator<Vertex> set1Iter = set1.iterator();
        Iterator<Vertex> set2Iter = set2.iterator();

        while ( set1Iter.hasNext() )
        {
            newSet.add( set1Iter.next() );
        }

        while ( set2Iter.hasNext() )
        {
            newSet.add( set2Iter.next() );
        }
        
        return newSet;
    }
}
