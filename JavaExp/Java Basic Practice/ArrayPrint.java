package java_basic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ArrayPrint {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] array = {1,2,3,4,5,6,7,8,9,10};
        for(int num : array){
            bw.write(num + " ");
        }
        bw.flush();
        bw.close();
    }
}
