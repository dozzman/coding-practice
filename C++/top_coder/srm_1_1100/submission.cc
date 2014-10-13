#include <functional>
#include <vector>
#include <map>
#include <string>
#include <cstdio>
#include <cmath>
#include <unordered_set>
#include <iostream>
#include <fstream>
#include <cassert>

class Point
{
    public:
    double x, y;

    Point( int _x, int _y ) : x(_x), y(_y) {}
    Point() : x( 0 ), y( 0 ) {}
    Point( const Point& p ) : x( p.x ), y( p.y ) {}
    Point& operator=( const Point& p )
    {
        x = p.x;
        y = p.y;
        return *this;
    }

    bool operator==( const Point& p ) const
    {
        if ( x == p.x && y == p.y )
        {
            return true;
        }

        return false;
    }

    double dist( const Point& p ) const
    {
        double dx = p.x - x;
        double dy = p.y - y;
        dx *= dx;
        dy *= dy;

        return sqrt( dx + dy );
    }
};

class Segment
{
    public:
    Point p0, p1;
    
    Segment() : p0( Point() ), p1( Point() ) {}
    Segment( int x0, int y0, int x1, int y1 ) : p0( x0, y0 ), p1( x1, y1 ) {}
    Segment( Point _p0, Point _p1 ) : p0( _p0 ), p1( _p1 ) {}
    Segment( const Segment& seg ) : p0 ( seg.p0 ), p1( seg.p1 ) {}
    Segment& operator=( const Segment& seg )
    {
        p0 = seg.p0;
        p1 = seg.p1;
        return *this;
    }

    bool operator==( const Segment& s ) const
    {
        if ( p0 == s.p0 && p1 == s.p1 )
        {
            return true;
        }

        return false;
    }

    bool operator!=( const Segment& s ) const
    {
        return !(*this == s);
    }

    Segment reverse() const
    {
        return Segment(p1, p0);
    }

    double length() const
    {
        return p0.dist( p1 );
    }

    double dot( const Segment& s ) const
    {
        double self_dx = p1.x - p0.x;
        double self_dy = p1.y - p0.y;
        double s_dx = s.p1.x - s.p0.x;
        double s_dy = s.p1.y - s.p0.y;
        
        // dot as if the segments were vectors with origin p0 and s.p0
        return (self_dx*s_dx) + (self_dy*s_dy);
    }
    
    bool overlaps( const Segment& seg ) const
    {
        if ( *this == seg )
        {
            return false;
        }

        Segment normal( Point(0,0), Point( p1.y - p0.y, p0.x - p1.x ) );
        double d = dot( normal );
        Segment segP0( Point(0,0), seg.p0 );
        Segment segP1( Point(0,0), seg.p1 );
        if ( normal.dot( segP0 ) != d )
        {
            return false;
        }
        if ( normal.dot( segP1 ) != d )
        {
            return false;
        }

        // segments lie within same plane
        // now test if a point lies between the other line segment
        Segment seg1( seg.p0, p0 );
        Segment seg2( seg.p0, p1 );
        Segment seg3( seg.p1, p0 );
        Segment seg4( seg.p1, p1 );

        if ( seg1.dot( seg2 ) < 0 )
        {
            return true;
        }
        if ( seg3.dot( seg4 ) < 0 )
        {
            return true;
        }
        if ( seg1.dot( seg3 ) < 0 )
        {
            return true;
        }
        if (seg2.dot( seg4 ) < 0 )
        {
            return true;
        }

        return false;

        Segment s1( p0, seg.p0 );
        Segment s2( p0, seg.p1 );
    }

