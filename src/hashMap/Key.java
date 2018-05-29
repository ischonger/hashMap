package hashMap;

public class Key<K>
{
    private K key;

    Key(K key)
    {
        this.key = key;
    }

    public K getKey()
    {
        return key;
    }

    @Override
    public boolean equals(Object obj)
    {
        return key.equals((String) obj);
    }
}
