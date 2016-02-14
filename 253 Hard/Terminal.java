import java.util.*;
import java.io.*;


public class Terminal {
	private static char[][] screen = new char[10][10];
	private static int row = 0;
	private static int col = 0;

	private static boolean insert = true;

	public static void main(String[] args) {
		for (char[] row : screen) {
			Arrays.fill(row, ' ');
	    }

		readInput("input.text");
		printScreen();

		readInput("input2.text");
		printScreen();
	}

	public static void readInput(String file) {
		try {
			Scanner s = new Scanner(new File(file));

			while (s.hasNextLine()) {
				String line = s.nextLine();

				for (int i = 0; i < line.length(); i++) {
					char c = line.charAt(i);

					if (c == '^') {
						switch(line.charAt(i + 1)) {
							case 'c': for(char[] row : screen) {Arrays.fill(row, ' ');}
									  break;
							case 'h': row = 0; col = 0;
									  break;
							case 'b': col = 0;
									  break;
							case 'u': row--;
									  if (row < 0) {row = 0;}
									  break;
							case 'd': row++;
									  if (row >= screen.length) {row = screen.length - 1;}
									  break;
							case 'l': col--;
									  if (col < 0) {col = 0;}
									  break;
							case 'r': col++;
									  if (col >= screen[row].length) {col = screen[row].length - 1;}
						    		  break;
							case 'e': for (int j = 0; j < screen[row].length; j++) {screen[row][col] = ' ';}
									  break;
							case 'i': insert = true;
									  break;
							case 'o': insert = false;
									  break;
							case '^': screen[row][col] = '^';
									  break;
							default:  row = Character.getNumericValue(line.charAt(i + 1));
									  col = Character.getNumericValue(line.charAt(i + 2));
									  i++; //increment one extra because it uses two control characters
						}
						i++; //increment to avoid printing control character
					}
					else {
						if (insert) {
							for (int j = screen[row].length - 1; j > col; j--) {
								screen[row][j] = screen[row][j-1];
							}
						}
						screen[row][col] = c;
						col++;
						if (col >= screen[row].length) {
							col = screen[row].length - 1;
						}
					}
				}
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void printScreen() {
		for (char[] row : screen) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
}
