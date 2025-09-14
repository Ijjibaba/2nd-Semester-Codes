import java.util.Scanner;
public class Bitoperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the first integer n:");
        int n = sc.nextInt();
        while(n<0 || n>9) {
            System.out.println("Please enter a valid integer between 0 and 9:");
            n = sc.nextInt();
        }
        System.out.println("Enter the bit position:");
        int pos = sc.nextInt();
        while(pos<0 || pos>3) {
            System.out.println("Please enter a valid integer between 0 and 3:");
            pos = sc.nextInt();
        }
        int mask = 1 << pos;
        if ((n & mask) == 0) {
            System.out.println("bit was zero.");
        } else {
            System.out.println("bit was one.");

        }
        
        sc.close();
    }
}