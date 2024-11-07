package java_basic;

import java.io.*;

public class PlusMinus {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        
        bw.write("2개의 숫자를 입력하세요 : ");
        bw.flush();
        String[] operand = br.readLine().split(" ");
        int operand1 = Integer.parseInt(operand[0]);
        int operand2 = Integer.parseInt(operand[1]);

        bw.write("덧셈 결과 : " + (operand1 + operand2) + "\n");
        bw.write("뺄셈 결과 : " + (operand1 - operand2) + "\n");
        bw.flush();
    }
}
