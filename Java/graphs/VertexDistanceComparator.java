package graphs;

import java.util.*;
import java.lang.*;

public class VertexDistanceComparator implements Comparator<Vertex>
{
    public int compare(Vertex v1, Vertex v2)
    {
        return Double.compare(v1.minDistance, v2.minDistance);
    }
}
