import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;  

public class Compressor 
{
	int value = 1;
	char character;
	HashMap<Character, String> binaryCodes = new HashMap();
	
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
		output.close();
		System.out.println(binaryCodes);
		
		
		FileReader reader = new FileReader(file);
		BufferedBitWriter writer = new BufferedBitWriter("compressed");
		while ((i = reader.read())!= -1)
		{
			// read character, take binary code for this character
			character = (char) i;
			String code = (String) binaryCodes.get(character);
			
			
			for (int j = 0; j < code.length(); j++)
			{
				boolean bit = false;
				char firstDigit = code.charAt(j);
				System.out.println("digit: "+ firstDigit + "_code: " + code);
				
				
				if (firstDigit == '0' && code.length() > 1)
				{
					bit = false;
				}
				else if (firstDigit == '1')
				{
					bit = true;
				}
				
				System.out.println(bit);
				writer.writeBit(bit);
			}
		}
		writer.close();
		reader.close();
	}
	
	BufferedWriter output = new BufferedWriter(new FileWriter("Codes"));
	
	public void recursion(Branch<Character> tree, String value) throws IOException
	{
		// key - characters
		// value - binary codes 

		// base case - leaf
		if (tree.isLeaf == true)
		{
			decompression(tree.info, value);
			binaryCodes.put(tree.info, value);
		}
		else 
		{
			recursion(tree.left, value + "0");
			recursion(tree.right, value + "1");
		}
	}
	
	
	// working
	public void decompression(Character info, String value) throws IOException
	{
		 output.write(value + "\n");
		 output.write(info + "\n");
	}
	
	public static void main(String[] args) throws IOException 
	{
		new Compressor();
	}
}
