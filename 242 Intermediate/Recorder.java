import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Show {
	public int start;
	public int end;
	public String name;

	public Show(int s, int e, String n) {
		start = s;
		end = e;
		name = n;
	}
}

 public class Recorder {
	 private static ArrayList<Show> shows = new ArrayList<>();

	 public static void main(String[] args) {
		 readInput("input.txt");

		 for (int i = 1; i < shows.size(); i++) {
		 	if (shows.get(i - 1).end > shows.get(i).start) {
				shows.remove(i);
				i--;
			}
		 }

		 for (Show s : shows) {
			 System.out.println(s.name);
		 }
	 }

	 public static void readInput(String file) {
		 try {
			 Scanner s = new Scanner(new File(file));

			 int start;
			 int end;
			 String name;

			 while(s.hasNext()) {
				 start = s.nextInt();
				 end = s.nextInt();
				 name = s.nextLine();

				 shows.add(new Show(start, end, name));
			 }
		 }
	 	catch(FileNotFoundException e) {
		 	e.printStackTrace();
	 	}
	 }
 }
