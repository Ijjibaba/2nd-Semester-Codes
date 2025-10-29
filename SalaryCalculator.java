import java.util.*;
public class SalaryCalculator {
    public static void main(String[] args){
        int salary = 20000;
        System.out.println("Starting Salary For Five Hours a day is: " + salary);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of extra hours worked in a day: ");
        int extrahours = sc.nextInt();
        if ( extrahours < 0)
        {
            System.out.println("Invalid Input");
        }else if ( extrahours == 0){
            System.out.println("No extra hours worked. Salary remains: " + salary);
        }else{
            double percent = salary * 2/100;
            double extrapay = percent * extrahours;
            double totalsalary = salary + extrapay;
            System.out.println("Total Salary after working " + extrahours + " extra hours is: " +totalsalary);
        }
        sc.close();
    }
}