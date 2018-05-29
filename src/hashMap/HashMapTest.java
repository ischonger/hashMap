package hashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class HashMapTest
{
    @Before
    public void setUp() throws Exception
    {
    }

    @Test
    public void putMapWithSameHash()
    {
        HashMap<Key, String> hashmap = new HashMap<Key, String>();
        Key firstNameKey = new Key("FB");
        Key lastNameKey = firstNameKey;
        hashmap.put(firstNameKey, "John");
        hashmap.put(lastNameKey, "Doe");

        assertEquals("John", hashmap.get(firstNameKey));
        assertNotEquals("Doe", hashmap.get(lastNameKey));
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void putHashMap()
    {
        HashMap<Key, String> hashmap = new HashMap<Key, String>();
        @SuppressWarnings("unchecked")
        Key firstNameKey = new Key("first name");
        @SuppressWarnings("unchecked")
        Key lastNameKey = new Key("last name");
        hashmap.put(firstNameKey, "John");
        hashmap.put(lastNameKey, "Doe");

        assertEquals("John", hashmap.get(firstNameKey));
        assertEquals("Doe", hashmap.get(lastNameKey));
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void put2HashMap()
    {
        HashMap<Key, Integer> hashMap = new HashMap<Key, Integer>();
        @SuppressWarnings("unchecked")
        Key zipKey = new Key("dojaqojnweofjdweofnöriweonfösökodnöfraeovnrenvirevrnvüorneövoneraövnaöregvnöaerjvn");
        hashMap.put(zipKey, 985876434);
        int value = hashMap.get(zipKey);
        assertEquals(Integer.valueOf(985876434), hashMap.get(zipKey));
    }

    @Test
    public void put3HashMap()
    {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("dojaqojnweofjdweofnöriweonfösökodnöfraeovnrenvirevrnvüorneövoneraövnaöregvnöaerjvn", 985876434);
        Object value = hashMap.get("dojaqojnweofjdweofnöriweonfösökodnöfraeovnrenvirevrnvüorneövoneraövnaöregvnöaerjvn");
        assertEquals(Integer.valueOf(985876434),
                     hashMap.get("dojaqojnweofjdweofnöriweonfösökodnöfraeovnrenvirevrnvüorneövoneraövnaöregvnöaerjvn"));
    }

    @Test
    public void putABoolMap()
    {
        HashMap<Integer, Boolean> boolMap = new HashMap<Integer, Boolean>();
        boolMap.put(3, true);
        Boolean actual = (Boolean) boolMap.get(3);
        assertEquals(actual, true);
    }

    @Test
    public void removeHashMap()
    {
        HashMap<Integer, String> intMap = new HashMap<Integer, String>();
        intMap.put(1, "first");
        intMap.put(2, "second");
        intMap.put(3, "third");
        intMap.put(1, "erstes");

        intMap.remove(1);
        assertEquals("erstes", intMap.get(1));

        intMap.remove(2);
        assertNull(intMap.get(2));

    }

    @Test
    public void removeHashMapInSecondLayer()
    {
        HashMap<Integer, String> intStringMap = new HashMap<Integer, String>();
        for (int i = 0; i < 32; i++)
        {
            intStringMap.put(i, String.valueOf(i));
        }
        intStringMap.remove(1);
        assertNull(intStringMap.get(1));
    }

    @Test
    public void removeHashMapInDeeperLayer()
    {
        HashMap<Integer, String> intStringMap = new HashMap<Integer, String>();
        for (int i = 0; i < 64; i++)
        {
            intStringMap.put(i, String.valueOf(i));
        }
        assertEquals("17", intStringMap.get(17));

        intStringMap.remove(17);
        assertNull(intStringMap.get(17));

    }

    @Test
    public void clearHashMap()
    {
        HashMap<String, Double> stringDoubleMap = new HashMap<String, Double>();
        for (int i = 0; i < 64; i++)
        {
            stringDoubleMap.put(String.valueOf(i), i + 0.6984635416);
        }
        stringDoubleMap.clear();
        assertNull(stringDoubleMap.get("18"));
    }

    @Test
    public void sizeOfAMap()
    {
        int expectedSize = 64;
        HashMap<Integer, Float> intFloatMap = new HashMap<Integer, Float>();
        for (int i = 0; i < expectedSize; i++)
        {
            intFloatMap.put(i, (float) i);
        }

        assertEquals(expectedSize, intFloatMap.size());
    }

    @Test
    public void sizeOfEmptyHashMap()
    {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        assertEquals(0, map.size());
    }

    @Test
    public void addAHashMap()
    {
        HashMap<Integer, Float> intFloatMap1 = new HashMap<Integer, Float>();
        for (int i = 0; i < 64; i++)
        {
            intFloatMap1.put(i, (float) i);
        }
        HashMap<Integer, Float> intFloatMap2 = new HashMap<Integer, Float>();
        for (int i = 64; i < 128; i++)
        {
            intFloatMap2.put(i, (float) i);
        }

        intFloatMap1.putAll(intFloatMap2);
        assertEquals(128, intFloatMap1.size());

        for (int i = 65; i < intFloatMap1.size(); i++)
        {
            assertEquals(intFloatMap1.get(i), intFloatMap2.get(i));
        }
    }

    @Test
    public void addAHashMapSmallTest()
    {
        HashMap<Integer, Float> intFloatMap1 = new HashMap<Integer, Float>();
        for (int i = 0; i < 8; i++)
        {
            intFloatMap1.put(i, (float) i);
        }

        HashMap<Integer, Float> intFloatMap2 = new HashMap<Integer, Float>();
        for (int i = 8; i < 16; i++)
        {
            intFloatMap2.put(i, (float) i);
        }

        intFloatMap1.putAll(intFloatMap2);
        assertEquals(16, intFloatMap1.size());

        for (int i = 8; i < intFloatMap1.size() + 10; i++)
        {
            assertEquals(intFloatMap1.get(i), intFloatMap2.get(i));
        }
    }

    @Test
    public void cloneAHashMap()
    {
        HashMap<Integer, String> intStringMap = new HashMap<Integer, String>();
        for (int i = 0; i < 64; i++)
        {
            intStringMap.put(i, Integer.toString(i));
        }
        HashMap<Integer, String> copyMap = intStringMap.clone();
        assertEquals("1", copyMap.get(1));
        assertEquals("2", copyMap.get(2));
    }

    @Test
    public void cloneIntBoolMap()
    {
        HashMap<Integer, Boolean> intBoolMap = new HashMap<Integer, Boolean>();
        for (int i = 0; i < 64; i++)
        {
            intBoolMap.put(i, (i % 2) == 0);
        }
        HashMap<Integer, Boolean> copyMap = intBoolMap.clone();
        assertEquals(true, copyMap.get(2));
        assertEquals(false, copyMap.get(3));
    }

    @Test
    public void mapHasAKey()
    {
        HashMap<Key, String> hashmap = new HashMap<Key, String>();
        @SuppressWarnings("unchecked")
        Key firstNameKey = new Key("first name");
        @SuppressWarnings("unchecked")
        Key lastNameKey = new Key("last name");
        hashmap.put(firstNameKey, "John");
        hashmap.put(lastNameKey, "Doe");

        assertFalse(hashmap.containsKey(new Key("a number")));
        assertTrue(hashmap.containsKey(lastNameKey));
    }

    @Test
    public void mapHasAValue()
    {
        int expectedSize = 64;
        HashMap<Integer, Float> intFloatMap = new HashMap<Integer, Float>();
        for (int i = 0; i < expectedSize; i++)
        {
            intFloatMap.put(i, (float) i);
        }

        assertEquals(expectedSize, intFloatMap.size());
        assertTrue(intFloatMap.containsValue((float) 1.0));
        assertFalse(intFloatMap.containsValue((float) 10000000.9));
    }

    // TODO use HashMap with a self-built object (eg adress)
    @Test
    public void adressMap()
    {
        HashMap<Integer, Adress> intAdMap = new HashMap<Integer, Adress>();

        Adress adress1 = new Adress("Erfurt", "street1", "number1", "zip1");
        Adress adress2 = new Adress("Weimar", "street2", "number2", "zip2");
        Adress adress3 = new Adress("Jenahr", "street3", "number3", "zip3");

        intAdMap.put(1, adress1);
        intAdMap.put(2, adress2);
        intAdMap.put(3, adress3);

        Adress receivedAdress1 = intAdMap.get(1);
        Adress receivedAdress2 = intAdMap.get(2);
        Adress receivedAdress3 = intAdMap.get(3);

        String city1 = receivedAdress1.getCity();
        String city2 = receivedAdress2.getCity();
        String city3 = receivedAdress3.getCity();

        assertEquals("Erfurt", city1);
        assertEquals("Weimar", city2);
        assertEquals("Jenahr", city3);
    }
    // TODO fix HashMap<String, Int>
}
