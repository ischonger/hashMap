# hashMap
* array-based Hashmap with initial length of 16 buckets.
* uses open hashing with closed addressing
* key-value pairs are hold in Node objects

## functions
* void put(Key key, V value)
* V get(Key key)
* boolean remove(Key key)
* int capacity()
* int size()
* void putAll(HashMap<Key, V> map2)
* HashMap<Key, V> clone()
* boolean containsKey(Key key)
* boolean containsValue(V value)
* HashSet<Node> entrySet()
* boolean isEmpty()
* HashSet<Key> keySet()
* Collection<Object> values()