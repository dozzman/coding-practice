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

    public Vertex vertex(int vertNum)
    {
        String vertString = Integer.toString(vertNum);
        for( Vertex v : vertices )
        {
            if ( vertString.equals( v.name ) )
            {
                return v;
            }
        }
        
        return null;
    }

    public boolean containsEdge(Vertex v1, Vertex v2)
    {
        for ( Vertex v : vertices )
        {
            if ( v == v1 )
            {
                for ( Edge e : v.adjacent )
                {
                    if ( e.dest == v2 )
                    {
                        return true;
                    }
                }
                break;
            }
        }

        return false;
    }

    public Graph(Vertex [] orig_vs, Edge [] orig_es )
    {
        Vertex [] vs = new Vertex[ orig_vs.length ];
        System.arraycopy( orig_vs, 0, vs, 0, orig_vs.length );
        Edge [] es = new Edge[ orig_es.length ];
        System.arraycopy( orig_es, 0, es, 0, orig_es.length );
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

    public Graph( Edge [] orig_es )
    {
        // extract all vertices from edges
        Edge [] es = new Edge[ orig_es.length ];
        System.arraycopy( orig_es, 0, es, 0, orig_es.length );
        Set<Vertex> vs = new HashSet<>();
        Map<Vertex, Set<Edge>> adjacencyList = new HashMap<>();

        for ( Edge e : es )
        {
            if ( !vs.contains( e.source ) )
            {
                vs.add( e.source );
            }

            if ( !vs.contains( e.dest ) )
            {
                vs.add ( e.dest );
            }

            if ( !adjacencyList.containsKey( e.source ) )
            {
                adjacencyList.put( e.source, new HashSet<Edge>() );
            }

            if ( !adjacencyList.containsKey( e.dest ) )
            {
                adjacencyList.put( e.dest, new HashSet<Edge>() );
            }

            Set<Edge> sourceEdges = adjacencyList.get( e.source );
            Set<Edge> destEdges = adjacencyList.get( e.dest );

            sourceEdges.add( new Edge( e.source, e.dest, e.weight ) );
            destEdges.add( new Edge( e.dest, e.source, e.weight ) );

        }

        vertices = vs.toArray(new Vertex[0]);

        for( Vertex v : vertices )
        {
            Set<Edge> edgesOfV = adjacencyList.get( v );
            v.adjacent = edgesOfV.toArray( new Edge[0] );
        }
    }

}
