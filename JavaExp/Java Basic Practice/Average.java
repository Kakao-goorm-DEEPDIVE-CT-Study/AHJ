package java_basic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Average {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int sum = 0;
        for(int num : array){
            sum += num;
        }
        double average = sum / array.length;
        bw.write("평균 : " + average + "\n");
        bw.flush();

        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] + array[i];
        }
        bw.write("변경된 배열 : ");
        for (int i = 0; i < array.length; i++) {
            bw.write(array[i] + " ");
        }
        bw.flush();
    }
}
