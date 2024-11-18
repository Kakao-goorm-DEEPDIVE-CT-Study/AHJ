package java_basic;

import java.io.*;
import java.util.*;

public class NumberASC {
    public static void main(String[] args) throws IOException {

       /* BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write("배열에 들어갈 숫자를 입력하세요 : ");
        bw.flush();
        String[] input = br.readLine().split(" ");

        int[] inputNumber = new int[input.length];
        for(int i = 0; i < input.length; i++) {
            inputNumber[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(inputNumber);
        bw.write("정렬된 배열 : ");
        for(int num : inputNumber){
            bw.write(num + " ");
        }
        bw.flush();*/

        //피드백 후 개선 코드
        //피드백 : 정렬 직접 구현해보기

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write("배열에 들어갈 숫자를 입력하세요 : ");
        bw.flush();
        String[] input = br.readLine().split(" ");

        int[] inputNumber = new int[input.length];
        for(int i = 0; i < input.length; i++) {
            inputNumber[i] = Integer.parseInt(input[i]);
        }
        arraySort(inputNumber);
        bw.write("정렬된 배열 : ");
        for(int num : inputNumber){
            bw.write(num + " ");
        }
        bw.flush();
    }
    public static void arraySort(int[] inputNumber){
        //삽입정렬
        for(int outerIndex = 1; outerIndex < inputNumber.length; outerIndex++){
            for(int innerIndex = outerIndex; innerIndex > 0; innerIndex--){
                if(inputNumber[innerIndex] < inputNumber[innerIndex - 1]){
                    int temp = inputNumber[innerIndex];
                    inputNumber[innerIndex] = inputNumber[innerIndex - 1];
                    inputNumber[innerIndex - 1] = temp;
                }
            }
        }
    }

}
