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
        resetGraph();
    }

    private void resetGraph()
    {
        Vertex [] vs = new Vertex[9];

        for(int i = 0; i < vs.length; i++ )
        {
            vs[i] = new Vertex(new String(Integer.toString(i)));
        }

        Edge [] es = new Edge[17];

        es[0] = new Edge(vs[0], vs[1], 4.0);
        es[1] = new Edge(vs[0], vs[2], 7.0);
        es[2] = new Edge(vs[0], vs[3], 4.0);
        es[3] = new Edge(vs[1], vs[2], 2.0);
        es[4] = new Edge(vs[2], vs[4], 3.0);
        es[5] = new Edge(vs[2], vs[3], 4.0);
        es[6] = new Edge(vs[1], vs[4], 8.0);
        es[7] = new Edge(vs[3], vs[4], 7.0);
        es[8] = new Edge(vs[1], vs[5], 13.0);
        es[9] = new Edge(vs[3], vs[7], 10.0);
        es[10] = new Edge(vs[4], vs[5], 4.0);
        es[11] = new Edge(vs[4], vs[6], 9.0);
        es[12] = new Edge(vs[4], vs[7], 7.0);
        es[13] = new Edge(vs[5], vs[6], 5.0);
        es[14] = new Edge(vs[6], vs[7], 8.0);
        es[15] = new Edge(vs[5], vs[8], 10.0);
        es[16] = new Edge(vs[7], vs[8], 16.0);

        g = new Graph( vs, es );
        
    }

    public static boolean compareArray( Object [] array1, Object [] array2 )
    {
        for ( Object o1 : array1 )
        {
            boolean match = false;
            for ( Object o2 : array2 )
            {
                if ( o1 == o2 )
                {
                    match = true;
                    break;
                }
            }
            if ( !match )
            {
                return false;
            }
        }

        return true;
    }

    // BreadthFirstSearch tests
    @Test
    public void BreadthFirstSearchTests()
    {
        resetGraph();
        BreadthFirstSearch.perform(g, g.vertex(0));
        assertEquals("Failure - path calculated wrong", g.vertex(7).previous, g.vertex(3));
        assertEquals(2.0, g.vertex(7).minDistance, 0.0);

        assertEquals("Failure - path calculated wrong", g.vertex(4).previous, g.vertex(1));
        assertEquals(2.0, g.vertex(4).minDistance, 0.0);

        assertEquals("Failure - path calculated wrong", g.vertex(8).previous, g.vertex(5));
        assertEquals(3.0, g.vertex(8).minDistance, 0.0);

        printRoute( g.vertex(8) );
    }
    
    // Dijskstra's Algorithm tests
    @Test
    public void DijkstraTests()
    {
        resetGraph();
        Dijkstra.perform(g, g.vertex(0));
        
        assertEquals("Failure - path calculated wrong", g.vertex(8).previous, g.vertex(5));
        assertEquals(23.0, g.vertex(8).minDistance,0.0);

        assertEquals("Failure - path calculated wrong", g.vertex(5).previous, g.vertex(4));
        assertEquals(13.0, g.vertex(5).minDistance,0.0);
        
        assertEquals("Failure - path calculated wrong", g.vertex(4).previous, g.vertex(2));
        assertEquals(9.0, g.vertex(4).minDistance,0.0);

        printRoute( g.vertex(8) );
    }
   
   // Depth First Search tests
    @Test
    public void depthFirstSearchTests()
    {
        resetGraph();
        DepthFirstSearch.perform(g, g.vertex(0));
        
        printRoute( g.vertex(8) );

        // how to blackbox test depth first search

    }

    // Kruskals Tests
    @Test
    public void kruskalTests()
    {
        resetGraph();
        Graph mst = Kruskal.perform( g );
        
        assertTrue("Failure - MST does not contain all vertices", compareArray( g.vertices, mst.vertices ) );

        assertTrue("Failure - MST does not contain neccessary edge", mst.containsEdge( mst.vertex(1), mst.vertex(2) ) );

        assertTrue("Failure - MST does not contain neccessary edge", mst.containsEdge( mst.vertex(4), mst.vertex(2) ) );

        assertTrue("Failure - MST does not contain neccessary edge", mst.containsEdge( mst.vertex(5), mst.vertex(4) ) );
        
    }
    
    // Prims Tests
    @Test
    public void primsTests()
    {
        resetGraph();

        Prims.perform( g, g.vertex(0) );
        assertEquals( 0.0 , g.vertex(0).minDistance, 0 );
        assertEquals("Failure - wrong parent", g.vertex(3).previous, g.vertex(0) );

    }

    private List<Vertex> routeFrom(Vertex v)
    {
        List<Vertex> list = new ArrayList<>();
        
        list.add(v);

        while (v.previous != null)
        {
            list.add(v.previous);
            v = v.previous;
        }
        
        return list;
    }

    private void printRoute(Vertex v)
    {
        List<Vertex> route = routeFrom(g.vertex(8));
        Iterator<Vertex> iter = route.iterator();
       
        System.out.println("Path:");
        while (iter.hasNext())
        {
                System.out.print(iter.next().name + ", ");      
        }
       
       System.out.println();
    }
}
