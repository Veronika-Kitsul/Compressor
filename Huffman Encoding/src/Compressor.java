import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;  

public class Compressor 
{
	int value = 1;
	char character;
	HashMap binaryCodes = new HashMap();
	
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
		Node<Branch<Character>> node = queue.pop();
		Branch<Character> tree = node.info;
		recursion(tree, "");
		System.out.println(binaryCodes);
		
		
		BufferedBitWriter writer = new BufferedBitWriter("compressed");
		while ((i = inputReader.read())!= -1)
		{
			// read character, take binary code for this character
			character = (char) i;
			String code = (String) binaryCodes.get(character);
			System.out.println(code);
			
			for (int j = 0; j < code.length(); j++)
			{
				char LastDigit = code.charAt(code.length() - 1);
				
				boolean bit = false;
				if (LastDigit == '0')
				{
					bit = false;
				}
				else if (LastDigit == '1')
				{
					bit = true;
				}
				writer.writeBit(bit);
				code = code.substring(0, code.length());
			}
		}
	}
	
	
	
	public void recursion(Branch<Character> tree, String value)
	{
		// key - characters
		// value - binary codes 

		// base case - leaf
		if (tree.isLeaf == true)
		{
			binaryCodes.put(tree.info, value);
		}
		else 
		{
			recursion(tree.left, value + "0");
			recursion(tree.right, value + "1");
		}
	}
	
	public static void main(String[] args) throws IOException 
	{
		new Compressor();
	}
}
