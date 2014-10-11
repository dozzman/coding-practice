#include <list>
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

    void sort()
    {
        if ( p0.y == p1.y )
        {
            if ( p1.x < p0.x )
            {
                Point temp = p0;
                p0 = p1;
                p1 = temp;
            }
        }
        else
        {
            if ( p1.y < p0.y )
            {
                Point temp = p0;
                p0 = p1;
                p1 = temp;
            }
        }
    }

    bool operator<( const Segment& seg ) const
    {
        assert( "Comparing Segments!" );
        return false;
    }

    double dot( const Segment& s ) const
    {
        double self_dx = p1.x - p0.x;
        double self_dy = p1.y - p0.y;
        double s_dx = s.p1.x - s.p0.x;
        double s_dy = s.p1.y - s.p0.y;

        return (self_dx*s_dx) + (self_dy*s_dy);
    }
    
    bool overlaps( const Segment& seg ) const
    {
        Segment s1( p0, seg.p0 );
        Segment s2( p0, seg.p1 );

        double s1_length = s1.length();
        double s2_length = s2.length();
        if ( abs( this->dot ( s1 ) ) == length()*s1_length )
        {
            if ( abs( this->dot ( s2 ) ) == length()*s2_length )
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

        // list of active graph vertices
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

                std::list<Edge*> edges;
                Vertex( T &data ) : data( data ) {}
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
                Edge( int _src, int _dest, double _weight ) : src( _src ), dest( _dest ), weight( _weight ) {}
        };

        // insert a new vertex
        int addVertex( T &data )
        {
            vertices.push_back( new Vertex( data ) );
            return (vertices.size() - 1);
        }

        // returns a vector of unique edges in the graph (edges going only one way)
        std::vector<Edge*> getEdges()
        {
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
                std::cout << "different" << std::endl;
                return false;
            };

            auto hashFunction = [] ( Edge const * const e )
            {
                return (size_t)0;
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

            for( auto e : result_set )
            { 
                result_vector.push_back( e );
            }
            return result_vector;
        }

    public:
        Graph() {}

        // add a new directed edge to the graph
        virtual void addEdge( T& src, T& dest, double weight = 1.0 )
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
            
            // add them if they do not exist
            if ( srcIndex == -1 )
            {
                srcIndex = addVertex( src );
            }

            if ( destIndex == -1 )
            {
                destIndex = addVertex( dest );
            }

            // check if edge already exists
            for ( auto e : vertices[srcIndex]->edges )
            {
                if ( e->getDest() == destIndex )
                {
                    return;
                }
            }
                
            Edge *e = new Edge( srcIndex, destIndex, weight );
            vertices[srcIndex]->edges.push_back( e );
        }

        // add undirected edge (two directed edges) to graph
        virtual void addEdges( T &src, T& dest, double weight = 1.0 )
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

    virtual void addEdge( Point &p0, Point &p1, double weight ) override
    {
        int srcIndex = -1, destIndex = -1;
        Segment seg( p0, p1 );
        // check for overlaps
        std::vector<Edge*> edges( Graph<Point>::getEdges() );
        
        std::cout << "Edges returned... ";
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
/*        auto edges( Graph<Point>::getEdges() );

        for ( auto e1 : edges )
        {
            for ( auto e2 : edges )
            {
                
            }
        }*/
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

    public:
        void formatData( std::vector<std::string> &data, int n, SegmentGraph &g )
        {
            // turn data into a graph
            for( auto& segment : data )
            {
                Segment seg( parseSegment( segment ) );
                g.addSegment( seg );
            }
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
