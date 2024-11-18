package java_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InvalidClassException;

public class PrintNameAge {
    public static void main(String[] args) throws IOException {
        /*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("이름, 나이 : ");
        String[] input = br.readLine().split(" ");
        System.out.println(input[0] + " " + Integer.parseInt(input[1]));
        */

        //피드백 후 개선 코드
        //피드백 : 이름에 띄어쓰기가 있을 시 오류 발생
        //피드백 : 예외처리 수행

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
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
        } catch(NumberFormatException e){
            System.out.println("입력 오류[나이] : 숫자를 입력하세요");
        } catch (IOException e){
            System.out.println("입력 오류 발생");
        }

        //GPT 참고 코드
        //더 다양한 예외처리
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("이름 나이 : " );

            String input = br.readLine();
            if(input == null || input.trim().isEmpty()){
                throw new java_basic.PrintNameAge.EmptyInputException("입력 오류 : 아무것도 입력하지 않았습니다");
            }

            int index = input.lastIndexOf(" ");
            if(index == -1){
                throw new java_basic.PrintNameAge.InvalidFormatException("입력 오류 : 이름과 나이를 올바르게 입력하세요");
            }

            String name = input.substring(0,index).trim();
            int age = Integer.parseInt(input.substring(index).trim());

            if(name.isEmpty()){
                throw new java_basic.PrintNameAge.EmptyNameException("입력 오류 : 이름을 입력하세요.");
            }

            System.out.println("이름 : " + name + " 나이 : " + age);

        } catch (java_basic.PrintNameAge.EmptyInputException | java_basic.PrintNameAge.InvalidFormatException |
                 java_basic.PrintNameAge.EmptyNameException e){
            System.out.println(e.getMessage());
        } catch (NumberFormatException e){
            System.out.println("입력 오류 : 나이에 숫자를 입력하세요");
        } catch (IOException e){
            System.out.println("입력 오류 발생");
        }
    }
    static class EmptyInputException extends Exception{
        public EmptyInputException(String message){
            super(message);
        }
    }
    static class InvalidFormatException extends Exception{
        public InvalidFormatException(String message){
            super(message);
        }
    }
    static class EmptyNameException extends Exception{
        public EmptyNameException(String message){
            super(message);
        }

    }
}