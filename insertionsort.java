public class insertionsort {
    public static void main(String[] args) {
        int[] arr = {4,1,5,2,3};
        for (int i = 1; i < arr.length ; i++){
            int k = arr[i];
            int j = i - 1;
            while(j >= 0 && arr[j] > k){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = k;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}