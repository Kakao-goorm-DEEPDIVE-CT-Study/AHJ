package java_intermediate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RamdaCalculator{
    @FunctionalInterface
    interface Calculate{
        double calculate(double operand1, double operand2);
    }

    static Calculate plus = (operand1, operand2) -> operand1 + operand2;
    static Calculate minus = (operand1, operand2) -> operand1 - operand2;
    static Calculate multiply = (operand1, operand2) -> operand1 * operand2;
    static Calculate divide = (operand1, operand2) -> {
        if(operand2 == 0){
            throw new ArithmeticException("0 으로 나눌 수 없습니다");
        }
        return operand1 / operand2;
    };


    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            double result;
            System.out.print("연산을 입력하세요 (예: 2 + 3 / 종료 : x): ");

            String[] inputs = br.readLine().split(" ");


            double operand1 = Double.parseDouble(inputs[0]);
            String operator = inputs[1];
            double operand2 = Double.parseDouble(inputs[2]);

            result = calculate(operand1, operator, operand2);

            System.out.println("연산 결과 : " + result);

        } catch (NumberFormatException e) {
            System.out.println("예외 발생 : 숫자를 입력하세요.");
        } catch (ArithmeticException e) {
            System.out.println("예외 발생 : " + e.getMessage());
        } catch (UnsupportedOperationException e){
            System.out.println("예외 발생 : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("예외 발생 : 입력 오류가 발생했습니다.");
        }
    }

    public static double calculate(double operand1, String operator, double operand2){

        return switch (operator){
            case "+"  -> plus.calculate(operand1, operand2);
            case "-" -> minus.calculate(operand1, operand2);
            case "*" -> multiply.calculate(operand1, operand2);
            case "/" -> divide.calculate(operand1, operand2);
            default -> throw new UnsupportedOperationException("지원하지 않는 연산자입니다.");
        };
    }
}
