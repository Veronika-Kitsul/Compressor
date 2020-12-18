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
		
		
		// start building binary tree of codes and their frequencies
		// unless there is one element left in the queue 
		// this one element is our tree
		while (queue.size() > 1)
		{
			// sum up priorities of two last nodes in the queue
			Node<Branch<Character>> node1 = queue.pop();
			int k = node1.priority;
			Node<Branch<Character>> node2 = queue.pop();
			k = k + node2.priority;
			// create a new branch out of this two elements and add back to the queue
			Branch<Character> first = new Branch<Character>(node1.info, node2.info);
			queue.add(k, first);
		}
		
		// take the tree out of the priority queue
		Node<Branch<Character>> node = queue.pop();
		Branch<Character> tree = node.info;
		
		// start recursion to decompose the tree
		recursion(tree, "");
		// close file writer for writing binary codes into a separate file
		output.close();
		
		// new file reader 
		FileReader reader = new FileReader(file);
		
		// new file writer - Mr. David's code for it
		BufferedBitWriter writer = new BufferedBitWriter("compressed");
		while ((i = reader.read())!= -1)
		{
			// read character, take binary code for this character
			character = (char) i;
			String code = (String) binaryCodes.get(character);
			
			// go through this code's each character
			for (int j = 0; j < code.length(); j++)
			{
				boolean bit = false;
				// take each char of the code
				char firstDigit = code.charAt(j);
				
				// convert char's integer number into boolean
				if (firstDigit == '0' && code.length() > 1)
				{
					bit = false;
				}
				else if (firstDigit == '1')
				{
					bit = true;
				}
				
				// pass booleans to Mr. David's bit writer so it writes every bit
				writer.writeBit(bit);
			}
		}
		// close bit writer and file reader
		writer.close();
		reader.close();
	}
	
	// create new file writer to have separate file of binary codes
	BufferedWriter output = new BufferedWriter(new FileWriter("Codes"));
	
	// recursion to decompose the tree
	public void recursion(Branch<Character> tree, String value) throws IOException
	{
		// key - characters
		// value - binary codes 

		// base case - leaf
		if (tree.isLeaf == true)
		{
			// if leaf - pass each node's value into the file that will contain codes for each character
			decompression(tree.info, value);
			binaryCodes.put(tree.info, value);
		}
		else 
		{
			recursion(tree.left, value + "0");
			recursion(tree.right, value + "1");
		}
	}
	
	
	// write codes for each character into a separate file
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
