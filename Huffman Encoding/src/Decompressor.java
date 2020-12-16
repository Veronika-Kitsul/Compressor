import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Decompressor {

	public Decompressor() throws IOException {
		// codes = keys, characters = values
		//use bit reader by Mr. David to read the file bit by bit
		//check if this code is in the map
		//if it is = write to the file
		
		// creating frequency map
		char character;
		int value = 1;
		HashMap<Character, Integer> codeMap = new HashMap<Character, Integer>();
		
		// file we read from
		File file = new File("Codes.txt");
		
		// file reader
		FileReader inputReader = new FileReader(file);
		
		// reading through the file
		int i;
		while ((i = inputReader.read())!= -1)
		{
			character = (char) i;
			
			// so i need to have a condition to check if this is a character or this is a code
			//if ()
			//codeMap.put();
		}
		System.out.println(codeMap);
	}

}
