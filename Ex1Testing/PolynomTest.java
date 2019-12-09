package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;

class PolynomTest 
{
	
	Polynom p;
	Polynom_able p1;
	@Test
	void testPolynom() 
	{
		p=new Polynom();
		boolean actual=p.isZero();
		assertTrue(actual);
	}

	@Test
	void testPolynomString() 
	{
		p= new Polynom("3x^5+0+1.3-4.6x+12x^7");
		String expected="12.0x^7+3.0x^5-4.6x+1.3";
		String actual=p.toString();
		assertEquals(expected,actual);
	}

	@Test
	void testCopy() 
	{
		p= new Polynom("3x^5+0+1.3-4.6x+12x^7");
		p1=p.copy();
		String expected=p.toString();
		String actual=p1.toString();
		assertEquals(expected,actual);
	}

	@Test
	void testF() 
	{
		p=new Polynom("6x-12x^5+19x^10+0x+9.2x^1");
		double actual=p.f(5);
		double expected=185509451;
		assertEquals(expected,actual);
	}

	@Test
	void testAddPolynom_able() 
	{
		p=new Polynom("x^2+4x-4");
		p1=new Polynom("3x^5+x^2+6x+0");
		p.add(p1);
		String expected="3.0x^5+2.0x^2+10.0x-4.0";
		String actual=p.toString();
		assertEquals(expected,actual);
	}

	@Test
	void testAddMonom() 
	{
		p=new Polynom("x^2+4x-4");
		Monom m=new Monom("3x^5");
		p.add(m);
		String expected="3.0x^5+x^2+4.0x-4.0";
		String actual=p.toString();
		assertEquals(expected,actual);
	}

	@Test
	void testSubstract() 
	{
		p=new Polynom("x^2+4x-4");
		p1= new Polynom("x^2+4x-4");
		p.substract(p1);
		String expected="0";
		String actual=p.toString();
		assertEquals(expected,actual);
	}

	@Test
	void testMultiplyPolynom_able() 
	{
		p=new Polynom("x^2+4x-4");
		p1= new Polynom("x^2+4x-4");
		p.multiply(p1);
		String expected="x^4+8.0x^3+8.0x^2-32.0x+16.0";
		String actual=p.toString();
		assertEquals(expected,actual);
	}

	@Test
	void testMultiplyMonom() 
	{
		p=new Polynom("x^2+4x-4");
		Monom m= new Monom("2x^2");
		p.multiply(m);
		String expected="2.0x^4+8.0x^3-8.0x^2";
		String actual=p.toString();
		assertEquals(expected,actual);
	}

	@Test
	void testEqualsPolynom_able() 
	{
		p=new Polynom("x^2+4x-4");
		p1= new Polynom("x^2+4x-4");
		assertTrue(p.equals(p1));
	}

	@Test
	void testRoot() 
	{
		boolean actual=false;
		p=new Polynom("x^3+2x^5-4");
		double root=p.root(-2, 2, 0.001);
		if(1.068-root<=0.001)//1.068 is actual root of p
			actual =true;
		assertTrue(actual);
	}

	@Test
	void testDerivative() 
	{
		p=new Polynom("2x^5+4+x^2+3x^1");
		String expected="10.0x^4+2.0x+3.0";
		String actual=p.derivative().toString();
		assertEquals(expected, actual);
	}

	@Test
	void testArea() 
	{
		boolean actual=false;
		p=new Polynom("-9x^2+x^4+14");
		double area = p.area(-1.414, 1.414, 0.001);
		if(24.890158052739933-area<0.001)//58.815... is actual area
			actual=true;
		assertTrue(actual);
	}


	@Test
	void testToString() 
	{
		p=new Polynom("0x^5+12x^1+2x^4-x^2+9-9+9-45x^78");
		String expected = "-45.0x^78+2.0x^4-x^2+12.0x+9.0";
		String actual=p.toString();
		assertEquals(expected, actual);
	}

}
