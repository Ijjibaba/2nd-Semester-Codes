import java.util.*;
import java.io.*;
import java.text.*;

public class MenuDrivenSalesReport {

    public static void main(String[] args) {
        List<Map<String, String>> listOFData = new ArrayList<>();

        ///////////////Step-1

        File dataFile = new File("Donot.txt");
        Scanner sc = new Scanner(System.in);
        String line;
        try {
            Scanner readFile = new Scanner(dataFile);

            String header = readFile.nextLine();
            String[] keys = header.split("\t");

            while (readFile.hasNextLine()) {

                line = readFile.nextLine();
                String[] values = line.split("\t");

                if (keys.length == values.length) {
                    Map<String, String> entry = new HashMap<>();

                    for (int i = 0; i < keys.length; i++) {
                        entry.put(keys[i], values[i]);
                    }
                    listOFData.add(entry);

                }

            }

        } catch (Exception e) {
        }

        ///////////////Step-2
        double totalSales = 0.0;
        Map<String, Double> empSales = new HashMap<>();
        Map<String, Double> productSales = new HashMap<>();
        Map<String, Double> regionSales = new HashMap<>();
        Map<String, Double> monthSales = new HashMap<>();

        SimpleDateFormat dmy = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        SimpleDateFormat mdy = new SimpleDateFormat("MMM-dd-yyyy", Locale.ENGLISH);
        SimpleDateFormat output = new SimpleDateFormat("MMMM", Locale.ENGLISH);

        for (Map<String, String> entry : listOFData) {
            int quantity = Integer.parseInt(entry.get("Qty").trim());
            double unitPrice = Integer.parseInt(entry.get("Unit Price").trim());
            double salesAmount = quantity * unitPrice;
            totalSales += salesAmount;

            String emp = entry.get("Rep ID");
            empSales.put(emp, empSales.getOrDefault(emp, 0.0) + salesAmount);

            String prod = entry.get("Product");
            productSales.put(prod, productSales.getOrDefault(prod, 0.0) + salesAmount);

            String region = entry.get("Region");
            regionSales.put(region, regionSales.getOrDefault(region, 0.0) + salesAmount);

            Date date = null;
            String dateStr = entry.get("Date").trim();

            try {
                date = dmy.parse(dateStr);

            } catch (Exception e) {

                try {
                    date = mdy.parse(dateStr);
                } catch (ParseException pe) {
                    continue;
                }

            }

            String month = output.format(date);
            monthSales.put(month, monthSales.getOrDefault(month, 0.0) + salesAmount);

        }
        ///////// Step-3
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

