package hashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;

public class HashMapTest
{
    @Test
    public void putMapWithSameHash1()
    {
        @SuppressWarnings("rawtypes")
        HashMap<Key, String> hashmap = new HashMap<Key, String>();
        Key<String> firstNameKey = new Key<String>("FB");
        Key<String> lastNameKey = firstNameKey;
        hashmap.put(firstNameKey, "John");
        hashmap.put(lastNameKey, "Doe");

        assertEquals("John", hashmap.get(firstNameKey));
        assertNotEquals("Doe", hashmap.get(lastNameKey));
    }

    @Test
    public void putMapWithSameHash2()
    {
        @SuppressWarnings("rawtypes")
        HashMap<Key, String> hashmap = new HashMap<Key, String>();
        Key<String> firstNameKey = new Key<String>("FB");
        hashmap.put(firstNameKey, "John");
        hashmap.put(firstNameKey, "Doe");

        assertNotEquals("Doe", hashmap.get(firstNameKey));
        assertEquals("John", hashmap.get(firstNameKey));
    }

    @Test
    public void putMapWithSameHashString1()
    {
        HashMap<String, String> hashmap = new HashMap<String, String>();
        hashmap.put("AaAa", "john");
        hashmap.put("BBBB", "doe");

        assertEquals("john", hashmap.get("AaAa"));
        assertEquals("doe", hashmap.get("BBBB"));
    }

