import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Decompressor {

	public Decompressor() throws IOException {
		// codes = keys, characters = values
		//use bit reader by Mr. David to read the file bit by bit
		//check if this code is in the map
		//if it is = write to the file
		
		String keyCode = null;
		String valueChar = "";
		HashMap<String, String> codeMap = new HashMap<String, String>();
		
		// file we read from
		File file = new File("Codes");
		
		BufferedReader in = new BufferedReader(new FileReader(file));
		
		int number = 0;
		for (String line = in.readLine(); line != null; line = in.readLine())
		{
			// even numbered lines = codes, odd numbered = characters
			if (number % 2 == 0)
			{
				keyCode = line;
			}
			else 
			{
				// put the value and it's code into the map
				valueChar = line;
				codeMap.put(keyCode, valueChar);
			}
			number++;
		}
		in.close();
		
		
		// read through compressed file 
		BufferedBitReader reader = new BufferedBitReader("compressed");
		// write  new file
		BufferedWriter output = new BufferedWriter(new FileWriter("Decompressed"));
		String code = "";
		
		// go through the compressed text 
		while (reader.hasNext() == true)
		{
			// code boolean 
			Boolean codeBool = reader.readBit();

			// make a binary code
			if (codeBool == true)
			{
				code = code + "1";
			}
			else if (codeBool == false)
			{
				code = code + "0";
			}
			
			// if code exists = write it's corresponding char into the file
			if (codeMap.get(code) != null)
			{
				output.write(codeMap.get(code));
				// update the code
				code = "";
			}
		}
		reader.close();
		output.close();
		
	}

	public static void main(String[] args) throws IOException 
	{
		new Decompressor();
	}
}