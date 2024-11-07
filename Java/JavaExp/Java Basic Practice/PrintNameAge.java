package java_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrintNameAge {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("이름,나이 : ");
        String[] input = br.readLine().split(" ");
        System.out.println(input[0] + " " + Integer.parseInt(input[1]));
        
    }
}