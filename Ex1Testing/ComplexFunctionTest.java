package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Ex1.*;

class ComplexFunctionTest 
{
	function f;
	Monom m;
	Polynom p,p1;
	ComplexFunction cf;

	@Test
	void testComplexFunctionFunction() 
	{
		boolean flag=false;
		m=new Monom("x^2");
		cf=new ComplexFunction(m);
		if (cf.left() instanceof Monom) 
			flag=true;
		String expected ="x^2";
		String actual=cf.toString();
		assertTrue(flag);
		assertEquals(expected,actual);
	}

	@Test
	void testComplexFunctionStringFunctionFunction() 
	{
		m=new Monom("x^2");
		p=new Polynom("3x^3+2x^2");
		f=new ComplexFunction("Plus",m,p);
		String expected="plus(x^2,3.0x^3+2.0x^2)";
		String actual=f.toString();
		assertEquals(expected, actual);
	}

	@Test
	void testPlus() 
	{
		p=new Polynom("3x^7-5x^3+9");
		cf= new ComplexFunction(new Polynom("x^5-5.5x^3+2x"));
		cf.plus(p);
		String expected="plus(x^5-5.5x^3+2.0x,3.0x^7-5.0x^3+9.0)";
		String actual=cf.toString();
		assertEquals(expected, actual);
	}

	@Test
	void testMul() 
	{
		p=new Polynom("3x^7-5x^3+9");
		cf= new ComplexFunction(new Polynom("x^5-5.5x^3+2x"));
		cf.mul(p);
		String expected="mul(x^5-5.5x^3+2.0x,3.0x^7-5.0x^3+9.0)";
		String actual=cf.toString();
		assertEquals(expected, actual);
	}

	@Test
	void testDiv() 
	{
		p=new Polynom("3x^7-5x^3+9");
		cf= new ComplexFunction(new Polynom("x^5-5.5x^3+2x"));
		cf.div(p);
		String expected="div(x^5-5.5x^3+2.0x,3.0x^7-5.0x^3+9.0)";
		String actual=cf.toString();
		assertEquals(expected, actual);	
	}

	@Test
	void testMax() 
	{
		p=new Polynom("3x^7-5x^3+9");
		cf= new ComplexFunction(new Polynom("x^5-5.5x^3+2x"));
		cf.max(p);
		String expected="max(x^5-5.5x^3+2.0x,3.0x^7-5.0x^3+9.0)";
		String actual=cf.toString();
		assertEquals(expected, actual);	
	}

	@Test
	void testMin() 
	{
		p=new Polynom("3x^7-5x^3+9");
		ComplexFunction cf= new ComplexFunction(new Polynom("x^5-5.5x^3+2x"));
		cf.min(p);
		String expected="min(x^5-5.5x^3+2.0x,3.0x^7-5.0x^3+9.0)";
		String actual=cf.toString();
		assertEquals(expected, actual);	
	}

	@Test
	void testComp() 
	{
		p=new Polynom("3x^7-5x^3+9");
		ComplexFunction cf= new ComplexFunction(new Polynom("x^5-5.5x^3+2x"));
		cf.comp(p);
		String expected="comp(x^5-5.5x^3+2.0x,3.0x^7-5.0x^3+9.0)";
		String actual=cf.toString();
		assertEquals(expected, actual);	
	}

	@Test
	void testF() 
	{
		double epsilon=0.0001;
		boolean flag=false;
		cf=new ComplexFunction(new Monom("0"));
		f=cf.initFromString("comp(x^5-4x^4,max(mul(x^3-2x^2+4,x+2),x^4-9x^3))");
		double expected =35276.59072;
		double actual=f.f(1.2);
		if(Math.abs(expected-actual)<epsilon)
			flag=true;
		assertTrue(flag);
	}

	@Test
	void testInitFromString() 
	{
		ComplexFunction cf=new ComplexFunction(new Monom("0"));
		//init a function from string with operation: mul, div ,plus ...
		String methodString="mul(6x^7+2x-1,plus(x^2+4x-4,x^9))"; 
		f=cf.initFromString(methodString);
		String expected="mul(6.0x^7+2.0x-1.0,plus(x^2+4.0x-4.0,x^9))";
		String actual=f.toString();
		assertEquals(expected, actual);
		//init a function from string with operation: Times, Divid ,Plus ...
		String OperationString ="Times(6x^7+2x-1,Plus(x^2+4x-4,x^9))";
		f=cf.initFromString(OperationString);
		expected="mul(6.0x^7+2.0x-1.0,plus(x^2+4.0x-4.0,x^9))";
		actual=f.toString();
		assertEquals(expected, actual);
	}

	@Test
	void testCopy() 
	{
		p=new Polynom("x^6-9x^3+2");
		p1=new Polynom("12x^4-3.3x^3");
		m=new Monom("2.1x^4");
		cf=new ComplexFunction("mul",p,p1);
		cf=new ComplexFunction("Divid",cf,m);
		f=cf.copy();
		String actual=f.toString();
		String expected=cf.toString();
		assertEquals(expected, actual);
		assertTrue(cf.equals(f));
	}

	@Test
	void testEqualsObject() 
	{
		p1=new Polynom("x+2");
		p=new Polynom("x+2");
		cf=new ComplexFunction("mul",p,p1);
		f=new Polynom("x^2+4x+4");
		assertTrue(cf.equals(f));
		//---------
		p1=new Polynom("3x");
		m=new Monom("4x");
		cf=new ComplexFunction("plus",m,p1);
		f=new Polynom("7x");
		assertTrue(cf.equals(f));
		//----------
		p1=new Polynom("x+2");
		p=new Polynom("x+2");
		cf=new ComplexFunction("plus",p,p1);
		f=new Polynom("2x+5");
		assertFalse(cf.equals(f));
	}

}
