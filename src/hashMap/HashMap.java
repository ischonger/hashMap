package hashMap;

import java.io.Serializable;

public class HashMap<Key, V> implements Cloneable, Serializable
{
    private int size;

    private int DEFAULT_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    private Node[] arrayMap = new Node[DEFAULT_CAPACITY];

    public void put(Key key, V value)
    {
        int index = key.hashCode() % arrayMap.length;
        Node node = new Node(key, value);
        if (arrayMap[index] == null)
        {
            arrayMap[index] = node;
        }
        else
        {
            if (arrayMap[index].getNext() == null)
            {
                arrayMap[index].setNext(node);
            }
            else
            {
                Node lastNode = recursiveDescent(arrayMap[index]);
                lastNode.setNext(node);
            }
        }
    }

    private void put(Node node)
    {
        Object key = node.getKey();
        Object value = node.getValue();
        int index = key.hashCode() % arrayMap.length;
        if (arrayMap[index] == null)
        {
            arrayMap[index] = node;
        }
        else
        {
            if (arrayMap[index].getNext() == null)
            {
                arrayMap[index].setNext(node);
            }
            else
            {
                Node lastNode = recursiveDescent(arrayMap[index]);
                lastNode.setNext(node);
            }
        }
    }

    private Node recursiveDescent(Node node)
    {
        while (node.getNext() != null)
        {
            node = node.getNext();
        }
        return node;
    }

    @SuppressWarnings("unchecked")
    public V get(Key key)
    {
        int index = key.hashCode() % arrayMap.length;
        Node expectedNode = arrayMap[index];
        V toBeReturned = null;
        if (expectedNode != null)
        {
            if (expectedNode.getKey() == key)
            {
                toBeReturned = (V) expectedNode.getValue();
            }
            else
            {
                toBeReturned = (V) expectedNode.checkNext(key);
            }

        }
        return toBeReturned;
    }

    public boolean remove(Key key)
    {
        boolean isRemoved = false;
        int removalIndex = key.hashCode() % arrayMap.length;
        Node expectedNode = arrayMap[removalIndex];
        if (expectedNode != null)
        {
            if (expectedNode.getKey() == key)
            {
                arrayMap[removalIndex].clear();
                arrayMap[removalIndex] = expectedNode.getNext();
            }
            else
            {
                Node oldNode = expectedNode;
                while (expectedNode.getKey() != key)
                {
                    oldNode = expectedNode;
                    expectedNode = expectedNode.getNext();
                }
                oldNode.setNext(expectedNode.getNext());
            }
            isRemoved = true;
        }
        return isRemoved;
    }

    public void clear()
    {
        for (int i = 0; i < DEFAULT_CAPACITY; i++)
        {
            arrayMap[i] = null;
        }

    }

    /*
     * returns the amount of used buckets
     */
    public int capacity()
    {
        return arrayMap.length;
    }

    /*
     * returns the amount of nodes saved in the array
     */
    public int size()
    {
        int size = 0;
        for (int i = 0; i < arrayMap.length; i++)
        {
            Node expectedNode = arrayMap[i];
            while (expectedNode != null)
            {
                size += 1;
                expectedNode = expectedNode.getNext();
            }
        }
        return size;
    }

    public void putAll(HashMap<Key, V> map2)
    {
        for (int i = 0; i < map2.capacity(); i++)
        {
            if (map2.arrayMap[i] != null)
            {
                Node node = map2.arrayMap[i];
                this.put(node);
            }
        }
        // TODO check for doubled entries?
    }

    public HashMap<Key, V> clone()
    {
        HashMap<Key, V> map2 = new HashMap<Key, V>();
        for (int i = 0; i < map2.capacity(); i++)
        {
            Node node = arrayMap[i];
            map2.put(node);
        }
        return map2;
    }

    public boolean containsKey(Key key)
    {
        for (int i = 0; i < arrayMap.length; i++)
        {
            Node expectedNode = arrayMap[i];
            while (expectedNode != null)
            {
                if (expectedNode.getKey() == key)
                {
                    return true;
                }
                expectedNode = expectedNode.getNext();
            }
        }
        return false;
    }

    public boolean containsValue(V value)
    {
        for (int i = 0; i < arrayMap.length; i++)
        {
            Node expectedNode = arrayMap[i];
            while (expectedNode != null)
            {
                if (expectedNode.getValue().equals(value))
                {
                    return true;
                }
                expectedNode = expectedNode.getNext();
            }
        }
        return false;
    }

}