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
			Branch first = new Branch(queue.pop(), queue.pop());
			queue.add(//here i need the priority of two of the elements from the queue but how do I take it?, first);
		}
	}
		
	public static void main(String[] args) throws IOException 
	{
		new Compressor();
	}
}
