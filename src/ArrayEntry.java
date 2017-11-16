public class ArrayEntry<K, V> {
	private K key;
	private V value;
	
	public ArrayEntry()
	{
		key = null;
		value = null;
	}
	public K getKey()
	{
		return key;
	}
	
	public V getValue()
	{
		return value;
	}
	
	public void setKey(K newKey)
	{
		key = newKey;
	}
	public void setValue(V newValue)
	{
		value = newValue;
	}
	
}


