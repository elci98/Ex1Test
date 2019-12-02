package Ex1Testing;

public class CFNode 
{
	private function _left=null,_right=null;
	private Operation _op=null;
	
	public CFNode(function left)
	{
		_left=left;
	}	
	public CFNode(Operation op,function left,function right)
	{
		_left=left;
		_right=right;
		_op=op;
	}
//---------------------Setters-& Getters--------------------- 
	public function get_left() 
	{
		return _left;
	}
	public void set_left(function _left) 
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
	public String toString() {
		return  _op+"("+_left + " ,"+ _right +")";
	}
	

}
