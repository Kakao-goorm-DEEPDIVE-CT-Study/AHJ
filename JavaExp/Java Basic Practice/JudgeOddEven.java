package java_basic;

import java.io.*;

public class JudgeOddEven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write("숫자를 입력하세요 : ");
        bw.flush();
        int inputNumber = Integer.parseInt(br.readLine());
        if(inputNumber % 2 == 0){
            bw.write("짝수입니다");
        }else {
            bw.write("홀수입니다");
        }
        bw.flush();
        bw.close();
    }
}
