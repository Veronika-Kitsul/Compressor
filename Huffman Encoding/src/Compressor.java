import java.io.InputStreamReader;
import java.util.HashMap;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;  

public class Compressor 
{
	int value = 1;
	char character;
	
	public Compressor() throws IOException
	{
		// creating frequency map
		HashMap<Character, Integer> frequencyMap = new HashMap<Character, Integer>();
		
		// file we read from
		File file = new File("helloWorld.txt");
		
		// file reader
		FileReader inputReader = new FileReader(file);
		
		// reading through the file
		int i;
		while ((i = inputReader.read())!= -1)
		{
			// if we already have this in the map - increase frequency 
			character = (char) i;
			if (frequencyMap.containsKey(character))
			{
				int fre = frequencyMap.get(character);
				frequencyMap.put(character, fre + 1);
			}
			// if the value is not in the map - add it
			else 
			{
				frequencyMap.put(character, value);
			}
		}
		
		
		// take it all into the priority Queue
		PriorityQueue<Branch<Character>> queue = new PriorityQueue<Branch<Character>>();
		for (Character key : frequencyMap.keySet())
		{
			queue.add(frequencyMap.get(key), new Branch<Character>(key));	
		}
		System.out.println(queue);
		
		
		// start building tree - issues with Nodes and Branches
		while (queue.size() > 1)
		{
			Node<Branch<Character>> node1 = queue.pop();
			int k = node1.priority;
			Node<Branch<Character>> node2 = queue.pop();
			k = k + node2.priority;
			Branch<Character> first = new Branch<Character>(node1.info, node2.info);
			queue.add(k, first);
		}
		recursion(queue);
	}
	
	
	
	public void recursion(PriorityQueue<Branch<Character>> queue)
	{
		HashMap binaryCodes = new HashMap();
		// key - characters
		// value - binary codes 
			String value;

		// base case - leaf
		if (isLeaf = true)
		{
			binaryCodes.put(key, value);
		}
		else 
		{
			value for left = value + "0";
			value for right = value + "1";
		}
		recursion();
	}
		
	public static void main(String[] args) throws IOException 
	{
		new Compressor();
	}
}
