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

        g = new Graph(vs, es);
    }

    // BreadthFirstSearch tests
    @Test
    public void BreadthFirstSearchTests()
    {
        resetGraph();
        BreadthFirstSearch.perform(g, g.vertices[0]);
        assertEquals("Failure - path calculated wrong", g.vertices[7].previous, g.vertices[3]);
        assertEquals(2.0, g.vertices[7].minDistance, 0.0);

        assertEquals("Failure - path calculated wrong", g.vertices[4].previous, g.vertices[1]);
        assertEquals(2.0, g.vertices[4].minDistance, 0.0);

        assertEquals("Failure - path calculated wrong", g.vertices[8].previous, g.vertices[5]);
        assertEquals(3.0, g.vertices[8].minDistance, 0.0);

        printRoute( g.vertices[8] );
    }
    
    // Dijskstra's Algorithm tests
    @Test
    public void DijkstraTests()
    {
        resetGraph();
        Dijkstra.perform(g, g.vertices[0]);
        
        assertEquals("Failure - path calculated wrong", g.vertices[8].previous, g.vertices[5]);
        assertEquals(23.0, g.vertices[8].minDistance,0.0);

        assertEquals("Failure - path calculated wrong", g.vertices[5].previous, g.vertices[4]);
        assertEquals(13.0, g.vertices[5].minDistance,0.0);
        
        assertEquals("Failure - path calculated wrong", g.vertices[4].previous, g.vertices[2]);
        assertEquals(9.0, g.vertices[4].minDistance,0.0);

        printRoute( g.vertices[8] );
    }
   
   // Depth First Search tests
    @Test
    public void depthFirstSearchTests()
    {
        resetGraph();
        DepthFirstSearch.perform(g, g.vertices[0]);
        
        printRoute( g.vertices[8] );

        // how to blackbox test depth first search??

    }

    // Kruskals Tests
    @Test
    public void kruskalTests()
    {
        resetGraph();
        Graph mst = Kruskal.perform( g );
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
        List<Vertex> route = routeFrom(g.vertices[8]);
        Iterator<Vertex> iter = route.iterator();
       
        System.out.println("Path:");
        while (iter.hasNext())
        {
                System.out.print(iter.next().name + ", ");      
        }
       
       System.out.println();
    }
}
