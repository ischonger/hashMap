package hashMap;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings(
{
  "serial", "hiding"
})
public class HashMap<Key, V> implements Cloneable, Serializable
{
    private int DEFAULT_CAPACITY = 16;

    private double LOAD_FACTOR = 0.75;

    private boolean DO_EXPANSION = true;

    @SuppressWarnings("rawtypes")
    private Node[] arrayMap = new Node[DEFAULT_CAPACITY];

    HashMap()
    {
    }

    /*
     * Constructs an empty HashMap with the specified initial capacity and load factor. With doExpansion = false a
     * hashmap can be generated with a static number of buckets. So there won't be an expansion phase in put().
     * 
     * throws IllegalArgumentException - if the initial capacity is negative or the load factor is not in [0, 1]
     */
    HashMap(int defaultCapacity, double loadFactor, boolean doExpansion)
    {
        if ((loadFactor >= 1.0 && loadFactor <= 0.0) || defaultCapacity <= 0)
        {
            throw new IllegalArgumentException();
        }
        this.DEFAULT_CAPACITY = defaultCapacity;
        this.LOAD_FACTOR = loadFactor;
        this.DO_EXPANSION = doExpansion;
    }

    /*
     * Constructs an empty HashMap with the specified initial capacity and load factor.
     * 
     * throws IllegalArgumentException - if the initial capacity is negative or the load factor is not in [0, 1]
     */
    HashMap(int defaultCapacity, double loadFactor)
    {
        if ((loadFactor >= 1.0 && loadFactor <= 0.0) || defaultCapacity <= 0)
        {
            throw new IllegalArgumentException();
        }
        this.DEFAULT_CAPACITY = defaultCapacity;
        this.LOAD_FACTOR = loadFactor;
    }

    HashMap(boolean doExpansion)
    {
        this.DO_EXPANSION = doExpansion;
    }

    public void put(Key key, V value)
    {
        Node<Key, V> node = new Node<Key, V>(key, value);
        put(node);
    }

