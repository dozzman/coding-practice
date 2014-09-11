package graphs;

import java.util.*;
import java.lang.*;

public class Graph
{
    Vertex [] vertices;
    
    // returns all undirected edges in this graph
    public Edge [] edges()
    {
        Map<Edge,Boolean> edges = new HashMap<>();

        for ( Vertex v : vertices )
        {
            for ( Edge e : v.adjacent )
            {
                edges.put(e,Boolean.TRUE);
            }
        }

        return edges.keySet().toArray(new Edge [edges.size()]);
    }

    public Graph(Vertex [] vs, Edge [] es)
    {
        vertices = vs;

        // duplicate and reverse the edges approprately within each vertex
        
        Map<Vertex,Integer> edgeCount = new HashMap<>();
        Map<Vertex,Integer> adjacentPointers = new HashMap<>();

         for ( Vertex v : vs )
         {
             edgeCount.put(v, Integer.valueOf(0));
             adjacentPointers.put(v, Integer.valueOf(0));
         }
         
         for ( Edge e : es )
         {
             edgeCount.put(e.source, edgeCount.get( e.source ) + 1 );
             edgeCount.put(e.dest, edgeCount.get( e.dest ) + 1 );
         }
 
         for ( Vertex v : vs )
         {
             v.adjacent = new Edge [edgeCount.get( v )];
         }
 
         for ( Edge e : es )
         {
             Edge reverse_e = new Edge(e.dest, e.source, e.weight);
             e.source.adjacent[adjacentPointers.get( e.source )] = e;
             e.dest.adjacent[adjacentPointers.get( e.dest )] = reverse_e;
             adjacentPointers.put( e.source, adjacentPointers.get( e.source ) + 1 );
             adjacentPointers.put( e.dest, adjacentPointers.get( e.dest ) + 1 );
         }
    }
}
