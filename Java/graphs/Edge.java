package graphs;

import java.lang.*;
import java.util.*;

public class Edge
{
    Vertex source;
    Vertex dest;
    double weight;
    
    public Edge(Vertex s, Vertex d)
    {
        source = s;
        dest = d;
        weight = 1.0;
    }

    public Edge(Vertex s, Vertex d, double w)
    {
        source = s;
        dest = d;
        weight = w;
    }

    // used to make all graph edges undirected when using Maps
    @Override
    public int hashCode()
    {
        return source.hashCode() + dest.hashCode();
    }
}
