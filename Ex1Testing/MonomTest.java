package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


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
		m = new Monom(3,4);
		assertEquals(3, m.get_coefficient());
		assertEquals(4, m.get_power());
	}

	@Test
	void testMonomMonom() 
	{
		m1=new Monom(3,4);
		m=new Monom(m1);
		assertEquals(m1.toString(), m.toString());

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
		double x = 1;
		m = new Monom("3.6,7");
		double temp = 3.6*Math.pow(x, 7);
		assertEquals(temp, m.f(x));
	}

	@Test
	void testMonomString() 
	{
		m=new Monom("12.3x^4");
		String expected="12.3x^4";
		String actual=m.toString();
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
	void testMonomBadString()
	{ // need to fix "x^^3" -> init it.
		String[] bad = {"2x2","^2","1*x500","-3^0","*x^50","x^-1","0x^-1","^^","3^x","-3^x^-2","-51x2",
				"-x*-3","-2^x","*x","x^-1","3^x","2xx","2^^","^2","x^1.23","x^31.2","-1*x^12.2","2x2","2xx","-2x^1.5","-2*x^1.5","x^1.5","2^2","2^x","22xx"};	
	//	for(String bad_string : bad)
			for(int i=0 ; i<bad.length;i++)
		{
			try
			{
				m = new Monom(bad[i]);
				fail("you could init from ("+bad[i]+"), the monom is: "+m.toString());
			}
			catch(Exception e)
			{
				//Success 
			}
		}
	}
}
