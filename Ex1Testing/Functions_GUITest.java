package Ex1Testing;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.function;
/**
 * Partial JUnit + main test for the GUI_Functions class, expected output from the main:
 * 0) java.awt.Color[r=0,g=0,b=255]  f(x)= plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)
1) java.awt.Color[r=0,g=255,b=255]  f(x)= plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)
2) java.awt.Color[r=255,g=0,b=255]  f(x)= div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)
3) java.awt.Color[r=255,g=200,b=0]  f(x)= -1.0x^4 +2.4x^2 +3.1
4) java.awt.Color[r=255,g=0,b=0]  f(x)= +0.1x^5 -1.2999999999999998x +5.0
5) java.awt.Color[r=0,g=255,b=0]  f(x)= max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)
6) java.awt.Color[r=255,g=175,b=175]  f(x)= min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)

 * @author boaz_benmoshe
 *
 */
class Functions_GUITest 
{
	public static void main(String[] a) 
	{
		Functions_GUI data=FunctionsFactory();
		//				Functions_GUI data= new Functions_GUI();
		//		Polynom p = new Polynom("x^2");
		//		Polynom p1 = new Polynom("x^9");
		//		Polynom p2 = new Polynom("x^1");
		//		Polynom p3 = new Polynom("x^3-4x+8");
		//		Polynom p4 = new Polynom("x^9-2x^4");
		//		Polynom p5 = new Polynom("x^3");
		//		data.add(p);
		//		data.add(p1);
		//		data.add(p2);
		//		data.add(p3);
		//		data.add(p4);
		//		data.add(p5);
		//				System.out.println(c.equals(p1, -3, 3, 0.1));


		//		try
		//		{
		//			data.initFromFile("C:\\Users\\אלחנן מהצרי\\eclipse-workspace\\OOP\\Ex1\\function_file.txt");
		//		}
		//		catch(IOException e)
		//		{
		//			e.printStackTrace();
		//		}
		//		System.out.println(data);
		int w=1000, h=600, res=200;
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
		data.drawFunctions(w,h,rx,ry,res);
		System.out.println(data.get(1).f(4.01));
		//data.drawFunctions("Ex1//GUI_params.json");
		//		System.out.println(data);
		//		String fileName="functions.txt";
		//		try 
		//		{
		//			data.saveToFile(fileName);
		//		}
		//		catch(IOException e)
		//		{
		//			e.printStackTrace();
		//		}
	}
	private Functions_GUI _data=null;
	//	@BeforeAll
	//	static void setUpBeforeClass() throws Exception {
	//	}

	@BeforeEach
	void setUp() throws Exception 
	{
		_data = FunctionsFactory();
	}

	@Test
	void testFunctions_GUI() 
	{
		Functions_GUI fG=new Functions_GUI();
		fG.add(new Polynom("x ^4 -9 x^2"));
	}

	@Test
	void testInitFromFile() 
	{
		String fileName = "function_file.txt";
		String filePath="C:\\Users\\אלחנן מהצרי\\eclipse-workspace\\OOP\\Ex1\\";
		try
		{
			_data.initFromFile(filePath+fileName);
		}
		catch(IOException e)
		{
			assertFalse(false);
			e.printStackTrace();
		}
		assertTrue(true);
	}

	@Test
	void testSaveToFile() 
	{
		String fileName ="functions.txt";
		String filePath="";
		try
		{
			_data.saveToFile(filePath+fileName);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		assertTrue(true);
	}

	@Test
	void testDrawFunctions() 
	{
		//_data.drawFunctions();
		//	fail("Not yet implemented");
	}

	@Test
	void testDrawFunctionsIntIntRangeRangeInt() 
	{
		//		_data.drawFunctions();
		//fail("Not yet implemented");
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
