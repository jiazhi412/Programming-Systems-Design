import java.util.ArrayList;

public class ADTester {
	public static void main(String[] args) {
		String fileName = "G:\\455\\Homework4\\PA4\\bin\\testFiles\\sowpods.txt";
		try {
			AnagramDictionary test = new AnagramDictionary(fileName);
			String s = "reeals";
			print(test.getAnagramsOf(s));
		} catch (Exception e) {
			System.out.println("File is not found!");
		}		
	}
	
	private static void print(ArrayList<String> arrayStrings) {
		for(int i = 0; i < arrayStrings.size();i++) {
			System.out.println(arrayStrings.get(i));
		}
	}
}