    bool intersects( const Segment& seg ) const
    {
        Segment normal( Point(0,0), Point( p1.y - p0.y, p0.x - p1.x ) );
        Segment seg_normal( Point(0,0), Point( seg.p1.y - seg.p0.y, seg.p0.x - seg.p1.x ) );
        Segment s1( p0, seg.p0 );
        Segment s2( p0, seg.p1 );
        Segment s3( seg.p0, p0 );
        Segment s4( seg.p0, p1 );
        
        std::cout << "this.s1 * this.s2 = " << normal.dot( s1 ) * normal.dot( s2 ) << std::endl;
        if ( normal.dot( s1 ) * normal.dot( s2 ) < 0 )
        {
            std::cout << "this.s3 * this.s4 = " << seg_normal.dot( s3 ) * seg_normal.dot( s4 ) << std::endl;
            if ( seg_normal.dot( s3 ) * seg_normal.dot( s4 ) < 0 )
            {
                return true;
            }
        }
        return false;
    }
};

template <typename T>
class Graph
{
    protected:
        class Vertex;
        class Edge;
        typedef std::vector<Vertex*> VertexVector;
        typedef std::vector<Edge*> EdgeVector;

        // vector of active graph vertices
        VertexVector vertices;

        //vertex type
        class Vertex
        {
            private:
                T data;

            public:
                T getData() const { return data; }

                void setData( const T& data )
                {
                    this->data = data;   
                }

                Edge* getEdgeWithDest( int dest ) const
                {
                    for ( auto e : edges )
                    {
                        if ( e->getDest() == dest )
                        {
                            return e;
                        }
                    }

                   return nullptr;
                }

                std::vector<Edge*> edges;
                Vertex( const T &data ) : data( data ) {}
        };

        //edge type
        class Edge
        {
            private:
                int src, dest;
                double weight;
            
            public:
                int getSrc() const { return src; }
                int getDest() const { return dest; }
                int getWeight() const { return weight; }
                void setSrc( int new_src ) { src = new_src; }
                void setDest( int new_dest ) { dest = new_dest; }

                Edge( int _src, int _dest, double _weight ) : src( _src ), dest( _dest ), weight( _weight ) {}
        };

        // insert a new vertex
        int addVertex( const T &data )
        {
            vertices.push_back( new Vertex( data ) );
            return (vertices.size() - 1);
        }

        // returns a vector of unique edges in the graph (edges going only one way)
        std::vector<Edge*> getEdges()
        {
            // predicate returns true iff two edges have the same endpoints
            auto predFunction = [] ( Edge const * const e1, Edge const * const e2 )
            {
                if ( e1->getSrc() == e2->getSrc() )
                {
                    if ( e1->getDest() == e2->getDest() )
                    {
                        return true;
                    }
                }
                else if ( e1->getSrc() == e2->getDest() )
                {
                    if ( e1->getDest() == e2->getSrc() )
                    {
                        return true;
                    }
                }
                return false;
            };
            
            // hash function ensures edges going in different directions but at the same nodes
            // hash to the same hash value
            auto hashFunction = [] ( Edge const * const e )
            {
                return (size_t)(e->getSrc() + e->getDest());
            };

            std::unordered_set<Edge*, decltype(hashFunction), decltype( predFunction )> result_set(0, hashFunction, predFunction);
            std::vector<Edge*> result_vector;

            for ( auto& v : vertices )
            {
                for ( auto e : v->edges )
                {
                    result_set.insert( e );
                }
            }

            // return edges as a vector
            for( auto e : result_set )
            { 
                result_vector.push_back( e );
            }
            return result_vector;
        }

    public:
        Graph() {}

        // add a new directed edge to the graph
        virtual void addEdge( const T& src, const T& dest, double weight = 1.0 )
        {
            int srcIndex = -1, destIndex = -1;

            // determine if nodes already exist
            for ( int i = 0; i < vertices.size(); i++ )
            {
                if ( vertices[i]->getData() == src )
                {
                    srcIndex = i;
                }
                else if ( vertices[i]->getData() == dest )
                {
                    destIndex = i;
                }
            }
            
            // check if edge already exists
            for ( auto e : vertices[srcIndex]->edges )
            {
                if ( e->getDest() == destIndex )
                {
                    return;
                }
            }
                
            // add them if they do not exist
            if ( srcIndex == -1 )
            {
                srcIndex = addVertex( src );
            }

            if ( destIndex == -1 )
            {
                destIndex = addVertex( dest );
            }

            Edge *e = new Edge( srcIndex, destIndex, weight );
            vertices[srcIndex]->edges.push_back( e );
        }

