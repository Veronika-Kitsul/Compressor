import java.io.InputStreamReader;
import java.util.HashMap;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;  

public class Compressor 
{
	// every character
	char character;
	int i = 0;
	int value;
	
	public Compressor() throws IOException
	{
		// creating frequency map
		HashMap<Character, Integer> frequencyMap = new HashMap<Character, Integer>();
		
		// file we read from
		File file = new File("helloWorld.txt");
		
		FileReader inputReader = new FileReader(file);
		
			/*for (char character = (char) inputReader.read(); inputReader.read() != -1; character = (char) inputReader.read())
			{
				
				character = (char) inputReader.read();

			}*/
		
		
		
		
		while (inputReader.read() != -1)
		{
			character = (char) inputReader.read();
			//frequencyMap.put(character, value);
			System.out.println(character);
		}
		System.out.println(frequencyMap);
		
		/*for (int i = 0; i < 12; i++)
		{
			
		}*/
	}
	
	
		
		public static void main(String[] args) throws IOException 
		{
			new Compressor();
		}

}
