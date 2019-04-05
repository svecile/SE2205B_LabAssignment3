public class Vertex <V>{
    private Vertex<V> next;
    private V element;
    private Vertex<V> previous;

    //-----------------------------------------------------------------
    //  Creates an empty node.
    //-----------------------------------------------------------------
    public Vertex()
    {
        next = null;
        element = null;
        previous = null;
    }

    //-----------------------------------------------------------------
    //  Creates a node storing the specified element.
    //-----------------------------------------------------------------
    public Vertex(V elem)
    {
        next = null;
        element = elem;
        previous = null;
    }

    //-----------------------------------------------------------------
    //  Returns the node that follows this one.
    //-----------------------------------------------------------------
    public Vertex<V> getNext()
    {
        return next;
    }

    //-----------------------------------------------------------------
    //  Returns the node that precedes this one.
    //-----------------------------------------------------------------
    public Vertex<V> getPrevious()
    {
        return previous;
    }

    //-----------------------------------------------------------------
    //  Sets the node that follows this one.
    //-----------------------------------------------------------------
    public void setNext (Vertex<V> dnode)
    {
        next = dnode;
    }

    //-----------------------------------------------------------------
    //  Sets the node that follows this one.
    //-----------------------------------------------------------------
    public void setPrevious (Vertex<V> dnode)
    {
        previous = dnode;
    }


    //-----------------------------------------------------------------
    //  Returns the element stored in this node.
    //-----------------------------------------------------------------
    public int getLabel()
    {
        return 1;
    }

    //-----------------------------------------------------------------
    //  Sets the element stored in this node.
    //-----------------------------------------------------------------
    public void setElement (V elem)
    {
        element = elem;
    }


}
