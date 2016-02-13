import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Rummikub {
	private static ArrayList<Tile> tiles = new ArrayList<>();

	private static ArrayList<ArrayList<Tile>> runs   = new ArrayList<>();
	private static ArrayList<ArrayList<Tile>> groups = new ArrayList<>();

	public static void main(String[] args) {
		readInput("input.text");

		tiles.sort((a,b) -> a.num - b.num);

		//create arrays for each color
		ArrayList<Tile> black  = new ArrayList<>(tiles);
		ArrayList<Tile> yellow = new ArrayList<>(tiles);
		ArrayList<Tile> red    = new ArrayList<>(tiles);
		ArrayList<Tile> purple = new ArrayList<>(tiles);

		black.removeIf(a -> a.color != 'B');
		yellow.removeIf(a -> a.color != 'Y');
		red.removeIf(a -> a.color != 'R');
		purple.removeIf(a -> a.color != 'P');

		//runs
		createRuns(black);
		createRuns(yellow);
		createRuns(red);
		createRuns(purple);

		runs.removeIf(a -> a.size() < 3);

		//groups
		createGroups();

		groups.removeIf(a -> a.size() < 3);

		for (ArrayList<Tile> tileList : runs) {
			for (Tile t : tileList) {
				System.out.print(t.color + "" + t.num + " ");
			}
			System.out.println();
		}

		for (ArrayList<Tile> tileList : groups) {
			for (Tile t : tileList) {
				System.out.print(t.color + "" + t.num + " ");
			}
			System.out.println();
		}
	}

	public static void createRuns(ArrayList<Tile> tiles) {
		if (tiles.size() < 3) return; //not possible to make a run with less than 3 tiles

		ArrayList<Tile> run = new ArrayList<>();
		run.add(tiles.get(0));
		for (int i = 1; i < tiles.size(); i++) {
			if (tiles.get(i-1).num == tiles.get(i).num - 1) {
				run.add(tiles.get(i));
			}
			else {
				runs.add(run); //add created run to main ArrayList
				run = new ArrayList<>(); //clear run
				run.add(tiles.get(i));
			}
		}
		runs.add(run);
	}

	public static void createGroups() {
		ArrayList<Tile> group = new ArrayList<>();
		group.add(tiles.get(0));
		for (int i = 1; i < tiles.size(); i++) {
			if (tiles.get(i-1).num == tiles.get(i).num) {
				group.add(tiles.get(i));
			}
			else {
				groups.add(group); //add created group to main ArrayList
				group = new ArrayList<>(); //clear group
				group.add(tiles.get(i));
			}
		}
		groups.add(group);
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
