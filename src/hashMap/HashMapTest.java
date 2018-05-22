package hashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class HashMapTest
{
    HashMap<String, Integer> map = new HashMap<String, Integer>();

    int mapSize = 100;

    @Before
    public void setUp() throws Exception
    {
        for (int i = 0; i < mapSize; i++)
        {
            map.put(String.valueOf(i), i);
        }
    }

    @Test
    public void putHashMap()
    {
        assertEquals(mapSize, map.size());

        int actual = map.get("51");
        assertEquals(51, actual);
    }

    @Test
    public void put2HashMap()
    {
        map.put("the_answer", 42);
        int actual = map.get("the_answer");
        assertEquals(actual, 42);
    }

    @Test
    public void putAnotherMap()
    {
        HashMap<Integer, Boolean> boolMap = new HashMap<Integer, Boolean>();
        boolMap.put(3, true);
        Boolean actual = boolMap.get(3);
        assertEquals(actual, true);
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
        assertEquals(mapSize + 10, actual);

        for (int i = map.size(); i < mapSize + 10; i++)
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

    @Test
    public void sizeOfEmptyHashMap()
    {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        assertEquals(0, map.size());
    }
}
