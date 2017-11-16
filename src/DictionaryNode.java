

public class DictionaryNode<K, V> {
	
	private K key;
	private V value;
	private DictionaryNode<K, V> next;
	
	public DictionaryNode()
	{
		next = null;
	}
	
	public void setKey(K newKey)
	{
		key = newKey;
	}
	public void setValue(V newValue)
	{
		value = newValue;
	}
	
	public void setNext(DictionaryNode<K, V> node)
	{
		next = node;
	}
	public K getKey()
	{
		return key;
	}
	public V getValue()
	{
		return value;
	}
	public DictionaryNode<K, V> getNext()
	{
		return next;
	}
}