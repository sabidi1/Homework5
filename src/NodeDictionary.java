/*
 * A program that sorts objects by value and key (Dictionary)
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NodeDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V> {

	private DictionaryNode<K, V> firstNode;
	private int numOfEntries;
	
	
	public NodeDictionary()
	{
		firstNode = null;
		numOfEntries = 0;
	}
	
	@Override
	public V add(K key, V value) {
		V temp = null;
		
		DictionaryNode<K, V> currentNode = firstNode;
		DictionaryNode<K, V> prevNode = null;
		
		while((currentNode != null) && key.compareTo(currentNode.getKey()) > 0)	//iterates and if key is smaller than currentnode, it b
		{
			prevNode = currentNode;
			currentNode = currentNode.getNext();
		}
		if((currentNode != null) && key.equals(currentNode.getKey()))	//if thehy are the same, change value a
		{
			temp = currentNode.getValue();
			currentNode.setValue(value);
		}
		else
		{
			DictionaryNode<K, V> newNode = new DictionaryNode<K, V>();
			newNode.setKey(key);
			newNode.setValue(value);
			if(prevNode == null)
			{
				newNode.setNext(firstNode);
				firstNode = newNode;
			}
			else
			{
				newNode.setNext(currentNode);
				prevNode.setNext(newNode);
			}
			
			numOfEntries++;
		}
		
		return temp;
	}

	@Override
	public V remove(K key) {
		DictionaryNode<K, V> currentNode = firstNode;
		DictionaryNode<K, V> prevNode = null;
		V tempValue;
		
		while(currentNode != null)		//iterates through the list
		{
			if(currentNode.getKey() == key)	//if entry to be removed is key
			{
				break;
			}
			else{
				prevNode = currentNode;
				currentNode = currentNode.getNext();
			}
		}
		if(currentNode == null)		//key does not exisit
		{
			return null;
		}
		else if(prevNode == null)	//onlyy 1	
		{
			tempValue = currentNode.getValue();
			firstNode = firstNode.getNext();
			return tempValue;
		}
		else if(currentNode.getNext() == null)	//the node to be removed is the last node
		{
			tempValue = currentNode.getValue();
			prevNode.setNext(null);
			return tempValue;
		}
		else			//in between nodes
		{
			tempValue = currentNode.getValue();
			prevNode.setNext(currentNode.getNext());
			return tempValue;
		}
	}
	
	
	/**
	 		Retrieves from this dictionary the value associated with a given
			search key.
			@param An object search key
			@return value assoicated with search key or null
	 */

	@Override
	public V getValue(K key) {
		DictionaryNode<K, V> currentNode = firstNode;
		while(currentNode != null)
		{
			if(currentNode.getKey() == key)		//if currentNode = key
			{
				return currentNode.getValue();
			}
			else
			{
				currentNode = currentNode.getNext();		//get next node
			}
		}
		return null;		//null if no such object exists
	}

	/**
	 		Checks if entry is in the dictionary
	 */
	public boolean contains(K key) {
		DictionaryNode<K, V> currentNode = firstNode;
		while(currentNode != null)		//iterates as along as theres nodes
		{
			if(currentNode.getKey() == key)		//if currentNode is same as key
			{
				return true;
			}
			else
			{
				currentNode = currentNode.getNext(); 		//get next node
			}
		}
		return false;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		return new KeyIterator();
	}

	@Override
	public Iterator<V> getValueIterator() {
		return new ValueIterator();
	}

	@Override
	public boolean isEmpty() {
		return numOfEntries == 0;
	}

	@Override
	public int getSize() {
		if(isEmpty())
		{
			return 0;
		}
		return numOfEntries;
	}

	@Override
	public void clear() {
		firstNode = null;
	}
	
	private class KeyIterator implements Iterator<K>{

		private DictionaryNode<K, V> nextNode;
		
		private KeyIterator()
		{
			nextNode = firstNode;
		}
		@Override
		public boolean hasNext() {
			
			return nextNode != null;
		}

		@Override
		public K next() {
			K result = null;
			if(hasNext())
			{
				result = nextNode.getKey();
				nextNode = nextNode.getNext();
			}
			else
			{
				throw new NoSuchElementException();
			}
			return result;
		}

	}
	
	private class ValueIterator implements Iterator<V>{

		private DictionaryNode<K, V> nextNode;
		
		private ValueIterator()
		{
			nextNode = firstNode;
		}
		@Override
		public boolean hasNext() {
			return nextNode != null;
		}

		@Override
		public V next() {
			V result = null;
			if(hasNext())
			{
				result = nextNode.getValue();
				nextNode = nextNode.getNext();
			}
			else
			{
				throw new NoSuchElementException();
			}
			return result;
		}
		
		
	}
}
