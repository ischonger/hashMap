package hashMap;

public class Node<Key, V>
{
    private int hash;

    private Key key;

    private V value;

    private Node next = null;

    public Node(Key key, V value)
    {
        this.hash = key.hashCode();
        this.key = key;
        this.value = value;
    }

    public V getValue()
    {
        return value;
    }

    public Key getKey()
    {
        return key;
    }

    public void setNext(Node node)
    {
        this.next = node;
    }

    public Node getNext()
    {
        return next;
    }

    public V checkNext(Key key2)
    {
        Node node = this.getNext();
        V toBeReturned = null;
        while (node != null)
        {
            if (node.getKey() == key2)
            {
                toBeReturned = (V) node.getValue();
            }
            node = node.getNext();
        }
        return toBeReturned;
    }

    public Node getNext(Key key2)
    {
        Node node = this.getNext();
        Node toBeReturned = null;
        while (node != null)
        {
            if (node.getKey() == key2)
            {
                toBeReturned = node;
            }
            node = node.getNext();
        }
        return node;
    }

    public void clear()
    {
        this.hash = 0;
        this.key = null;
        this.value = null;
    }
}
