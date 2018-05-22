package hashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class HashMapTest
{
    HashMap<String, Integer> map = new HashMap<String, Integer>();

    @Before
    public void setUp() throws Exception
    {
        for (int i = 0; i < 100; i++)
        {
            map.put(String.valueOf(i), i);
        }
    }

    @Test
    public void putHashMap()
    {
        assertEquals(100, map.size());

        int actual = map.get("51");
        assertEquals(51, actual);
    }

    @Test
    public void removeHashMap()
    {
        for (int i = 0; i < 100; i++)
        {
            map.put(String.valueOf(i), i);
        }

        map.remove("1");
        assertNull(map.get("1"));
    }

    @Test
    public void clearHashMap()
    {
        map.clear();
        assertNull(map.get("1"));
    }

    @Test
    public void addAHashMap()
    {
        HashMap<String, Integer> map2 = new HashMap<String, Integer>();
        for (int i = 0; i < 10; i++)
        {
            map2.put(String.valueOf(i), i);
        }
        map.addAll(map2);

        int actual = map.size();
        assertEquals(110, actual);

        for (int i = map.size(); i < 110; i++)
        {
            assertEquals(map.get(String.valueOf(100 + i)), map2.get(String.valueOf(i)));
        }
    }

    @Test
    public void sizeOfHashMap()
    {
        int expectedSize = map.size();
        assertEquals(expectedSize, 100);
    }

}
