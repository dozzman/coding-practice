package data_structures;

import java.lang.*;
import java.util.*;

public class BSTree<T extends Comparable<? super T>>
{
    private class Node
    {
        public Node parent;
        public Node left;
        public Node right;
        public T data;

        public Node( T newData )
        {
            data = newData;
        }

        public Node( T newData, Node newParent )
        {
            data = newData;
            parent = newParent;
        }
    };

    private Node root;
    private int count;

    public int count() { return count; };

    private void insertAt(T data, Node node)
    {
        if ( data.compareTo( node.data ) < 0 )
        {
            if ( node.left == null )
            {
                node.left = new Node( data, node );
                return;
            }
            else
            {
                insertAt( data, node.left );
            }
        }
        else
        {
            if ( node.right == null )
            {
                node.right = new Node( data, node );
                return;
            }
            else
            {
                insertAt( data, node.right );
            }
        }
    }

    public void insert(T data)
    {
        if ( root == null )
        {
            root = new Node( data, null );
            count++;
            return;
        }

        insertAt( data, root );
        count++;
    }
    
    public boolean search(T data)
    {
       return searchNodeAt( data, root ) == null ? false : true ;
    }
    
    private Node searchNodeAt(T data, Node node)
    {
        if( node == null )
        {
            return null;
        }
        
        if( data.compareTo( node.data ) == 0 )
        {
            return node;
        }

        if ( data.compareTo( node.data ) < 0 )
        {
            return searchNodeAt( data, node.left );
        }
        else
        {
            return searchNodeAt( data, node.right );
        }
    }
    
    private Node minimumNodeAt( Node node )
    {
        if ( node.left == null )
        {
            return node;
        }
        else
        {
            return minimumNodeAt( node.left );
        }
    }

    private Node successorNode( Node node )
    {
        if ( node.right != null )
        {
            return minimumNodeAt( node.right );
        }

        Node nodeParent = node.parent;
        Node temp = node;

        while ( nodeParent != null && node == nodeParent.right )
        {
            node = nodeParent;
            nodeParent = nodeParent.parent;
        }

        if( nodeParent == null || nodeParent.data.compareTo( temp.data ) <= 0 )
        {
            return null;
        }

        return nodeParent;
    }

    public T successor( T data )
    {
        
        Node node = searchNodeAt( data, root );
        Node successorNode = successorNode( node );
        if ( successorNode == null )
            return null;

       return successorNode.data;
    }

    private void transplant( Node n1, Node n2 )
    {
        if ( n1.parent == null )
        {
            root = n2;
        }
        else if ( n1.parent.left == n1 )
        {
            n1.parent.left = n2;
        }
        else
        {
            n1.parent.right = n2;
        }
        if ( n2 != null )
        {
            n2.parent = n1.parent;
        }
    }

    public boolean delete( T data )
    {
        Node node = searchNodeAt( data, root );
        if ( node == null )
        {
            // data not found in tree
            return false;
        }
        else if ( node.left == null )
        {
            transplant( node, node.right );
        }
        else if ( node.right == null )
        {
            transplant( node, node.left );
        }
        else
        {
            Node successorNode = minimumNodeAt( node.right );
            if ( successorNode.parent != node )
            {
                transplant( successorNode, successorNode.right );
                successorNode.right = node.right;
                successorNode.right.parent = successorNode;
            }
            transplant( node, successorNode );
            successorNode.left = node.left;
            successorNode.left.parent = successorNode;
        }

        return true;
    }
}
