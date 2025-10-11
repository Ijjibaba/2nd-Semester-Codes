public class HammingCode {

    public static void main(String[] args) {
        int[] msg = {1,0,1,1,  0,1,0,0,  1,0,1,0,  1,1,0,1};
        //int[] msg = {1,0,1,1,  0,1,0,0};
        //int[] msg = {1,0,1,1};
        //int[] msg = {1};
        //int[] msg = {0};

        int s;
        int blockSize;
        int blockStart;
        for (int n=0; n<5;n++)
        {
            blockStart = (int) (Math.pow(2,n));
            blockSize = (int) Math.pow(2,n);
            s = 0;
            for (int i=blockStart; i < msg.length; i+=blockSize*2)
            {  
                for (int k = 0; k < blockSize ;k++)
                {
                    if ((i+k) < msg.length)
                        s += msg[i+k];
                }
            }
            System.out.println ("for hamming bit-"+(n+1)+" the sum is "+s);
        }
    }
}