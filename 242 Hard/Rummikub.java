import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Rummikub {
	private static ArrayList<Tile> tiles = new ArrayList<>();
	private static ArrayList<ArrayList<Tile>> runs = new ArrayList<>();

	public static void main(String[] args) {
		readInput("input.text");

		tiles.sort((a,b) -> a.num - b.num);

		ArrayList<Tile> black  = new ArrayList<>(tiles);
		ArrayList<Tile> yellow = new ArrayList<>(tiles);
		ArrayList<Tile> red    = new ArrayList<>(tiles);
		ArrayList<Tile> purple = new ArrayList<>(tiles);

		black.removeIf(a -> a.char != 'b');
		yellow.removeIf(a -> a.char != 'y');
		red.removeIf(a -> a.char != 'r');
		purple.removeIf(a -> a.char != 'p');

		
	}

	public static void createRuns(ArrayList<Tile> tiles) {
		ArrayList<Tile> run = new ArrayList<>();
		for (int i = 0; i < tiles.size(); i++) {
			//TODO add runs to arraylist and push to main list
		}
	}

	public static void readInput(String file) {
		try {
			 Scanner s = new Scanner(new File(file));

			 char color;
			 int number;

			 while(s.hasNext()) {
				 String token = s.next();

				 color = token.charAt(0);
				 number = Integer.parseInt(token.substring(1));

				 tiles.add(new Tile(color, number));
			 }
		 }
	 	catch(FileNotFoundException e) {
		 	e.printStackTrace();
	 	}
	}
}

class Tile {
	public char color;
	public int num;

	public Tile(char c, int n) {
		color = c;
		num = n;
	}
}
