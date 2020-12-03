
public class Branch<T> 
{
	boolean isLeaf;
	Branch<T> left;
	Branch<T> right;
	T info;
	
	public Branch(Branch<T> child1, Branch<T> child2) 
	{	
		left = child1;
		right = child2;
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
