package Ex1Testing;

@SuppressWarnings("serial")
public class ComplexFunction implements complex_function 
{

	private CFNode _root;

	private ComplexFunction(CFNode root)
	{
		_root=root;
	}
	public ComplexFunction(function left)
	{
		_root=new CFNode(left);
	}
	public ComplexFunction(String Operator,function left,function right)
	{
		Operator=ConvertOp(Operator);
		Operation operator=Operation.valueOf(Operator);
		_root=new CFNode(operator,left,right);
	}
	@Override
	public void plus(function f1) 
	{
		Operation op = Operation.Plus;
		ComplexFunction tempCF = _root.get_right()!=null ? new ComplexFunction(_root.get_op(),_root.get_left(),_root.get_right()) : new ComplexFunction(_root.get_left());
		CFNode tempNode=new CFNode(op,tempCF,f1);
		_root=tempNode;
	}

	@Override
	public void mul(function f1) 
	{
		Operation op=Operation.Times;
		ComplexFunction tempCF = _root.get_right()!=null ? new ComplexFunction(_root.get_op(),_root.get_left(),_root.get_right()) : new ComplexFunction(_root.get_left());
		CFNode tempNode=new CFNode(op,tempCF,f1);
		_root=tempNode;
	}

	@Override
	public void div(function f1) 
	{
		Operation op = Operation.Divid;
		ComplexFunction tempCF = _root.get_right()!=null ? new ComplexFunction(_root.get_op(),_root.get_left(),_root.get_right()) : new ComplexFunction(_root.get_left());
		CFNode tempNode=new CFNode(op,tempCF,f1);
		_root=tempNode;
	}

	@Override
	public void max(function f1) 
	{
		Operation op = Operation.Max;
		ComplexFunction tempCF = _root.get_right()!=null ? new ComplexFunction(_root.get_op(),_root.get_left(),_root.get_right()) : new ComplexFunction(_root.get_left());
		CFNode tempNode=new CFNode(op,tempCF,f1);
		_root=tempNode;
	}

	@Override
	public void min(function f1) 
	{
		Operation op = Operation.Min;
		ComplexFunction tempCF = _root.get_right()!=null ? new ComplexFunction(_root.get_op(),_root.get_left(),_root.get_right()) : new ComplexFunction(_root.get_left());
		CFNode tempNode=new CFNode(op,tempCF,f1);
		_root=tempNode;
	}

	@Override
	public void comp(function f1) //composition
	{
		Operation op = Operation.Comp;
		ComplexFunction tempCF = _root.get_right()!=null ? new ComplexFunction(_root.get_op(),_root.get_left(),_root.get_right()) : new ComplexFunction(_root.get_left());
		CFNode tempNode=new CFNode(op,tempCF,f1);
		_root=tempNode;
	}

	@Override
	public function left() 
	{
		return _root.get_left();
	}

	@Override
	public function right() 
	{
		if(_root.get_right()!=null)
			return _root.get_right();
		System.out.println("right function is null!!");
		return null;
	}

	@Override
	public Operation getOp() 
	{
		if(_root.get_op()!=null)
			return Operation.valueOf(_root.get_op());
		return null;
	}
	@Override
	public double f(double x) 
	{
		if(_root._right==null)
			return _root._left.f(x);
		switch(_root._op)
		{
		case Comp:
		{
			return _root._left.f(_root._right.f(x));
		}
		case Times:
		{
			return _root._left.f(x)*_root._right.f(x);
		}
		case Divid:
		{
			return _root._left.f(x)/_root._right.f(x);
		}
		case Plus:
		{
			return _root._left.f(x)+_root._right.f(x);
		}
		case Max:
		{
			return Math.max(_root._left.f(x),_root._right.f(x));
		}
		case Min:
		{	
			return Math.min(_root._left.f(x),_root._right.f(x));
		}
		default:
		{
			break;
		}
		}

		return 0;
	}

	@Override
	public function initFromString(String s) 
	{
		//recursion stop conditions
		if(!s.contains(",") && ( s.contains("+") || s.contains("-") ))
			return new Polynom(s);
		else if(!s.contains(",")) 
			return new Monom(s);

		String Operator="",rightFunc="",leftFunc="",string="";
		CFNode root=new CFNode();
		int sLength=s.length()-1,k=0;
		while(s.charAt(k)!='(') // chain the operator into operator
		{
			Operator+=s.charAt(k++);
		}
		int leftCursor=k++;
		while(s.charAt(k)!='(' && s.charAt(k) !=',')//chain the operator or left function to string
		{
			string+=s.charAt(k++);
		}
		switch(s.charAt(k))
		{
		case '(':
		{
			int counter=0;
			boolean flag=true;
			while(flag)
			{
				if(s.charAt(k)=='(')
					counter++;
				if(s.charAt(k)==')')
					counter--;
				string+=s.charAt(k++);
				if(counter==0)
					flag=false;
			}

			leftFunc=string;
			rightFunc=s.substring(++k,sLength);
			Operator=ConvertOp(Operator);
			root._op=Operation.valueOf(Operator);
			break;
		}
		case ',':
		{
			leftFunc=string;
			rightFunc=s.substring(++k,sLength);
			Operator=ConvertOp(Operator);
			root._op=Operation.valueOf(Operator);
			break;
		}

		}
		root._left=initFromString(leftFunc);
		root._right=initFromString(rightFunc);
		return new ComplexFunction(root);
	}

	@Override
	public String toString()
	{
		return _root.toString();
	}
	@Override
	public function copy() 
	{
		function f= initFromString(_root.toString());
		return f;
	}
	private String ConvertOp(String Operator) 
	{
		switch(Operator)
		{
		case "comp":
		{
			return "Comp";
		}
		case "mul":
		{
			return "Times";
		}
		case "div":
		{
			return "Divid";
		}
		case "plus":
		{
			return "Plus";
		}
		case "max":
		{
			return "Max";
		}
		case "min":
		{	
			return "Min";
		}
		default:
			return "None";
	}
	}
	//---------------Inner-Class-CpmplexFunction-Node------------
	private static class CFNode 
	{
		private function _left=null,_right=null;
		private Operation _op=Operation.None;

		CFNode()
		{

		}
		CFNode(function left)
		{
			_left=left;
		}	
		CFNode(Operation op,function left,function right)
		{
			_left=left;
			_right=right;
			_op=op;
		}
		//---------------------Getters--------------------- 
		function get_left() 
		{
			return _left;
		}
		function get_right()
		{
			return _right;
		}
		String get_op() 
		{
			return _op.toString();
		}
		public String toString() 
		{
			if(_right!=null && _op!= Operation.None) // i.e ComplexFunction
				return  _op+"("+_left + ","+ _right +")";
			return _left+"";
		}
	}

}
