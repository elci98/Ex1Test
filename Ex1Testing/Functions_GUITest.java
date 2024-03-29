package Ex1Testing;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Ex1.*;
/**
 * Partial JUnit + main test for the GUI_Functions class, expected output from the main:
0) java.awt.Color[r=0,g=0,b=255]  f(x)= plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)
1) java.awt.Color[r=0,g=255,b=255]  f(x)= plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)
2) java.awt.Color[r=255,g=0,b=255]  f(x)= div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)
3) java.awt.Color[r=255,g=200,b=0]  f(x)= -1.0x^4 +2.4x^2 +3.1
4) java.awt.Color[r=255,g=0,b=0]  f(x)= +0.1x^5 -1.2999999999999998x +5.0
5) java.awt.Color[r=0,g=255,b=0]  f(x)= max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)
6) java.awt.Color[r=255,g=175,b=175]  f(x)= min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)

 *
 */
class Functions_GUITest 
{
	public static void main(String[] a) 
	{
		//		Functions_GUI data=FunctionsFactory();
		Functions_GUI data= new Functions_GUI();
		Polynom p1=new Polynom("x");
		Polynom p2=new Polynom("x^2");
		Polynom p3=new Polynom("x^2-4x+2");
		Polynom p4=new Polynom("x^3");
		Polynom p5=new Polynom("x^5-2x^3+9x^2");
		Polynom p6=new Polynom("4x^3-2x^2");
		Polynom p7=new Polynom("x^7");
		Polynom p8=new Polynom("-x");
		data.add(p1);
		data.add(p2);
		data.add(p3);
		data.add(p4);
		data.add(p5);
		data.add(p6);
		data.add(p7);
		data.add(p8);
		int w=1000, h=600, res=200;
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
		data.drawFunctions(w,h,rx,ry,res);
		//data.drawFunctions("Ex1//GUI_params.json");
	}
	private Functions_GUI _data=null;

	@BeforeEach
	void setUp() throws Exception 
	{
		_data = FunctionsFactory();
	}

	@Test
	void testFunctions_GUI() 
	{
	}

	@Test
	void testInitFromFile() 
	{
		try
		{
			_data.saveToFile("test.txt");
			Functions_GUI test=new Functions_GUI();
			test.initFromFile("test.txt");
			if(!test.containsAll(_data))
			{
				assertFalse(false);
			}
			assertTrue(true);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	void testSaveToFile()  
	{
		try
		{
			_data.saveToFile("test.txt");
			Functions_GUI test=new Functions_GUI();
			test.initFromFile("test.txt");
			if(!test.containsAll(_data))
			{
				assertFalse(false);
			}
			assertTrue(true);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static Functions_GUI FunctionsFactory() 
	{
		Functions_GUI ans = new Functions_GUI();
		String s1 = "3.1+2.4x^2-x^4";
		String s2 = "5+2x-3.3x+0.1x^5";
		String[] s3 = {"x+3","x-2", "x-4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		for(int i=1;i<s3.length;i++) 
		{
			cf3.mul(new Polynom(s3[i]));
		}

		ComplexFunction cf = new ComplexFunction("plus", p1,p2);
		ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x+1"),cf3);
		Polynom max =new Polynom("x^2+2");
		cf4.plus(new Monom("2"));
		ans.add(cf.copy());
		ans.add(cf4.copy());
		cf.div(p1);
		ans.add(cf.copy());
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		ans.add(cf5.copy());
		ans.add(cf6.copy());
		ComplexFunction max1 = new ComplexFunction(ans.get(0).copy());
		ComplexFunction min = new ComplexFunction(ans.get(0).copy());
		for(int i=1;i<ans.size();i++) {
			max1.max(ans.get(i));
			min.min(ans.get(i));
		}
		ans.add(max);
		ans.add(min);

		return ans;
	}
}
