package Ex1Testing;

@SuppressWarnings("serial")
public class ComplexFunction implements complex_function 
{
	/**
	 * 
	 */

	private CFNode _root;

	private ComplexFunction(CFNode root)
	{
		_root=root;
	}
	public ComplexFunction(function left)
	{
		_root=new CFNode(left);
	}
	public ComplexFunction(Operation Operator,function left,function right)
	{
		_root=new CFNode(Operator,left,right);
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
	public void comp(function f1) 
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
			return _root.get_op();
		return null;
	}
	@Override
	public double f(double x) 
	{

		return 0;
	}

	@Override
	public function initFromString(String s) 
	{
		String Operator="",rightFunc="";
		CFNode root=new CFNode();
		if(!s.contains(",") && ( s.contains("+") || s.contains("-") ))
			return new Polynom(s);
		else if(!s.contains(",")) 
			return new Monom(s);
		int j=s.length()-2,k=0;
		while(s.charAt(k)!='(')
			Operator+=s.charAt(k++);
		while(s.charAt(j)!=',')
			rightFunc+=s.charAt(j--);
		root._right=initFromString(reverseString(rightFunc));
		root._left=initFromString(s.substring(++k, j));
		root._op= Operator=="" ?Operation.None:Operation.valueOf(Operator);
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
	//---------Auxiliary-reverse-String-Function--------------------
	private String reverseString(String s)
	{
		StringBuilder input1 = new StringBuilder(); 
		input1.append(s); 
		return input1.reverse().toString();
	}

	//---------------Inner-Class-CpmplexFunction-Node------------
	private class CFNode 
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
		//---------------------Setters-& Getters--------------------- 
		function get_left() 
		{
			return _left;
		}
		void set_left(function _left) 
		{
			this._left = _left;
		}
		public function get_right()
		{
			return _right;
		}
		public void set_right(function _right) 
		{
			this._right = _right;
		}
		public Operation get_op() 
		{
			return _op;
		}
		public void set_op(Operation _op) 
		{
			this._op = _op;
		}
		@Override
		public String toString() 
		{
			if(_right!=null)
				return  _op+"("+_left + ","+ _right +")";
			return _left+"";
		}
	}

}
