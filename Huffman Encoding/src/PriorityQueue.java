import java.util.ArrayList;

public class PriorityQueue<E> {

	private ArrayList<Node<E>> queue = new ArrayList<Node<E>>();
	
	public PriorityQueue() 
	{
		
	}
	
	public String toString()
	{
		return queue.toString();
	}

	public void add(int p, E i)
	{
		int priority = p;
		E info = i;
		
		Node element = new Node<E>(priority, info); 
		
		if (queue.size() == 0)
		{
			queue.add(element);
		}
		
		else if (queue.get(0).priority < element.priority)
		{
			queue.add(0, element);
		}
		else if (queue.get(queue.size()-1).priority > element.priority)
		{
			queue.add(element);
		}
		
		//binary search for correct location
		else 
		{
			int start = 0, end = queue.size()-1;
			while(start < end)
			{
				Node<E> midPoint = queue.get((start+end)/2);
				
				if (midPoint.priority > element.priority)
				{
					start = (start+end)/2 + 1;
				}
				else 
				{
					end = (start+end)/2;
				}
			}
			queue.add(start, element);
		}
	}
	
	public Node<E> pop()
	{
		return queue.remove(queue.size()-1);
	}
	
	public int size()
	{
		return queue.size();
	}
	
	public static void main(String[] args)
	{
		PriorityQueue<Character> myQ = new PriorityQueue<Character>();
	}
	
}
