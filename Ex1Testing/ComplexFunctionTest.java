package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.server.Operation;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Polynom;

class ComplexFunctionTest {
	ComplexFunction c;
	Monom m;
	Polynom p,p1;

	@Test
	void testComplexFunctionFunction() {
		
	}

	@Test
	void testComplexFunctionStringFunctionFunction() {
		m=new Monom("x^2");
		p=new Polynom("3x^3+2x^2");
		c=new ComplexFunction("Plus",m,p);
		String expected="3x^3+3x^2";
		String actual=c.toString();
		assertEquals(expected, actual);
	}

	@Test
	void testPlus() {
		m=new Monom("x^2");
		p=new Polynom("3x^3+2x^2");
		c=new ComplexFunction("Plus",m,p);
		String expected="3x^3+3x^2";
		String actual=c.toString();
		assertEquals(expected, actual);
		
	}

	@Test
	void testMul() {
		fail("Not yet implemented");
	}

	@Test
	void testDiv() {
		fail("Not yet implemented");
	}

	@Test
	void testMax() {
		fail("Not yet implemented");
	}

	@Test
	void testMin() {
		fail("Not yet implemented");
	}

	@Test
	void testComp() {
		fail("Not yet implemented");
	}

	@Test
	void testF() {
		fail("Not yet implemented");
	}

	@Test
	void testInitFromString() {
		String s="mul(x^5+2,4x+1)";
		p=new Polynom("x^5+2");
		p1=new Polynom("4x+1");
		c=new ComplexFunction("mul",p,p1);
		String expected="4x^6+x^5+8x+2";
		String actual=c.toString();
		assertEquals(expected, actual);
		
	}

	@Test
	void testCopy() {
		fail("Not yet implemented");
	}

	@Test
	void testEqualsFunctionDoubleDoubleDouble() {
		fail("Not yet implemented");
	}

}
