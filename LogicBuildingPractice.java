import java.io.*;
import java.util.*;

class Country {
    String name;
    double surfaceArea;
    int indepYear;
    long population;
    double lifeExpectancy;
    double gnp;
    double gnpOld;

    public Country(String[] data) {
        name = data[1];
        surfaceArea = Double.parseDouble(data[4]);
        indepYear = data[5].equals("NULL") ? 0 : Integer.parseInt(data[5]);
        population = Long.parseLong(data[6]);
        lifeExpectancy = data[7].equals("NULL") ? 0 : Double.parseDouble(data[7]);
        gnp = data[8].equals("NULL") ? 0 : Double.parseDouble(data[8]);
        gnpOld = data[9].equals("NULL") ? 0 : Double.parseDouble(data[9]);
    }
}

public class LogicBuildingPractice{

    ArrayList<Country> ciList = new ArrayList<>();

    public LogicBuildingPractice(String filePath) {
        readFile(filePath);
    }

    void readFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");

                if (parts.length > 9) {  
                    ciList.add(new Country(parts));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    double colSum(int colIndex) {
        double sum = 0;

        for (Country c : ciList) {
            switch (colIndex) {
                case 1 -> sum += c.surfaceArea;
                case 3 -> sum += c.population;
                case 4 -> sum += c.lifeExpectancy;
                case 5 -> sum += c.gnp;
                case 6 -> sum += c.gnpOld;
            }
        }

        return sum;
    }

    public static void main(String[] args) {

        String path = "CountryInfo.txt";

        System.out.println("Using File: " + path);

        LogicBuildingPractice obj = new LogicBuildingPractice(path);

        System.out.println("\n--- Results ---");
        System.out.println("1. Sum of Surface Areas: " + obj.colSum(1));
        System.out.println("2. Sum of Populations: " + obj.colSum(3));

        double totalLifeExp = obj.colSum(4);
        double avgLifeExp = totalLifeExp / obj.ciList.size();

        System.out.println("3. Average Life Expectancy: " + avgLifeExp);
    }
}