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
	//overloaded equality functions
	boolean compare(float x, float y)
	{
		return x==y;
	}
	boolean compare(double x, double y)
	{
		return x==y;
	}
	boolean compare(int x, int y)
	{
		return x==y;
	}
	boolean compare(char x, char y)
	{
		return x==y;
	}
	boolean compare(String x, String y)
	{
		return x.equals(y);
	}
	boolean compare(X x, X y)
	{
		return x.equals(y);
	}
	boolean compareMapKeys(SingleMap<X,Y> otherMap)
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
	int capacity;
	double loadfactor;
	int size;
	public HashMap(int c, double lf)
	{
		capacity = c;
		loadfactor = lf;
		size = 0;
		hmap = new List[c];
	}
	public int hashValue(X x)
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
		int flag = 0;
		for(SingleMap<X,Y> temp: hmap[hash])
		{
			if(temp.compareKey(x))
			{
				temp.value = y;
				flag = 1;
			}
		}
		if(flag==0)
		{
			hmap[hash].add(new SingleMap(x,y));
			size++;
		}
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
			}
			i++;
		}
	}
	public boolean containsKey(X x)
	{
		int hash = hashValue(x);
		if(hmap[hash]==null)
			return false;
		for(SingleMap<X,Y> temp: hmap[hash])
		{
			if(temp.compareKey(x))
				return true;
		}
		return false;
	}
}
