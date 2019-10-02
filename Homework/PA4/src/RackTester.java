import java.util.ArrayList;

public class RackTester {
	public static void main(String[] args) {
		String s = "reel";
		Rack r = new Rack(s);
		print(r.getAllSubsets());
	}

	private static void print(ArrayList<String> arrayStrings) {
		for(int i = 0; i < arrayStrings.size();i++) {
			System.out.println(arrayStrings.get(i));
		}
	}
}
