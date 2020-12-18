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
			if (number % 2 == 0)
			{
				keyCode = line;
			}
			else 
			{
				valueChar = line;
				codeMap.put(keyCode, valueChar);
			}
			number++;
		}
		in.close();
		
		
		
		BufferedBitReader reader = new BufferedBitReader("compressed");
		BufferedWriter output = new BufferedWriter(new FileWriter("Decompressed"));
		String code = "";
		
		while (reader.hasNext() == true)
		{
			Boolean codeBool = reader.readBit();
			// so these booleans are incorrect because I get true-true-false-false-false, but i should get true-true-false-true-false
			
			if (codeBool == true)
			{
				code = code + "1";
			}
			else if (codeBool == false)
			{
				code = code + "0";
			}
			
			if (codeMap.get(code) != null)
			{
				output.write(codeMap.get(code));
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