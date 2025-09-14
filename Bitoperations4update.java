import java.util.Scanner;
public class Bitoperations4update {
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
        System.out.println("Enter the bit value (0 or 1):");
        int operation = sc.nextInt();
        while(operation < 0 || operation > 1) {
            System.out.println("Please enter a valid bit value (0 or 1):");
            operation = sc.nextInt();
        }

        int mask = 1 << pos;
        int notmask = ~mask; // Create a mask to update the bit at position pos
        int newn;
        // If operation is 1, set the bit; if 0, clear the bit
        
        if (operation == 1) {
            newn = mask | n; // Set the bit at position pos
        } else {
            newn = notmask & n; // Clear the bit at position pos
        }
        System.out.println("New integer after updating the bit: " + newn);

        sc.close();
    }
}