package hashMap;

import java.util.Arrays;

public class HashMap<K, V>
{
    private int size;

    private int DEFAULT_CAPACITY = 8;

    @SuppressWarnings("unchecked")
    private Entry<K, V>[] values = new Entry[DEFAULT_CAPACITY];

    public V get(K key)
    {
        for (int i = 0; i < size; i++)
        {
            if (values[i] != null)
            {
                if (values[i].getKey() == key.hashCode())
                {
                    return values[i].getValue();
                }
            }
        }
        return null;
    }

    public void put(K key, V value)
    {
        boolean insert = true;
        for (int i = 0; i < size; i++)
        {
            if (values[i].getKey() == key.hashCode())
            {
                values[i].setValue(value);
                insert = false;
            }
        }
        if (insert)
        {
            ensureCapacity();
            values[size++] = new Entry<K, V>(key, value);
        }
    }

    private void ensureCapacity()
    {
        if (size == values.length)
        {
            int newSize = values.length * 2;
            values = Arrays.copyOf(values, newSize);
        }
    }

    public int size()
    {
        return size;
    }

    public void remove(K key)
    {
        for (int i = 0; i < size; i++)
        {
            values[i].getKey();
            if (values[i].getKey() == key.hashCode())
            {
                values[i] = null;
                size--;
                condenseArray();
            }
        }
    }

    private void condenseArray()
    {
        for (int i = 0; i < size; i++)
        {
            values[i] = values[i + 1];
        }

    }

    public void clear()
    {
        for (int i = 0; i < size; i++)
        {
            values[i] = null;
        }
    }

    public void addAll(HashMap<K, V> map2)
    {
        int secondSize = map2.size();
        int newSize = size + secondSize;
        @SuppressWarnings("unchecked")
        Entry<K, V>[] newValues = new Entry[newSize];

        for (int i = 0; i < size; i++)
        {
            newValues[i] = values[i];
        }
        for (int i = 0; i < secondSize; i++)
        {
            newValues[size + i] = map2.values[i];
        }
        size = newSize;
        values = newValues;
    }
}