        void removeEdge( const T& src, const T& dest )
        {
            for ( int i = 0; i < vertices.size(); i++ )
            {
                if ( vertices[i]->getData() == src )
                {
                    for( typename EdgeVector::iterator iter = vertices[i]->edges.begin(); iter != vertices[i]->edges.end(); iter++ )
                    {
                        if ( vertices[(*iter)->getDest()]->getData() == dest )
                        {
                            vertices[i]->edges.erase( iter );
                            return;
                        }
                    }
                }
            }
        }


        // add undirected edge (two directed edges) to graph
        virtual void addEdges( const T &src, const T& dest, double weight = 1.0 )
        {
            addEdge( src, dest, weight );
            addEdge( dest, src, weight );
        }

};

class SegmentGraph : protected Graph<Point>
{
    public:
    void printGraph()
    {
        for ( auto v : vertices )
        {
            std::cout << "( " << v->getData().x << ", " <<  v->getData().y << " ) ---> ";
            for ( auto e : v->edges )
            {
                std::cout << "( " << vertices[e->getDest()]->getData().x << ", " << vertices[e->getDest()]->getData().y << " ), ";
            }
            std::cout << std::endl;
        }
    }
    
    void addSegment( Segment &seg )
    {
        Graph<Point>::addEdges( seg.p0, seg.p1 );
    }

    virtual void addEdge( const Point &p0, const Point &p1, double weight ) override
    {
        int srcIndex = -1, destIndex = -1;
        Segment seg( p0, p1 );
        // check for overlaps
        std::vector<Edge*> edges( Graph<Point>::getEdges() );
        printf("Adding edge %.0f %.0f %.0f %.0f\n", p0.x, p0.y, p1.x, p1.y );
        std::cout << "Edges before... ";
        for ( auto e : edges )
        {
            printf(" (%.0f %.0f %.0f %.0f) ", vertices[e->getSrc()]->getData().x, vertices[e->getSrc()]->getData().y, vertices[e->getDest()]->getData().x, vertices[e->getDest()]->getData().y );
        }

        std::cout << std::endl;
        std::cout << std::endl;


        for ( auto e : edges )
        {
            Segment segOfE( vertices[e->getSrc()]->getData(), vertices[e->getDest()]->getData() );
            if ( seg.overlaps ( segOfE ) && seg != segOfE && seg != segOfE.reverse() )
            {
                std::cout << "we have an overlap!\n";
                double length = seg.length();
                double nx = ( p1.x - p0.x ) / length;

                double t = ( segOfE.p0.x - p0.x ) / nx;

                if ( t > 0 && t < 1 )
                {
                    // segOfE.p0 is the point which lies within the current edge
                    // so vertices[e->getSrc()] needs to be adjusted
                    t = ( segOfE.p1.x - p0.x ) / nx;
                    
                    if ( t > 1 )
                    {
                        // src comes down to p0
                        vertices[e->getSrc()]->setData( p0 );
                    }
                    else if ( t < 0 )
                    {
                        // src goes up to p1
                        vertices[e->getSrc()]->setData( p1 );
                    }
                    
                    // if none of the above, the entire new edge lies within the current edge
                    // therefore don't add it
                }
                else
                {
                    // segOfE.p0 is the point that lies outside of the current edge
                    // so vertices[e->getDest()] needs to be adjusted
                    if ( t > 1 )
                    {
                        // dest needs to come down to p0
                        vertices[e->getDest()]->setData( p0 );
                    }
                    else if ( t < 0 )
                    {
                        vertices[e->getDest()]->setData( p1 );
                    }
                    // none of the above => edge lies within current edge
                }
                return;
            }
        }

        // determine if nodes already exist
        for ( int i = 0; i < vertices.size(); i++ )
        {
            if ( vertices[i]->getData() == p0 )
            {
                srcIndex = i;
            }
            else if ( vertices[i]->getData() == p1 )
            {
                destIndex = i;
            }
        }

        if ( srcIndex != -1 )
        {
            // check if edge already exists
            for ( auto e : vertices[srcIndex]->edges )
            {
                if ( e->getDest() == destIndex )
                {
                    return;
                }
            }
        }
        
        // add them if they do not exist
        if ( srcIndex == -1 )
        {
            srcIndex = addVertex( p0 );
        }

        if ( destIndex == -1 )
        {
            destIndex = addVertex( p1 );
        }


        Edge *e = new Edge( srcIndex, destIndex, weight );
        vertices[srcIndex]->edges.push_back( e );
   }

