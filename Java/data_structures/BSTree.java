package data_structures;

import java.lang.*;
import java.util.*;

public class BSTree<T extends Comparable>
{
    private class Node<T>
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

    private Node<T> root;
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
    
    private boolean searchAt(T data, Node node)
    {
        if ( node == null )
        {
            return false;
        }
        
        if ( data.compareTo( node.data ) == 0)
        {
            return true;
        }

        if ( data.compareTo( node.data ) < 0 )
        {
            return searchAt( data, node.left );
        }

        return searchAt( data, node.right );
    }

    public boolean search(T data)
    {
       return searchAt( data, root );
    }
}
