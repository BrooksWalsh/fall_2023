import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class AutoTest {

	public static void main(String[] args) {
		String filename1 = args[0];
		String filename2 = args[1];
		File file1 = new File(filename1);
		File file2 = new File(filename2);
		Scanner file1Scanner = null;
		Scanner file2Scanner = null;
		try {
			file1Scanner = new Scanner(file1);
			file2Scanner = new Scanner(file2);
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(1);
		}
		int count = 0;
		int line = 0;
		while (file1Scanner.hasNextLine()) {
			line++;
			String line1 = file1Scanner.nextLine();
			String line2 = file2Scanner.nextLine();
			
            // clean the lines from spaces
			line1 = line1.trim();
			line1 = line1.replaceAll("\t", " ");
			line1 = line1.replaceAll("( )+", " ");
			
			line2 = line2.trim();
			line2 = line2.replaceAll("\t", " ");
			line2 = line2.replaceAll("( )+", " ");

			// compare the two lines
			if(!line1.equals(line2) && !(line1.startsWith("Profit") || line1.startsWith("Loss"))) {
				System.out.println("line " + line + ": " + line1 + "\n does not match: " + line2);
				count++;
			}
		}
		file1Scanner.close();
		file2Scanner.close();
		if(count == 0) {
			System.out.println("All tests passed.");
			System.exit(0);
		}
		else {
			System.out.println( (line-count) + " lines match the reference output out of " + line + " lines");
			System.exit(1);
		}

	}

}
