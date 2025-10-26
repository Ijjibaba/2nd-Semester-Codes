public class Linkedlist {
    public static void main(String[] args) {

        int [][] arr = { {31,6},{92,-1},{64,4},{55,2},{80,1},{22,0},{47,3} };
        int startindex = 5;
        int i; i=startindex;
        while (i >= 0) {
            System.out.print(arr[i][0] + " ");// data
            i = arr[i][1];// changes index
            if(i >= 0) {
                System.out.print("-> ");
                System.out.print(i + " ");// current index
                int nextindex = arr[i][1];// prints next index
                System.out.print(nextindex + " ");
                System.out.println();
            }
        }
    }
}