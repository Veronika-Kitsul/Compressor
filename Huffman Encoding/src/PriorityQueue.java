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

	public void add(Node<E> newNode)
	{
		if (queue.size() == 0)
		{
			queue.add(newNode);
		}
		
		else if (queue.get(0).priority < newNode.priority)
		{
			queue.add(0, newNode);
		}
		else if (queue.get(queue.size()-1).priority > newNode.priority)
		{
			queue.add(newNode);
		}
		
		//binary search for correct location
		else 
		{
			int start = 0, end = queue.size()-1;
			while(start < end)
			{
				Node<E> midPoint = queue.get((start+end)/2);
				
				if (midPoint.priority > newNode.priority)
				{
					start = (start+end)/2 + 1;
				}
				else 
				{
					end = (start+end)/2;
				}
			}
			queue.add(start, newNode);
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
		myQ.add(new Node(15,'k'));
		myQ.add(new Node(1,'d'));
		myQ.add(new Node(3,'k'));
		myQ.add(new Node(1,'d'));
		myQ.add(new Node(3,'k'));
		myQ.add(new Node(1,'d'));
		myQ.add(new Node(2,'k'));
		myQ.add(new Node(8,'d'));
		myQ.add(new Node(9,'t'));
		myQ.add(new Node(0,'d'));
		myQ.add(new Node(3,'k'));
		myQ.add(new Node(98,'d'));
		myQ.add(new Node(6,'k'));
		myQ.add(new Node(5,'d'));
		myQ.add(new Node(3,'k'));
		System.out.println(myQ);
		myQ.pop();
		System.out.println(myQ);
		myQ.pop();
		System.out.println(myQ);
	}
	
}
