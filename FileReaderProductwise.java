import java.io.*;
import java.util.*;

public class FileReaderProductwise {
    public static void main(String[] args) {
        String path = "Donot.txt";
        List<String[]> rows = new ArrayList<>();
        String header = "";

        try (Scanner scanner = new Scanner(new File(path))) {

            // Read header
            if (scanner.hasNextLine()) {
                header = scanner.nextLine();
            }

            // Read rest of the data
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\t"); // split by tab
                rows.add(parts);
            }

            // Sort rows by Product column (index 3)
            rows.sort(Comparator.comparing(a -> a[3]));

            // Print header first
            System.out.println(header);

            // Print sorted rows exactly as they were in the file
            for (String[] row : rows) {
                System.out.println(String.join("\t", row));
            }

            System.out.println("\nSorted successfully by product.");

        } catch (FileNotFoundException e) {
            System.out.println("File can't be opened.");
        }
    }
}