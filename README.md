# hashMap
* array-based Hashmap with initial length of 16 buckets.
* uses open hashing with closed addressing
* key-value pairs are hold in Node objects

## functions
#### void put(Key key, V value)
Associates the specified value with the specified key in this map.
#### V get(Key key)
Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
#### boolean remove(Key key)
Removes the mapping for the specified key from this map if present.
#### int capacity()
Returns the amount of used buckets.
#### int size()
Returns the number of key-value mappings in this map.
#### void putAll(HashMap<Key, V> map2)
Copies all of the mappings from the specified map to this map.
#### HashMap<Key, V> clone()
Returns a shallow copy of this HashMap instance: the keys and values themselves are not cloned.
#### boolean containsKey(Key key)
Returns true if this map contains a mapping for the specified key.
#### boolean containsValue(V value)
Returns true if this map maps one or more keys to the specified value.
#### HashSet<Node> entrySet()
Returns a Set view of the mappings contained in this map.
#### boolean isEmpty()
Returns true if this map contains no key-value mappings.
#### HashSet<Key> keySet()
Returns a Set view of the keys contained in this map.
#### Collection<Object> values()
Returns a Collection view of the values contained in this map.