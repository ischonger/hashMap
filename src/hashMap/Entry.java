package hashMap;

public class Entry<K, V>
{
    private final K key;

    private V value;

    public Entry(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    public int getKey()
    {
        return key.hashCode();
    }

    public V getValue()
    {
        return value;
    }

    public void setValue(V value)
    {
        this.value = value;
    }
}
