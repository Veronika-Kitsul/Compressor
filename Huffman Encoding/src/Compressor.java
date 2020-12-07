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
		
		
		// start building tree - issues in priority queue with having nodes previously
		for (int j = 0; j < queue.size(); j++)
		{
			Node node1 = queue.pop();
			int k = node1.priority;
			Node node2 = queue.pop();
			k = k + node2.priority;
			Branch first = new Branch(node1, node2);
			queue.add(k, first);
		}
	}
		
	public static void main(String[] args) throws IOException 
	{
		new Compressor();
	}
}
