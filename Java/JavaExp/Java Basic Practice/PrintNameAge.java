package java_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrintNameAge {
    public static void main(String[] args) throws IOException {
        /*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("이름, 나이 : ");
        String[] input = br.readLine().split(" ");
        System.out.println(input[0] + " " + Integer.parseInt(input[1]));
        */

        //피드백 후 개선 코드
        //피드백 : 이름에 띄어쓰기가 있을 시 오류 발생

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("이름, 나이 : ");
        //input 받기
        String input = br.readLine();
        //age부분의 index 추출
        int index = input.lastIndexOf(" ");
        //이름 추출
        String name = input.substring(0,index);
        //나이 추출
        String age = input.substring(index);

        System.out.println("이름 : " + name + " 나이 : " + age);
    }
}