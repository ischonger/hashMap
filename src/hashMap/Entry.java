package hashMap;

public class Entry<V>
{
    private V value;

    public Entry(V value)
    {
        this.value = value;
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
