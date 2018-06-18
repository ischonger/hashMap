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
    /*
     * key of Type Key, using two identical keys
     */
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

    /*
     * same test as before; twisted order of assert-tests
     */
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

    /*
     * testing for collision: both string-keys "AaAa" and "BBBB" have the same hash code 2031744
     */
    @Test
    public void putMapWithSameHashString1()
    {
        HashMap<String, String> hashmap = new HashMap<String, String>();
        hashmap.put("AaAa", "john");
        hashmap.put("BBBB", "doe");

        assertEquals("john", hashmap.get("AaAa"));
        assertEquals("doe", hashmap.get("BBBB"));
    }

    /*
     * same test as putMapWithSameHashString1() twisted assertion order and false negative tests
     */
    @Test
    public void putMapWithSameHashString2()
    {
        HashMap<String, String> hashmap = new HashMap<String, String>();
        hashmap.put("AaAa", "john");
        hashmap.put("BBBB", "doe");

        assertEquals("doe", hashmap.get("BBBB"));
        assertEquals("john", hashmap.get("AaAa"));
        assertNotEquals("john", hashmap.get("BBBB"));
        assertNotEquals("doe", hashmap.get("AaAa"));
    }

    /*
     * testing put() with a long string
     */
    @Test
    public void putTestLongString()
    {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("dojaqojnweofjdweofnör789iweonfösökodnöfraeo vnrenvirevrnvüorneövoneraövnaöregvnöaerjvn", 985876434);
        assertEquals(Integer.valueOf(985876434),
                     hashMap.get("dojaqojnweofjdweofnör789iweonfösökodnöfraeo vnrenvirevrnvüorneövoneraövnaöregvnöaerjvn"));
    }

    /*
     * testing put() for a integer-boolean map
     */
    @Test
    public void putTestBool()
    {
        HashMap<Integer, Boolean> boolMap = new HashMap<Integer, Boolean>();
        boolMap.put(3, true);
        Boolean actual = boolMap.get(3);
        assertEquals(actual, true);
    }

    /*
     * test remove()
     */
    @Test
    public void removeTest()
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

    /*
     * remove an element from an empty map
     */
    @Test
    public void removeFromEmptyMap()
    {
        HashMap<Integer, String> intMap = new HashMap<Integer, String>();
        assertFalse(intMap.remove(7));
    }

    /*
     * remove a node from any deeper layer second layer nodes can be reached via traversing from first layer nodes using
     * node.getNext()
     */
    @Test
    public void removeFromMapInDeeperLayer()
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

    /*
     * testing clear()
     */
    @Test
    public void clearMap()
    {
        HashMap<String, Double> stringDoubleMap = new HashMap<String, Double>();
        for (int i = 0; i < 64; i++)
        {
            stringDoubleMap.put(String.valueOf(i), i + 0.6984635416);
        }
        stringDoubleMap.clear();
        assertNull(stringDoubleMap.get("18"));
    }

    /*
     * testing size() size() returns the number of nodes (=entries) in the hashmap
     */
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

    /*
     * check the size of an empty map
     */
    @Test
    public void sizeOfEmptyMap()
    {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        assertEquals(0, map.size());
    }

    private HashMap<Integer, Float> putAllTest(int lengthOfMap1, int lengthOfMap2)
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

        return intFloatMap1;
    }

    /*
     * test putAll() function with different ranges putAllTest() builds a hashmap from 0 to first delimiter value and a
     * second hashmap from first delimiter + 1 to second delimiter. Then it adds the values from the second hashmap
     * using putAll() to the first hashmap. Afterwards it checks whether the second half of the first hashmap equals
     * with the secondly generated hashmap.
     */
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

    @Test
    public void putAll2048()
    {
        putAllTest(2048, 4096);
    }

    /*
     * same test as putAllTest() but using random values with delimiters 512 and 1024. this function will be used below
     * in compareExecutionTime()
     */
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

    /*
     * test for putting an empty hashmap in another should throw NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void nullPointerExceptionInPutAll()
    {
        Random rand = new Random();
        int lengthOfMap1 = 512;
        HashMap<Integer, Float> intFloatMap1 = new HashMap<Integer, Float>();
        for (int i = 0; i < lengthOfMap1; i++)
        {
            intFloatMap1.put(i, rand.nextFloat() + 1);
        }

        HashMap<Integer, Float> intFloatMap2 = new HashMap<Integer, Float>();

        intFloatMap1.putAll(intFloatMap2);
    }

    /*
     * testing clone() for a <Integer, String> hashmap tests if x.equals(x.clone()) is true tests if x.getClass ==
     * x.clone().getClass() is true
     */
    @Test
    public void cloneAMap()
    {
        HashMap<Integer, String> intStringMap = new HashMap<Integer, String>();
        for (int i = 0; i < 64; i++)
        {
            intStringMap.put(i, Integer.toString(i));
        }
        HashMap<Integer, String> copyMap = intStringMap.clone();
        assertEquals("1", copyMap.get(1));
        assertEquals("2", copyMap.get(2));

        assertEquals(copyMap, intStringMap);
        assertEquals(copyMap.getClass(), intStringMap.getClass());
    }

    /*
     * testing clone() for an <Integer, Boolean> hashmap
     */
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

        assertEquals(copyMap, intBoolMap);
        assertEquals(copyMap.getClass(), intBoolMap.getClass());
    }

    /*
     * testing if correct key will be found and if unsaved key will not be found
     */
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

    /*
     * testing if map contains correct values
     */
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

    /*
     * testing if complex objects can be used in hashmaps too using Adress as a complex structure (= self-built class)
     * this test uses Adress as value
     */
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

    /*
     * testing if complex objects can be used in hashmaps too using Adress as a complex structure (= self-built class)
     * this test uses Adress as key
     */
    @Test
    public void adressAsAKey()
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

    /*
     * testing getEntry()
     */
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

    /*
     * prints the set of a hashmap using getEntry. using @Ignore this test will be ignored to avoid spamming output
     */
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

    /*
     * testing isEmpty() for a hashmap which was filled and then cleared by using remove() function over all first layer
     * nodes (= entries)
     */
    @Test
    public void isMapEmptyUsingRemove()
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

    /*
     * testing isEmpty() for a hashmap which was filled and then cleared by using clear() function
     */
    @Test
    public void isMapEmptyUsingClear()
    {
        HashMap<Integer, Adress> intAdMap = new HashMap<Integer, Adress>();
        Random rand = new Random();

        for (int i = 1; i <= 100; i++)
        {
            intAdMap.put(i,
                         new Adress(Integer.toString(rand.nextInt(100)), Integer.toString(rand.nextInt(100)), Integer.toString(rand.nextInt(100)), Integer.toString(rand.nextInt(100))));
        }

        intAdMap.clear();

        assertTrue(intAdMap.isEmpty());
    }

    /*
     * testing isEmpty() for an empty hashmap
     */
    @Test
    public void isNewMapEmpty()
    {
        HashMap<Integer, Adress> intAdMap = new HashMap<Integer, Adress>();
        assertTrue(intAdMap.isEmpty());
    }

    /*
     * testing getKey()
     */
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

    /*
     * testing values()
     */
    @Test
    public void getValuesFromMap()
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

    /*
     * does the same test as putAllRandomValues() but using a hashmap without expansion step in the put()-function
     */
    @Test
    public void putAllRandomValuesNoExpansion()
    {
        Random rand = new Random();
        int lengthOfMap1 = 512;
        int lengthOfMap2 = 1024;
        HashMap<Integer, Float> intFloatMap1 = new HashMap<Integer, Float>(false);
        for (int i = 0; i < lengthOfMap1; i++)
        {
            intFloatMap1.put(i, rand.nextFloat() + 1);
        }

        HashMap<Integer, Float> intFloatMap2 = new HashMap<Integer, Float>(false);
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

    /*
     * compares the execution time of two hashmaps whilst first hashmaps in putAllRandomValues() are using the expansion
     * step but not those in putAllRandomValuesNoExpansion()
     */
    @Test
    public void compareExecutionTime()
    {
        long startTime = System.currentTimeMillis();
        putAllRandomValues();
        long stopTime = System.currentTimeMillis();
        long elapsedTimeWithExpansion = stopTime - startTime;

        long startTime2 = System.currentTimeMillis();
        putAllRandomValuesNoExpansion();
        long stopTime2 = System.currentTimeMillis();
        long elapsedTimeNoExpansion = stopTime2 - startTime2;

        assertTrue(elapsedTimeWithExpansion > elapsedTimeNoExpansion);
    }

    /*
     * testing illegal arguments in hashmap constructor
     */
    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentsInConstructor()
    {
        @SuppressWarnings("unused")
        HashMap<Double, Double> doubleMap = new HashMap<Double, Double>(-5, 0, true);
    }
}