    /*
     * Associates the specified value with the specified key in this map.
     * 
     * params Node<Key, V> node
     */
    @SuppressWarnings("unchecked")
    private void put(Node<Key, V> node)
    {
        Object key = node.getKey();
        int index = computeIndex(key);
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
                Node<?, ?> lastNode = getLastAdjascentNode(arrayMap[index]);
                lastNode.setNext(node);
            }
        }
        if (DO_EXPANSION)
        {
            enlargeArrayMap();
        }
    }

    private int computeIndex(Object object)
    {
        return Math.abs(object.hashCode() % arrayMap.length);
    }

    @SuppressWarnings("rawtypes")
    private void enlargeArrayMap()
    {
        int numberOfEmptyBuckets = 0;
        int length = arrayMap.length;
        for (int i = 0; i < length; i++)
        {
            if (arrayMap[i] == null)
            {
                numberOfEmptyBuckets++;
            }
        }

        if (numberOfEmptyBuckets <= (int) length * (1 - LOAD_FACTOR))
        {
            Node[] newArrayMap = new Node[length * 2];
            for (int i = 0; i < length; i++)
            {
                newArrayMap[i] = arrayMap[i];
            }
            this.arrayMap = newArrayMap;
        }
    }

    @SuppressWarnings("unchecked")
    private Node<Key, V> getLastAdjascentNode(Node<Key, V> node)
    {
        while (true)
        {
            if (node.getNext() == null)
            {
                return node;
            }
            node = (Node<Key, V>) node.getNext();
        }
    }

    /*
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * 
     * params Key key returns V
     */
    @SuppressWarnings("unchecked")
    public V get(Key key)
    {
        int index = computeIndex(key);
        Node<Key, ?> expectedNode = arrayMap[index];
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

    /*
     * Removes the mapping for the specified key from this map if present.
     * 
     * params Key key
     * 
     * returns V
     */
    @SuppressWarnings("unchecked")
    public boolean remove(Key key)
    {
        boolean isRemoved = false;
        int removalIndex = computeIndex(key);
        Node<Key, V> expectedNode = arrayMap[removalIndex];
        if (expectedNode != null)
        {
            if (expectedNode.getKey() == key)
            {
                arrayMap[removalIndex].clear();
                arrayMap[removalIndex] = expectedNode.getNext();
            }
            else
            {
                Node<Key, V> oldNode = expectedNode;
                while (expectedNode.getKey() != key)
                {
                    oldNode = expectedNode;
                    expectedNode = (Node<Key, V>) expectedNode.getNext();
                }
                oldNode.setNext(expectedNode.getNext());
            }
            isRemoved = true;
        }
        return isRemoved;
    }

    /*
     * Removes all of the mappings from this map.
     */
    public void clear()
    {
        for (int i = 0; i < DEFAULT_CAPACITY; i++)
        {
            arrayMap[i] = null;
        }

    }

    /*
     * Returns the amount of used buckets.
     */
    public int capacity()
    {
        return arrayMap.length;
    }

    /*
     * Returns the number of key-value mappings in this map.
     * 
     * returns int
     */
    @SuppressWarnings("unchecked")
    public int size()
    {
        int size = 0;
        for (int i = 0; i < arrayMap.length; i++)
        {
            Node<Key, V> expectedNode = arrayMap[i];
            while (expectedNode != null)
            {
                size += 1;
                expectedNode = (Node<Key, V>) expectedNode.getNext();
            }
        }
        return size;
    }

    /*
     * Copies all of the mappings from the specified map to this map.
     * 
     * params HashMap<Key, V> map2
     * 
     * throws NullPointerException - if the specified map is null
     */
    @SuppressWarnings("unchecked")
    public void putAll(HashMap<Key, V> map2)
    {
        if (map2.isEmpty())
        {
            throw new NullPointerException();
        }

        for (int i = 0; i < map2.capacity(); i++)
        {
            Node<Key, V> node = map2.arrayMap[i];
            if (map2.arrayMap[i] != null)
            {
                this.put(node);
            }
        }
    }

    /*
     * Returns a shallow copy of this HashMap instance: the keys and values themselves are not cloned.
     * 
     * @see java.lang.Object#clone()
     */
    public HashMap<Key, V> clone()
    {
        return this;
    }

    /*
     * Returns true if this map contains a mapping for the specified key.
     * 
     * param Key key returns boolean
     */
    @SuppressWarnings("unchecked")
    public boolean containsKey(Key key)
    {
        for (int i = 0; i < arrayMap.length; i++)
        {
            Node<Key, V> expectedNode = arrayMap[i];
            while (expectedNode != null)
            {
                if (expectedNode.getKey() == key)
                {
                    return true;
                }
                expectedNode = (Node<Key, V>) expectedNode.getNext();
            }
        }
        return false;
    }

    /*
     * Returns true if this map maps one or more keys to the specified value.
     * 
     * param V value returns boolean
     */
    @SuppressWarnings("unchecked")
    public boolean containsValue(V value)
    {
        for (int i = 0; i < arrayMap.length; i++)
        {
            Node<Key, V> expectedNode = arrayMap[i];
            while (expectedNode != null)
            {
                if (expectedNode.getValue().equals(value))
                {
                    return true;
                }
                expectedNode = (Node<Key, V>) expectedNode.getNext();
            }
        }
        return false;
    }

    /*
     * Returns a Set view of the mappings contained in this map.
     * 
     * returns HashSet<Node>
     */
    @SuppressWarnings("rawtypes")
    public HashSet<Node> entrySet()
    {
        HashSet<Node> entrySet = new HashSet<Node>();
        Node<?, ?> traverseNode = null;
        for (int i = 0; i < arrayMap.length; i++)
        {
            traverseNode = arrayMap[i];
            while (traverseNode != null)
            {
                entrySet.add(traverseNode);
                traverseNode = traverseNode.getNext();
            }
        }
        return entrySet;
    }

    /*
     * Returns true if this map contains no key-value mappings.
     * 
     * returns boolean
     */
    public boolean isEmpty()
    {
        for (int i = 0; i < arrayMap.length; i++)
        {
            if (arrayMap[i] != null)
            {
                return false;
            }
        }
        return true;
    }

    /*
     * Returns a Set view of the keys contained in this map.
     * 
     * returns HashSet<Key>
     */
    @SuppressWarnings("unchecked")
    public HashSet<Key> keySet()
    {
        HashSet<Key> keySet = new HashSet<Key>();
        Node<?, ?> traverseNode = null;
        for (int i = 0; i < arrayMap.length; i++)
        {
            traverseNode = arrayMap[i];
            while (traverseNode != null)
            {
                keySet.add((Key) traverseNode.getKey());
                traverseNode = traverseNode.getNext();
            }
        }
        return keySet;
    }

    /*
     * Returns a Collection view of the values contained in this map.
     * 
     * returns Collection<Object>
     */
    public Collection<Object> values()
    {
        List<Object> values = new LinkedList<Object>();
        Node<?, ?> traverseNode = null;
        for (int i = 0; i < arrayMap.length; i++)
        {
            traverseNode = arrayMap[i];
            while (traverseNode != null)
            {
                Object value = traverseNode.getValue();
                values.add(value);
                traverseNode = traverseNode.getNext();
            }
        }
        return (Collection<Object>) values;
    }

}