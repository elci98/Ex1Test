package Ex1Testing;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Functions_GUI implements functions 
{

	ArrayList<function> collection= new ArrayList<function>();
	public Functions_GUI()
	{

	}
	public function get(int index)
	{
		if(index<collection.size())
			return collection.get(index);
		return null;
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
		for(int i=0;i<collection.size();i++)
		{
			System.out.println(collection.get(i).f(1));
		}
		double Xmin=rx.get_min(),Xmax=rx.get_max(),Ymax=ry.get_max(),Ymin=ry.get_min();
		double xAxis=Xmax-Xmin;
		double yAxis=Ymax-Ymin;
		double Xavg=(Xmax+Xmin)/2;
		double Yavg=(Ymax+Ymin)/2;
		Color[] Colors= {Color.BLACK,Color.BLUE,Color.CYAN,Color.DARK_GRAY,Color.GRAY,Color.GREEN,Color.LIGHT_GRAY,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.RED,Color.YELLOW};
		StdDraw.setCanvasSize( width,height); // sets the width and the height of the window
		StdDraw.setXscale(Xmin,Xmax );
		StdDraw.setYscale(Ymin,Ymax);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.03);
		StdDraw.point(Xavg, Yavg);
		StdDraw.setPenRadius(0.005);
		StdDraw.line(Xmin, Yavg, Xmax,Yavg );// x axis line draw
		StdDraw.line(Xavg, Ymin, Xavg, Ymax);//y axis line draw
		StdDraw.setPenRadius(0.0001);
		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 20)); 
		int counter=(int)(-resolution/2);
		
		for(double i=Ymin;i<Ymax;i+=yAxis/resolution)//drawing horizontal lines
		{
			StdDraw.line(Xmin, i, Xmax, i);
			StdDraw.textRight(Xavg, i, Integer.toString(counter++));// xAxis numbers- not finished, still need to get scaled
		}
		counter=(int)-(resolution/2);
		for(double i=Xmin;i<Xmax;i+=xAxis/resolution)//drawing vertical lines
		{
			StdDraw.line(i, Ymin,i , Ymax);
			StdDraw.textRight(i, Yavg, Integer.toString(counter++));//yAxis numbers- not finished, still need to get scaled
		}
		StdDraw.setPenRadius(0.002);
		for(int i=0;i<collection.size();i++)// function drawing
		{
			double Step=xAxis/resolution;
			StdDraw.setPenColor(Colors[i%Colors.length]);
			double x0=Xmin;
			while(x0<Xmax)
			{
				StdDraw.line(x0, collection.get(i).f(x0), x0+Step,collection.get(i).f(x0+Step));
				x0+=Step/50;
			}

		}

	}

	@Override
	public void drawFunctions(String json_file) 
	{
		Object obj= null;
		JSONParser parser= new JSONParser();
		try 
		{
			FileReader reader= new FileReader(json_file); //creating a new file reader @throws IOException
			obj=parser.parse(reader); // parse json file by parameters @throws ParseException
		} 
		catch (IOException |  ParseException e) 
		{
			e.printStackTrace();
		}
		JSONObject jo = (JSONObject) obj; // down casting obj from Object to JSONObject
		long Width = (long)jo.get("Width"); // pulling Width value from json long casting is a must
		long Height=(long)jo.get("Height");
		long Resolution=(long)jo.get("Resolution");
		JSONArray ja = (JSONArray) jo.get("Range_X"); //creating a JSON array with Range_x values
		long Xmin=(long)ja.get(0); //since we`ve got only 2 values we pull them by hand and not by iterator
		double Xmax=(long)ja.get(1); 
		Range rx=new Range((double)Xmin,(double)Xmax); // creating new Range object with the values from the array
		ja = (JSONArray) jo.get("Range_Y"); // equivalent to creating Range rx 
		long Ymin=(long)ja.get(0);
		long Ymax=(long)ja.get(1);
		Range ry=new Range((double)Ymin,(double)Ymax);
		// calling the drawFunctions with the values we`ve got, with casting to fit function deceleration
		drawFunctions((int)Width, (int)Height, rx, ry, (int)Resolution); 
	}
	public String toString()
	{
		return collection.toString();
	}

}
