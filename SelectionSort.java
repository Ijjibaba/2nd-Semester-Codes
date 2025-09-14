import java.util.Scanner;
public class SelectionSort {                         
 static void selectionSort(int[] arr) {    
    for (int i = 0; i < arr.length - 1; i++) {   
        int minIndex = i;                        
        for (int j = i + 1; j < arr.length; j++){
            if (arr[j] < arr[minIndex]) {        
                minIndex = j;                    
            }                                    
        }                                        
        int temp = arr[minIndex];                
        arr[minIndex] = arr[i];
        arr[i] = temp;
    }                                            
}                                                
public static void main(String[] args) {         
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the number of elements: ");
    int n = scanner.nextInt();
    int[] arr = new int[n];
    System.out.println("Enter " + n + " integers:");
    for (int i = 0; i < n; i++) {
        arr[i] = scanner.nextInt();
    }
    selectionSort(arr);             
    for (int num : arr)                          
        System.out.print(num + " ");
    scanner.close();
    }
}