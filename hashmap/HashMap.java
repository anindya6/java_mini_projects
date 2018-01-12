import java.io.*;
import java.util.*;
class SingleMap<X,Y>
{
	X key;
	Y value;
	SingleMap(X key, Y value)
	{
		this.key = key;
		this.value = value;
	}
	boolean compare(X x, X y)
	{
		return x.equals(y);
	}
	//overloaded compare functions, compare key or compare object
	boolean compareKey(SingleMap<X,Y> otherMap)
	{
		return compare(key, otherMap.key);
	}
	boolean compareKey(X key)
	{
		return compare(this.key, key);
	}
	Y getValue()
	{
		return value;
	}
}
public class HashMap<X,Y>
{
	List<SingleMap<X,Y>> [] hmap;
	Set<X> keys;
	int capacity;
	double loadfactor;
	int size;
	public HashMap(int c, double lf)
	{
		capacity = c;
		loadfactor = lf;
		size = 0;
		hmap = new List[c];
		keys = new HashSet<>();
	}
	private int hashValue(X x)
	{
		String temp = ""+x;
		int hash = (temp.hashCode()%capacity);
		System.out.println(capacity+" : "+hash);
		if(hash<0)
			hash = capacity + hash;
		return hash;
	}
	public void insert(X x, Y y)
	{
		int hash = hashValue(x);
		if(hmap[hash] == null)
			hmap[hash] = new LinkedList<>();
		for(SingleMap<X,Y> temp: hmap[hash])
		{
			if(temp.compareKey(x))
			{
				temp.value = y;
				return;
			}
		}
		hmap[hash].add(new SingleMap(x,y));
		keys.add(x);
		size++;
	}
	public Y get(X x)
	{
		int hash = hashValue(x);
		if (hmap[hash]==null)
			return null;
		for(SingleMap<X,Y> temp: hmap[hash])
		{
			if(temp.compareKey(x))
				return temp.getValue();
		}
		return null;
	}
	public void delete(X x)
	{
		int hash = hashValue(x);
		if(hmap[hash]==null)
			return;
		int i=0;
		for(SingleMap<X,Y> temp: hmap[hash])
		{
			if(temp.compareKey(x))
			{
				hmap[hash].remove(i);
				size--;
				keys.remove(x);
				return;
			}
			i++;
		}
	}
	public boolean containsKey(X x)
	{
		int hash = hashValue(x);
		if(hmap[hash]==null)
			return false;
		/*if maintain a set of keys, this will be faster, but I intend on doing keyset in a better way,
		* so im not going to use this:
		return keys.contains(x);
		*/
		for(SingleMap<X,Y> temp: hmap[hash])
		{
			if(temp.compareKey(x))
				return true;
		}
		return false;
	}
	private void rehash()
	{//this is where you rehash when the load factor is exceeded
	}
	public Set<X> keySet()
	{
		//I am tempted to use HashSet<X> for this, trying to figure out a less hacky way
		return keys;
	}
}
