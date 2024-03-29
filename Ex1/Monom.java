
package Ex1;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 *
 */
public class Monom implements function
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp()
	{
		return _Comp;
	}
	public Monom(double a, int b)
	{
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) 
	{
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient()
	{
		return this._coefficient;
	}
	public int get_power()
	{
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative()
	{
		if(this.get_power()==0)
		{
			return getNewZeroMonom();
		}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) 
	{
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() 
	{
		return this.get_coefficient() == 0;
	}
	
	public Monom(String s) 
	{
		if(s.contains(" "))
			s=s.replace(" ", "");
		if(s==null)throw new RuntimeException("string is null!");
		if(s=="")throw new RuntimeException("string is empty!");
		try 
		{
			boolean x=false;
			boolean flag = isPositive(s);
			int i= (flag && s.charAt(0)!='+') ? 0 : 1;
			int pow=0;
			double coef=0;
			String t="";
			if(s.charAt(i)=='0'&& s.length()==1 )
			{
				getNewZeroMonom();
				return;
			}
			while(i<s.length()&& s.charAt(i)< 58 && s.charAt(i)> 47  ||i<s.length()&& s.charAt(i)=='.' )
			{
				t+=s.charAt(i++);
			}
			if(t!="")
			{
				Double d = Double.parseDouble(t);
				coef=d;
			}
			
			if(s.contains("x")&& !t.equals("0"))
			{
				coef = coef==0 ? 1 : coef;
				x=true;
			}
			if(coef==0)
			{
				getNewZeroMonom();
				return;
			}
			i++;
			if(s.contains("^"))
			{
				i++;
				t="";
				while(i<s.length())
				{
					t+=s.charAt(i++);
				}
				Integer d = Integer.parseInt(t);
				pow=d;
			}
			else 
				pow = x? 1:0;
			if (!flag) coef*= -1;
			if(Math.abs(coef-Math.round(coef))<0.0001)
				this.set_coefficient(Math.round(coef));
			else 
				this.set_coefficient(coef);
			this.set_power(pow);

		}
		catch (NumberFormatException e)
		{
			throw new NumberFormatException("Power should be positive");
		}
	}
	/**
	 * equals function compares current Monom f(x) values to m.f(x) values between closed interval [x0,x1]
	 *         with epsilon accuracy. 
	 *         when x belongs to [x0,x1] and we increase x`s value by step value
	 * */
	public boolean equals(Object m)
	{
		double x0=-10,x1=10,step=0.01,epsilon=0.0001;
		while(x0<=x1)
		{
			double y0=(double)Math.round(this.f(x0) * 10000000000000000d) / 10000000000000000d;
			double y1 =(double)Math.round(((function)m).f(x0) * 10000000000000000d) / 10000000000000000d;
			if(Math.abs(y0-y1)>epsilon)
				return false;
			x0+=step;
		}
		return true;
	}
	public void add(Monom m) 
	{
		if(m.get_power()!= this.get_power()) 
			throw new RuntimeException("Error! - powers don`t match");
		BigDecimal coef_1 = BigDecimal.valueOf(_coefficient);
		BigDecimal coef_2 = BigDecimal.valueOf(m.get_coefficient());
		this._coefficient=coef_1.add(coef_2).doubleValue();
		if(this._coefficient==0)
			_power=0;
	}

	public void multiply(Monom d) 
	{
		this._coefficient*=d._coefficient;
		this._power+=d._power;
	}

	public String toString() 
	{
		if(isZero())return "0";
		if(this._power == 0) return ""+_coefficient;
		else if(this._power== 1)
		{
			if(Math.abs(_coefficient)==1)
				return _coefficient==1? "x":"-x";
			else
				return _coefficient+"x";
		}
		else   // if power > 1
		{
			if(Math.abs(_coefficient)==1)
				return _coefficient==1? "x^"+this._power:"-x^"+this._power;
			else
				return _coefficient+"x^"+this._power;
		}
	}
	//****************** Private Methods and Data *****************

	private boolean isPositive(String s)
	{
		return s.charAt(0)!= '-';
	}
	private void set_coefficient(double a)
	{
		BigDecimal coef_2 = BigDecimal.valueOf(a);
		this._coefficient=coef_2.doubleValue();
	}
	private void set_power(int p)
	{
		if(p<0) 
			throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);
		this._power = p;
	}
	private static Monom getNewZeroMonom()
	{
		return new Monom(ZERO);
	}

	@Override
	public function initFromString(String s) 
	{
		function f = new Monom(s);
		return f;
	}
	@Override
	public function copy() 
	{
		return new Monom(this.toString());
	}
	private double _coefficient; 
	private int _power;
}
