package Ex1;

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
import org.json.simple.*;
import org.json.simple.parser.*;

public class Functions_GUI implements functions 
{
	/**
	 * we chose ArrayList as our function collection
	 * */
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

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection<? extends function> arg0) 
	{
		arg0=(Collection<function>)arg0;
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
		if(!collection.contains(arg0))
		{
			System.out.println("collection do not contain such function");
			return false;
		}
		return collection.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) 
	{
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

	@Override
	public boolean retainAll(Collection<?> arg0) 
	{
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

	/**
	 * initliaze new collection of function from file 
	 * */
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
	/**
	 * save our current functions collection to file
	 * */
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
			buffer.write(f.toString());
			buffer.newLine();
		}
		buffer.close();	
	}
	/**
	 * this methos draws the functions in a GUI window
	 * */
	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) 
	{
		double Xmin=rx.get_min(),Xmax=rx.get_max(),Ymax=ry.get_max(),Ymin=ry.get_min();
		double xAxis=Xmax-Xmin;
		double yAxis=Ymax-Ymin;
		Color[] Colors= {Color.GREEN,Color.BLUE,Color.CYAN,Color.RED,Color.GRAY,Color.BLACK,Color.LIGHT_GRAY,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.DARK_GRAY,Color.YELLOW};
		StdDraw.setCanvasSize(width,height); // sets the width and the height of the window
		StdDraw.setXscale(Xmin,Xmax);
		StdDraw.setYscale(Ymin,Ymax);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.03);
		StdDraw.point(0,0); // draw (0,0) point
		StdDraw.setPenRadius(0.005);
		StdDraw.line(Xmin,0,Xmax,0);// x axis line draw
		StdDraw.line(0,Ymin,0,Ymax);//y axis line draw
		StdDraw.setPenRadius(0.0001);
		StdDraw.setFont(new Font("Ariel", Font.BOLD, 20)); 
		//drawing horizontal lines and coordinations numbers, we divide by 20 to get a nice coordinations system
		for(double i=Ymin;i<Ymax;i+=yAxis/20)
		{
			StdDraw.line(Xmin, i, Xmax, i);
			StdDraw.textRight(0, i, Integer.toString((int)i));// xAxis numbers
		}
		for(double i=Xmin;i<Xmax;i+=xAxis/20)//drawing vertical lines, 
		{
			StdDraw.line(i, Ymin,i , Ymax);
			StdDraw.textRight(i, 0, Integer.toString((int)i));//yAxis numbers
		}
		StdDraw.setPenRadius(0.00358);
		double Step=xAxis/resolution;
		for(int i=0;i<collection.size();i++)// function drawing
		{
			double x0=Xmin;
			StdDraw.setPenColor(Colors[i%Colors.length]);
			while(x0<Xmax)
			{
				//here we limit the number of digits after the floating point to 16 digits to prevent inaccuracy
				double y0=(double)Math.round(collection.get(i).f(x0) * 10000000000000000d) / 10000000000000000d;
				double y1 =(double)Math.round(collection.get(i).f(x0+Step) * 10000000000000000d) / 10000000000000000d;
				StdDraw.line(x0, y0,x0+Step,y1);
				x0+=Step;
			}
		}

	}
	/**
	 * same method as above, 
	 * only difference is that this method read the GUI parameters from json file.
	 * it uses json simple to do so.
	 * @throws IOException if file is unreadable
	 * @throws NullPointerException if json given parameter is invalid
	 * @throws ParseException from JSONParser
	 * */
	@Override
	public void drawFunctions(String json_file) 
	{
		Object obj= null;
		JSONParser parser= new JSONParser();
		try 
		{
			obj=parser.parse(new FileReader(json_file)); // parse json file by parameters @throws ParseException,IOException
			JSONObject jo = (JSONObject) obj; // down casting obj from Object to JSONObject
			long Width = (long)jo.get("Width"); // pulling Width value from json long casting is a must
			long Height=(long)jo.get("Height");
			long Resolution=(long)jo.get("Resolution");
			JSONArray ja = (JSONArray) jo.get("Range_X"); //creating a JSON array with Range_x values
			long Xmin=(long)ja.get(0); //since we`ve got only 2 values we pull them by hand and not by iterator
			long Xmax=(long)ja.get(1); 
			Range rx=new Range((double)Xmin,(double)Xmax); // creating new Range object with the values from the array
			ja = (JSONArray) jo.get("Range_Y"); // equivalent to creating Range rx 
			long Ymin=(long)ja.get(0);
			long Ymax=(long)ja.get(1);
			Range ry=new Range((double)Ymin,(double)Ymax);
			// call drawFunctions with the values we`ve got, with casting to fit function deceleration
			drawFunctions((int)Width, (int)Height, rx, ry, (int)Resolution);
		} 
		catch (IOException | ParseException | NullPointerException e) 
		{
			int w=1000, h=600, res=200;
			Range rx = new Range(-10,10);
			Range ry = new Range(-5,15);
			drawFunctions(w,h,rx,ry,res);
			if(e instanceof NullPointerException)
				throw new NullPointerException("json invalid parameter");
			e.printStackTrace();
		}
	}
	public String toString()
	{
		return collection.toString();
	}

}
