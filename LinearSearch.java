import java.util.Scanner;
public class LinearSearch {
    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Enter the no. of elements:");
        int n = inp.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = inp.nextInt();
        }
        System.out.println("Enter the key to search:");
        int key = inp.nextInt();
        int result = linearSearch(arr, key);
        if (result == -1) {
            System.out.println("Not found");
        } else {
            System.out.println("Found at index " + result);
        }
        inp.close();
    }
}