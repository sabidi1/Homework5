import java.util.Iterator;


/**
   An ADT Sorted Dictonary using an Array.
   
*/

public class ArrayDictionary<K extends Comparable<? super K>,V> implements DictionaryInterface<K,V>
{
	private final ArrayEntry<K,V> [] dictionary;
	private int numOfEntries;
	
	public ArrayDictionary()
	{
		@SuppressWarnings("unchecked")
		ArrayEntry<K,V>[] tempDictionary = (ArrayEntry<K, V>[])new ArrayEntry[10];
		dictionary = tempDictionary;
		numOfEntries = 0;
	}
	
	public V add(K key, V value) 
	{
		V result = null;
		int topIndex = numOfEntries - 1;
		
		ArrayEntry<K, V> newArrayEntry = new ArrayEntry<K, V>();
		newArrayEntry.setKey(key);
		newArrayEntry.setValue(value);
		
		if(numOfEntries == 0)
		{
			dictionary[0] = newArrayEntry;
			numOfEntries++;
		}
		else
		{
			for(int i = topIndex; i >= 0; i--)
			{
				if(key.compareTo(dictionary[i].getKey()) > 0)
				{
					if(topIndex == i)
					{
						dictionary[i + 1] = newArrayEntry;
					}
					else
					{
						while(topIndex >= i)
						{
							dictionary[topIndex + 1] = dictionary[topIndex];
							topIndex--;
						}
						dictionary[i] = newArrayEntry;
					}
					numOfEntries++;
					return result;
				}
				else if(key.equals(dictionary[i].getKey()))	//If keys are same
				{
					dictionary[i].setKey(key);
					result = dictionary[i].getValue();
					return result;
				}
			}
			topIndex = numOfEntries - 1;
			while(topIndex >= 0)
			{
				dictionary[topIndex + 1] = dictionary[topIndex];
				topIndex--;
			}
			dictionary[0] = (newArrayEntry);
			numOfEntries++;
		}
		return result;
	}
   
    public V remove(K key)
    {
		for (int i = 0; i < numOfEntries; i++)
		{
			if (dictionary[i].getKey().equals(key))
			{
				V thisValue = dictionary[i].getValue();
				
				if (i == numOfEntries-1)		//if its the last entry
				{
					dictionary[i] = null;
				}
				else			//In the middle....if entries are after it, reindex everything in array
				{
					for (int q = i; q < numOfEntries-1; q++)
					{
						dictionary[q] = dictionary[q+1];
					}
					
					dictionary[numOfEntries-1] = null;
				}
				
				numOfEntries--;
				return thisValue;
			}
		}
		
		return null;
    }

    public V getValue(K key)
	{
		for(int i = 0; i < numOfEntries; i++)
		{
			if(dictionary[i].getKey().equals(key))
			{
				return dictionary[i].getValue();
			}
		}
		
		return null;
	}
   
    /**
     * Checks if key is there
     * @return true if 
     */
    public boolean contains(K key)
	{
		for(int i = 0; i < numOfEntries; i++)
		{
			if(dictionary[i].getKey().equals(key))
			{
				return true;
			}
		}
		return false;
	}
   
    
    public Iterator<K> getKeyIterator()
	{
		
		return new ArrayKeyIterator();
	}
   
    public Iterator<V> getValueIterator()
	{
		
		return new ArrayValueIterator();
	}
   
    public class ArrayKeyIterator implements Iterator<K>
    {	
    		private int currentIndex;
    		public ArrayKeyIterator()
    		{
    			currentIndex = -1;
    		}

		@Override
		public boolean hasNext() 
		{
			return dictionary[currentIndex +1] != null;
		}
		@Override
		public K next() 
		{
			currentIndex++;
			return dictionary[currentIndex].getKey();
		}
    }
    	
	public class ArrayValueIterator implements Iterator<V>
	{
		private int currentIndex;
		public ArrayValueIterator()
		{
			currentIndex = -1;
		}
		public boolean hasNext() {
			return dictionary[currentIndex +1] != null;
		}

		@Override
		public V next() {
			currentIndex++;
			return dictionary[currentIndex].getValue();
		}
    }
   
    public boolean isEmpty()
	{
		return numOfEntries == 0;		//return true if numOfEntries equals 0, else return false
	}
   
    
    public int getSize()
	{
		return numOfEntries;
	}
    
    /**
     		Removes all entries
     */
    public void clear()
	{
  
		for(int i = 0; i < numOfEntries; i++)
		{
			dictionary[i] = null;	//set all values to null
		}
		numOfEntries = 0;		//set numOfEntries to 0
	} 
}