    @Test
    public void putMapWithSameHashString2()
    {
        HashMap<String, String> hashmap = new HashMap<String, String>();
        hashmap.put("AaAa", "john");
        hashmap.put("BBBB", "doe");

        assertEquals("doe", hashmap.get("BBBB"));
        assertEquals("john", hashmap.get("AaAa"));
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void putHashMap()
    {
        HashMap<Key, String> hashmap = new HashMap<Key, String>();
        Key<String> firstNameKey = new Key<String>("first name");
        Key<String> lastNameKey = new Key<String>("last name");
        hashmap.put(firstNameKey, "John");
        hashmap.put(lastNameKey, "Doe");

        assertEquals("John", hashmap.get(firstNameKey));
        assertEquals("Doe", hashmap.get(lastNameKey));
    }

    @Test
    public void put3HashMap()
    {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("dojaqojnweofjdweofnör789iweonfösökodnöfraeo vnrenvirevrnvüorneövoneraövnaöregvnöaerjvn", 985876434);
        assertEquals(Integer.valueOf(985876434),
                     hashMap.get("dojaqojnweofjdweofnör789iweonfösökodnöfraeo vnrenvirevrnvüorneövoneraövnaöregvnöaerjvn"));
    }

    @Test
    public void putABoolMap()
    {
        HashMap<Integer, Boolean> boolMap = new HashMap<Integer, Boolean>();
        boolMap.put(3, true);
        Boolean actual = boolMap.get(3);
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
    public void removeFromEmptyHashmap()
    {
        HashMap<Integer, String> intMap = new HashMap<Integer, String>();
        assertFalse(intMap.remove(7));
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
    public void putAll16()
    {
        putAllTest(8, 16);
    }

    @Test
    public void putAll256()
    {
        putAllTest(128, 256);
    }

    @Test
    public void putAll1024()
    {
        putAllTest(512, 1024);
    }

    public void putAllTest(int lengthOfMap1, int lengthOfMap2)
    {
        HashMap<Integer, Float> intFloatMap1 = new HashMap<Integer, Float>();
        for (int i = 0; i < lengthOfMap1; i++)
        {
            intFloatMap1.put(i, (float) i);
        }

        HashMap<Integer, Float> intFloatMap2 = new HashMap<Integer, Float>();
        for (int i = lengthOfMap1; i < lengthOfMap2; i++)
        {
            intFloatMap2.put(i, (float) i);
        }

        intFloatMap1.putAll(intFloatMap2);
        assertEquals(lengthOfMap2, intFloatMap1.size());

        for (int i = lengthOfMap1; i < lengthOfMap2; i++)
        {
            assertEquals(intFloatMap1.get(i), intFloatMap2.get(i));
        }
    }

    @Test
    public void putAllRandomValues()
    {
        Random rand = new Random();
        int lengthOfMap1 = 512;
        int lengthOfMap2 = 1024;
        HashMap<Integer, Float> intFloatMap1 = new HashMap<Integer, Float>();
        for (int i = 0; i < lengthOfMap1; i++)
        {
            intFloatMap1.put(i, rand.nextFloat() + 1);
        }

        HashMap<Integer, Float> intFloatMap2 = new HashMap<Integer, Float>();
        for (int i = lengthOfMap1; i < lengthOfMap2; i++)
        {
            intFloatMap2.put(i, rand.nextFloat() + 1);
        }

        intFloatMap1.putAll(intFloatMap2);
        assertEquals(lengthOfMap2, intFloatMap1.size());

        for (int i = lengthOfMap1; i < lengthOfMap2; i++)
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
        @SuppressWarnings("rawtypes")
        HashMap<Key, String> hashmap = new HashMap<Key, String>();
        Key<String> firstNameKey = new Key<String>("first name");
        Key<String> lastNameKey = new Key<String>("last name");
        hashmap.put(firstNameKey, "John");
        hashmap.put(lastNameKey, "Doe");

        assertFalse(hashmap.containsKey(new Key<String>("a number")));
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

        assertEquals("Erfurt", intAdMap.get(1).getCity());
        assertEquals("Weimar", intAdMap.get(2).getCity());
        assertEquals("Jenahr", intAdMap.get(3).getCity());
    }

    @Test
    public void adressAsKey()
    {
        HashMap<Adress, String> adIntMap = new HashMap<Adress, String>();

        Adress adress1 = new Adress("Erfurt", "street1", "number1", "zip1");
        Adress adress2 = new Adress("Weimar", "street2", "number2", "zip2");
        Adress adress3 = new Adress("Jenahr", "street3", "number3", "zip3");

        adIntMap.put(adress1, "dangerous");
        adIntMap.put(adress2, "eligible");
        adIntMap.put(adress3, "granted");

        assertEquals("dangerous", adIntMap.get(adress1));
        assertEquals("eligible", adIntMap.get(adress2));
        assertEquals("granted", adIntMap.get(adress3));
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void getEntrySet()
    {
        Random rand = new Random();
        int lengthOfMap1 = 512;
        HashMap<Integer, Integer> intFloatMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < lengthOfMap1; i++)
        {
            intFloatMap.put(i, rand.nextInt(lengthOfMap1) + 1);
        }

        HashSet<Node> nodeSet = intFloatMap.entrySet();
        for (Node node : nodeSet)
        {
            assertEquals(node.getValue(), intFloatMap.get((Integer) node.getKey()));
        }
    }

    @SuppressWarnings("rawtypes")
    @Ignore
    @Test
    public void getEntrySetPrinted()
    {
        Random rand = new Random();
        int lengthOfMap1 = 512;
        HashMap<Integer, Integer> intFloatMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < lengthOfMap1; i++)
        {
            intFloatMap.put(i, rand.nextInt(lengthOfMap1) + 1);
        }

        HashSet<Node> nodeSet = intFloatMap.entrySet();

        System.out.println("{");
        for (Node node : nodeSet)
        {
            System.out.println(node.toString());
        }
        System.out.println("}");
    }

    @Test
    public void isHashmapEmpty()
    {
        HashMap<Integer, Adress> intAdMap = new HashMap<Integer, Adress>();
        Random rand = new Random();

        for (int i = 1; i <= 100; i++)
        {
            intAdMap.put(i,
                         new Adress(Integer.toString(rand.nextInt(100)), Integer.toString(rand.nextInt(100)), Integer.toString(rand.nextInt(100)), Integer.toString(rand.nextInt(100))));
        }

        for (int i = 1; i <= 100; i++)
        {
            intAdMap.remove(i);
        }

        assertTrue(intAdMap.isEmpty());
    }

    @Test
    public void isNewHashmapEmpty()
    {
        HashMap<Integer, Adress> intAdMap = new HashMap<Integer, Adress>();
        assertTrue(intAdMap.isEmpty());
    }

    @Test
    public void getKeySet()
    {
        int lengthOfMap = 12;
        HashMap<Integer, String> intFloatMap = new HashMap<Integer, String>();
        for (int i = 0; i < lengthOfMap; i++)
        {
            intFloatMap.put(i, Integer.toString(i));
        }

        HashSet<Integer> keySet = intFloatMap.keySet();
        Integer i = 0;
        for (Integer key : keySet)
        {
            key.equals(i);
            i++;
        }
    }

    @Test
    public void getValuesFromHashMap()
    {
        HashMap<Adress, String> adStringMap = new HashMap<Adress, String>();

        Adress adress1 = new Adress("Erfurt", "street1", "number1", "zip1");
        Adress adress2 = new Adress("Weimar", "street2", "number2", "zip2");
        Adress adress3 = new Adress("Jenahr", "street3", "number3", "zip3");

        adStringMap.put(adress1, "dangerous");
        adStringMap.put(adress2, "eligible");
        adStringMap.put(adress3, "granted");

        String[] cityNames = new String[3];
        cityNames[0] = "dangerous";
        cityNames[1] = "eligible";
        cityNames[2] = "granted";
        Collection<Object> mapValues = adStringMap.values();

        assertEquals(adStringMap.size(), mapValues.size());

        for (Object value : mapValues)
        {
            assertTrue(adStringMap.containsValue((String) value));
        }
    }
}
