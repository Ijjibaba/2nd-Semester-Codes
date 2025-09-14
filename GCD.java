import java.util.Scanner;
public class GCD{
    static int gcd(int n , int m){
        if (m == 0){
            return n;
        }else{
            return gcd(m, n % m);
        }
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter 1st number to find their GCD: ");
        int n = scanner.nextInt();
        System.out.print("Enter 2nd number to find their GCD: ");
        int m = scanner.nextInt();
        System.out.println("GCD of " + n + " and " + m + " is: " + gcd(n, m));
        scanner.close();
    }
}