    // reconstruction adds any absent intersection nodes and 
    // joins up any overlapping segments
    void reconstruct()
    {
        std::cout << "in reconstruct...\n\n";
        auto edges( Graph<Point>::getEdges() );
        bool restart = true;

        while ( restart )
        {
            std::cout << "loop de loop\n";
            restart = false;
            for ( auto e1 : edges )
            {
                for ( auto e2 : edges )
                {
                    if ( e1 == e2 )
                    {
                        continue;
                    }

                    Segment s1( vertices[e1->getSrc()]->getData(), vertices[e1->getDest()]->getData() );
                    Segment s2( vertices[e2->getSrc()]->getData(), vertices[e2->getDest()]->getData() );

                    if ( s1.intersects( s2 ) )
                    {
                        std::cout << "we have an intersection!\n";
                        std::cout << "between ";
                        printf(" (%.0f %.0f %.0f %.0f) ", s1.p0.x, s1.p0.y, s1.p1.x, s1.p1.y );
                        printf(" (%.0f %.0f %.0f %.0f) ", s2.p0.x, s2.p0.y, s2.p1.x, s2.p1.y );
                        std::cout << std::endl;
                        restart = true;

                        double nx = ( s1.p1.x - s1.p0.x );
                        double ny = ( s1.p1.y - s1.p0.y );
                        double mx = ( s2.p1.x - s2.p0.x );
                        double my = ( s2.p1.y - s2.p0.y );
                        double y_diff = s1.p0.y - s2.p0.y;
                        double x_diff = s2.p0.x - s1.p0.x;
                        double denom = nx*my - mx*ny;

                        std::cout << "s1 length = " << s1.length() << "\n";
                        std::cout << "s2 length = " << s2.length() << "\n";
                        std::cout << "x_diff = " << x_diff << ", y_diff = " << y_diff << std::endl;
                        std::cout << "nx = " << nx << ", ny = " << ny << ", mx = " << mx << ", my = " << my << std::endl; 
                        double t = ( mx*(y_diff) + my*(x_diff) ) / (denom);
                        double s = ( nx*(-y_diff) + ny*(-x_diff) ) / (denom);
                        // calculate point of intersection
                        Point intersect(s1.p0.x + nx*t, s1.p0.y + ny*t);
                        std::cout << "intersection point = ( " << intersect.x << ", " << intersect.y << " )\n";
                        std::cout << "s = " << s << ", t = " << t << "\n";
                        int intIndex;
                        int s1_ID = 1;
                        int s2_ID = 2;
                        int intersectExists = -1;

                        // add the new intersection point to the graph
                        // if statements determine whether intersection occurs between the two
                        // lines, in which case a new point must be added, or if it occurs at an
                        // endpoint, in which case no new nodes must be added
                        if ( s == 0 )
                        {
                            intIndex = e1->getSrc();
                            intersectExists = s2_ID;
                        }
                        else if ( s == 1 )
                        {
                            intIndex = e1->getDest();
                            intersectExists = s2_ID;
                        }
                        else if ( t == 0 )
                        {
                            intIndex = e2->getSrc();
                            intersectExists = s1_ID;
                        }
                        else if ( t == 1 )
                        {
                            intIndex = e2->getDest();
                            intersectExists = s1_ID;
                        }
                        else
                        {
                            intIndex = addVertex( intersect );
                        }

                        if (intersectExists == s1_ID )
                        {
                            // intersection occurs at intIndex, which exists already
                            // move the current edge to between src and intIndex, and create a new
                            // edge between intIndex an dest
                            int old_e1Dest = e1->getDest();
                            e1->setDest( intIndex );
                            vertices[old_e1Dest]->getEdgeWithDest( e1->getSrc() )->setSrc( intIndex );
                            Graph<Point>::addEdges( vertices[intIndex]->getData(), vertices[old_e1Dest]->getData() );
                        }
                        else if ( intersectExists == s2_ID )
                        {
                            // same as above, except for the other line
                            int old_e2Dest = e2->getDest();
                            e2->setDest( intIndex );
                            vertices[old_e2Dest]->getEdgeWithDest( e2->getSrc() )->setSrc( intIndex );
                            Graph<Point>::addEdges( vertices[intIndex]->getData(), vertices[old_e2Dest]->getData() );
                        }
                        else
                        {
                            std::cout << "cut both lines in half\n";
                            // intersection occurs somewhere between the two lines, therefore do both of the above
                            // cases, except add a new intersection vertex
                            int old_e1Dest = e1->getDest();

                            // begin connecting up everything
                            e1->setDest( intIndex );
                            vertices[old_e1Dest]->getEdgeWithDest( e1->getSrc() )->setSrc( intIndex );
                            Graph<Point>::addEdge( vertices[intIndex]->getData(), vertices[e1->getSrc()]->getData() );
                            Graph<Point>::removeEdge( vertices[old_e1Dest]->getData(), vertices[e1->getSrc()]->getData() );
                            Graph<Point>::addEdges( vertices[intIndex]->getData(), vertices[old_e1Dest]->getData() );

                            //TODO: need to make a new edge to connect intersection to e1.dest
                            //also need to connect intersection to both sides of e2,
                            //finally need to duplicate all of above for edges in other direction


                            int old_e2Dest = e2->getDest();
                            e2->setDest( intIndex );
                            vertices[old_e2Dest]->getEdgeWithDest( e2->getSrc() )->setSrc( intIndex );
                            Graph<Point>::addEdge( vertices[intIndex]->getData(), vertices[e2->getSrc()]->getData() );
                            Graph<Point>::removeEdge( vertices[old_e2Dest]->getData(), vertices[e2->getSrc()]->getData() );
                            Graph<Point>::addEdges( vertices[intIndex]->getData(), vertices[old_e2Dest]->getData() );
                        }
                    }
                    if ( restart )
                    {
                        break;
                    }
                }
                if ( restart )
                {
                    break;
                }
            }
        }
    }

};

class PenLift
{
    private:
        Segment parseSegment( std::string &data )
        {
            Segment result;
            sscanf( data.c_str(), "%lf %lf %lf %lf", &(result.p0.x), &(result.p0.y), &(result.p1.x), &(result.p1.y) );
            std::cout << "Parsed: " << std::endl;
            std::cout << result.p0.x << " " << result.p0.y << " " << result.p1.x << " " << result.p1.y << std::endl << std::endl;
            return result;
        }

    public: //TODO: make this private before submission
        void formatData( std::vector<std::string> &data, int n, SegmentGraph &g )
        {
            // turn data into a graph
            for( auto& segment : data )
            {
                Segment seg( parseSegment( segment ) );
                g.addSegment( seg );
            }

            g.reconstruct();
        }

    public:
        int numTimes( std::vector<std::string> &data, int n )
        {
            SegmentGraph g;
            formatData( data, n, g );
            return 0;
        }
};

int main()
{
    PenLift test;

    std::ifstream file("data.txt");
    std::vector<std::string> data;
    std::string line;

    while ( std::getline( file, line ) )
    {
        data.push_back( line );
    }

    SegmentGraph g;
    test.formatData( data, 0, g );
    g.printGraph();

    return 0;
}
