package Ex1Testing;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Functions_GUI implements functions 
{

	ArrayList<function> collection= new ArrayList<function>();
	public Functions_GUI()
	{

	}
	public boolean add(function arg0) 
	{
		collection.add(arg0);
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) 
	{
		collection.addAll(arg0);
		return true;
	}

	@Override
	public void clear() 
	{
		collection.clear();
	}

	@Override
	public boolean contains(Object arg0) 
	{
		return collection.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) 
	{
		return collection.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() 
	{
		return collection.isEmpty();
	}

	@Override
	public Iterator<function> iterator() 
	{
		return collection.iterator();
	}

	@Override
	public boolean remove(Object arg0) 
	{
		arg0=(function)arg0;
		if(!collection.contains(arg0))
		{
			System.out.println("collection do not contain such function");
			return false;
		}
		return collection.remove(arg0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean removeAll(Collection<?> arg0) 
	{
		arg0=(Collection<function>)arg0;
		Iterator<function> i = collection.iterator();
		while(i.hasNext())
		{
			function f=i.next();
			if(!collection.contains(f))
			{
				System.out.println("collection do not contain such function: "+f);
				return false;
			}
		}
		return collection.removeAll(arg0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean retainAll(Collection<?> arg0) 
	{
		arg0=(Collection<function>)arg0;
		return collection.retainAll(arg0);
	}

	@Override
	public int size() 
	{
		return collection.size();
	}

	@Override
	public Object[] toArray() 
	{
		return collection.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) 
	{
		return collection.toArray(arg0);
	}

	@Override
	public void initFromFile(String file) throws IOException 
	{
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String line="";
		ComplexFunction cf=new ComplexFunction(new Monom("0"));
		while((line = buffer.readLine())!=null)
		{
			function f= cf.initFromString(line);
			collection.add(f);
		}
		buffer.close();
	}

	@Override
	public void saveToFile(String file) throws IOException 
	{
		File file1 = new File(file);
		file1.createNewFile();
		BufferedWriter buffer = new BufferedWriter(new FileWriter(file1));
		Iterator<function> i = collection.iterator();
		while(i.hasNext())
		{
			function f= i.next();
			buffer.write(f.toString()+"\n");

		}
		buffer.close();	
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) 
	{
	}

	@Override
	public void drawFunctions(String json_file) 
	{
		// TODO Auto-generated method stub

	}
	public String toString()
	{
		return collection.toString();
	}

}
