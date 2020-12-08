
public class Branch<T> 
{
	boolean isLeaf;
	Branch<T> left;
	Branch<T> right;
	T info;
	
	public Branch(Branch<T> node1, Branch<T> node2) 
	{	
		left = node1;
		right = node2;
		isLeaf = false;
	}
	
	public Branch(T info)
	{
		this.info = info;
		isLeaf = true;
	}
	
	public String toString()
	{
		return info.toString();
	}
	
	
}
