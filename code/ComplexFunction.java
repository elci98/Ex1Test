package Ex1Testing;

@SuppressWarnings("serial")
public class ComplexFunction implements complex_function 
{
	/**
	 * 
	 */
	
	private CFNode _root;

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
		ComplexFunction cfTemp=this;
		CFNode temp = new CFNode(op,cfTemp,f1);
		_root=temp;
	}

	@Override
	public void mul(function f1) 
	{
		Operation op = Operation.Times;
		CFNode temp = new CFNode(op,this,f1);		
		_root=temp;
	}

	@Override
	public void div(function f1) 
	{
		Operation op = Operation.Divid;
		CFNode temp = new CFNode(op,this,f1);		
		_root=temp;
	}

	@Override
	public void max(function f1) 
	{
		Operation op = Operation.Max;
		CFNode temp = new CFNode(op,this,f1);		
		_root=temp;
	}

	@Override
	public void min(function f1) 
	{
		Operation op = Operation.Min;
		CFNode temp = new CFNode(op,this,f1);		
		_root=temp;
	}

	@Override
	public void comp(function f1) 
	{
		Operation op = Operation.Comp;
		CFNode temp = new CFNode(op,this,f1);		
		_root=temp;
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

		return null;
	}
	@Override
	public String toString()
	{
		return _root.toString();
	}
	@Override
	public function copy() 
	{
		return null;
	}
}
