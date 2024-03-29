package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.Monom;

class MonomTest 
{
	Monom m,m1;
	@Test
	void testToString() 
	{
		m=new Monom(1.9,3);
		String expected="1.9x^3";
		String actual=m.toString();
		assertEquals(expected, actual);
	}
	@Test
	void testMonomDoubleInt() 
	{
		m=new Monom(2,3);
		String expected="2.0x^3";
		String actual=m.toString();
		assertEquals(expected, actual);
	}

	@Test
	void testMonomMonom() 
	{
		m1=new Monom(3,4);
		m=new Monom(m1);
		String expected=m1.toString();
		String actual=m.toString();
		assertEquals(expected, actual);
	}

	@Test
	void testDerivative() 
	{
		m=new Monom(4,3);
		m1=m.derivative();
		String expected="12.0x^2";
		String actual=m1.toString();
		assertEquals(expected, actual);
	}

	@Test
	void testF() 
	{
		m=new Monom(12,3);
		double expected= 768;
		double actual= m.f(4);
		assertEquals(expected, actual);
	}

	@Test
	void testMonomString() 
	{ 
		Monom m=new Monom("-0x^5");
		Monom m1 = new Monom("-x^5");
		Monom m2 = new Monom("-x");
		Monom m3 = new Monom("x");
		Monom m4 = new Monom("-1x");
		Monom m5 = new Monom("4x^2");
		Monom m6 = new Monom("2x");
		//m
		String expected="0";
		String actual=m.toString();
		assertEquals(expected, actual);
		//m1
		expected="-x^5";
		actual=m1.toString();
		assertEquals(expected, actual);
		//m2
		expected="-x";
		actual=m2.toString();
		assertEquals(expected, actual);
		//m3
		expected="x";
		actual=m3.toString();
		assertEquals(expected, actual);
		//m4
		expected="-x";
		actual=m4.toString();
		assertEquals(expected, actual);
		//m5
		expected="4.0x^2";
		actual=m5.toString();
		assertEquals(expected, actual);
		//m6
		expected="2.0x";
		actual=m6.toString();
		assertEquals(expected, actual);
	}

	@Test
	void testEqualsMonom() 
	{
		m1 = new Monom(4,5);
		m = new Monom(6,7);
		boolean expected=false;
		boolean actual=m.equals(m1);
		assertEquals(expected, actual);
	}

	@Test
	void testAdd() 
	{
		m = new Monom(2.75,4);
		m1 = new Monom(34,4);
		m.add(m1);
		String expected="36.75x^4";
		String actual=m.toString();
		assertEquals(expected, actual);
	}

	@Test
	void testMultiply() 
	{
		m = new Monom(2.75,2);
		m1 = new Monom(12,4);
		m.multiply(m1);
		String expected="33.0x^6";
		String actual=m.toString();
		assertEquals(expected, actual);
	}
}
