public class GeneralizePattern {
    public static void main(String[] args) {
        System.out.println("H1");
        generatePattern(1,21,1);
        System.out.println("H2");
        generatePattern(2,21,2);
        System.out.println("H3");
        generatePattern(4,21,4);
        System.out.println("H4");
        generatePattern(8,40,8);
    }
    static void generatePattern(int start, int end, int step) {
        int c=0;
        for(int i = start; i <= end;) {
            for(int j = 1; j < i+step && j <= end; j++) {
                System.out.print(j);
                c=j;
            }
            i=c+step+1;
        }
        System.out.println();
    }
}