import java.io.*;
import java.util.*;
import java.text.*;

public class MenuDrivenSalesReport {

    public static void main(String[] args) {
        String fileName = "Donot.txt";
        List<Map<String, String>> records = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        // Step 1: Read header and file data
        String[] headers = null;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String headerLine = br.readLine();
            if (headerLine == null) {
                System.out.println("File is empty!");
                return;
            }

            headers = headerLine.split("\\t");

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\t");
                if (values.length == headers.length) {
                    Map<String, String> record = new HashMap<>();
                    for (int i = 0; i < headers.length; i++) {
                        record.put(headers[i], values[i]);
                    }
                    records.add(record);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // Step 2: Calculate once (to avoid re-reading file later)
        double totalSales = 0.0;
        Map<String, Double> empSales = new HashMap<>();
        Map<String, Double> productSales = new HashMap<>();
        Map<String, Double> regionSales = new HashMap<>();
        Map<String, Double> monthSales = new HashMap<>();

        SimpleDateFormat inFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        SimpleDateFormat altFormat = new SimpleDateFormat("MMM-dd-yy", Locale.ENGLISH);
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);

        for (Map<String, String> record : records) {
            try {
                int qty = Integer.parseInt(record.get("Qty").trim());
                double unitPrice = Double.parseDouble(record.get("Unit Price").trim());
                double saleAmount = qty * unitPrice;
                totalSales += saleAmount;

                String emp = record.get("Rep ID");
                empSales.put(emp, empSales.getOrDefault(emp, 0.0) + saleAmount);

                String product = record.get("Product");
                productSales.put(product, productSales.getOrDefault(product, 0.0) + saleAmount);

                String region = record.get("Region");
                regionSales.put(region, regionSales.getOrDefault(region, 0.0) + saleAmount);

                String dateStr = record.get("Date").trim();
                Date date = null;
                try {
                    date = inFormat.parse(dateStr);
                } catch (ParseException e) {
                    try {
                        date = altFormat.parse(dateStr);
                    } catch (ParseException ex) {
                        continue;
                    }
                }
                String month = monthFormat.format(date);
                monthSales.put(month, monthSales.getOrDefault(month, 0.0) + saleAmount);

            } catch (Exception e) {
                // skip bad record
            }
        }

        // Step 3: Auto-generate menu
        int choice = -1;
        do {
            System.out.println("\n===== AUTO-GENERATED MENU =====");
            System.out.println("1. View Total Sales Amount");
            System.out.println("2. View Employee-wise Sales");
            System.out.println("3. View Product-wise Sales");
            System.out.println("4. View Region-wise Sales");
            System.out.println("5. View Month-wise Sales");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.printf("\nTotal Sales Amount: %.2f\n", totalSales);
                    break;
                case 2:
                    System.out.println("\nEmployee-wise Sales:");
                    empSales.forEach((k, v) -> System.out.printf("%s : %.2f\n", k, v));
                    break;
                case 3:
                    System.out.println("\nProduct-wise Sales:");
                    productSales.forEach((k, v) -> System.out.printf("%s : %.2f\n", k, v));
                    break;
                case 4:
                    System.out.println("\nRegion-wise Sales:");
                    regionSales.forEach((k, v) -> System.out.printf("%s : %.2f\n", k, v));
                    break;
                case 5:
                    System.out.println("\nMonth-wise Sales:");
                    monthSales.forEach((k, v) -> System.out.printf("%s : %.2f\n", k, v));
                    break;
                case 0:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 0);

        sc.close();
    }
}