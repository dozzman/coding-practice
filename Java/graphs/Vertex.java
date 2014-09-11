package graphs;

import java.util.*;
import java.lang.*;

public class Vertex
{
    Edge [] adjacent;
    Vertex previous;
    int colour;
    double minDistance;
    String name;

    public Vertex( Edge [] es )
    {
        adjacent = es;
        
        for (Edge e : adjacent)
        {
            e.source = this;
        }
    }

    public Vertex(String string)
    {
        name = string;
    }
}

