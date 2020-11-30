
public class Node<E> {

	public int priority;
	public E info;
	
	public Node(int p, E i) 
	{
		this.priority = p;
		this.info = i;
	}
		
	public String toString()
	{
		return (priority + " - " + info);
	}
}
	


