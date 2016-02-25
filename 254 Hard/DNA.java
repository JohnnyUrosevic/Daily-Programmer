import java.util.*;
import java.io.*;

public class DNA {
	public static ArrayList<String> strands = new ArrayList<>();

	public static void main(String[] args) {
		readInput("input.txt");

		Collections.sort(strands, (a, b) -> b.length() - a.length());

		String dna = "";
		for (int i = 0; i < strands.size(); i++) {
			if (dna.indexOf(strands.get(i)) >= 0) {
				strands.delete(i);
				i--;
			}
			else {
				String s = strands.get(i);

				String frontOverlap;
				for (int j = 0; j < dna.length() - s.length(); j++) {

				}
				dna += strands.get(i);
			}
		}
	}

	public static void readInput(String path) {
		try {
			Scanner s = new Scanner(new File(path));

			while (s.hasNextLine()) {
				strands.add(s.nextLine());
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
