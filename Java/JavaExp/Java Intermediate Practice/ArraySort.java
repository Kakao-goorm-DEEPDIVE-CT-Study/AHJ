package java_intermediate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ArraySort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("정렬할 배열을 입력하세요 : ");

        String[] inputString = br.readLine().split(" ");
        System.out.println("정렬 전 배열 : " + Arrays.toString(inputString));

        int[] inputsBubble = new int[inputString.length];

        for(int index = 0; index < inputString.length; index++){
            int value = Integer.parseInt(inputString[index]);
            inputsBubble[index] = value;
        }

        int[] inputsInsertion = inputsBubble.clone();
        int[] inputsSelection = inputsBubble.clone();

        bubbleSort(inputsBubble);
        System.out.println("버블정렬 : " + Arrays.toString(inputsBubble));

        insertionSort(inputsInsertion);
        System.out.println("삽입정렬 : " + Arrays.toString(inputsInsertion));

        selectionSort(inputsSelection);
        System.out.println("선택정렬 : " + Arrays.toString(inputsSelection));

    }

    public static void bubbleSort(int[] inputsBubble){
        for(int outerIndex = 0; outerIndex < inputsBubble.length - 1; outerIndex++){
            for(int innerIndex = 0; innerIndex < inputsBubble.length - 1 -outerIndex; innerIndex++){
                if(inputsBubble[innerIndex] > inputsBubble[innerIndex+1]){
                    changeValue(inputsBubble, innerIndex + 1, innerIndex);
                }
            }
            System.out.println("버블정렬 중간단계 : " + Arrays.toString(inputsBubble));
        }
    }

    public static void insertionSort(int[] inputsInsertion){
        for(int outerIndex = 1; outerIndex < inputsInsertion.length; outerIndex++){
            for (int innerIndex = outerIndex; innerIndex > 0; innerIndex--){
                //선택한 값이 정렬된 값보다 작다면 교환
                if(inputsInsertion[innerIndex] < inputsInsertion[innerIndex - 1]){
                    changeValue(inputsInsertion,innerIndex, innerIndex - 1);
                }else{//선택한 값이 정렬된 값보다 크다면 정렬 완료 -> 내부 루프 종료
                    break;
                }
            }
            System.out.println("삽입정렬 중간단계 : " + Arrays.toString(inputsInsertion));
        }
    }

    public static void selectionSort(int[] inputsSelection){
        for(int outerIndex = 0; outerIndex < inputsSelection.length; outerIndex++){
            int minIndex = outerIndex;
            for(int innerIndex = outerIndex + 1; innerIndex < inputsSelection.length; innerIndex++){
                if(inputsSelection[innerIndex] < inputsSelection[outerIndex]){
                    minIndex = innerIndex;
                }
            }
            changeValue(inputsSelection,outerIndex,minIndex);
            System.out.println("선택정렬 중간단계 : " + Arrays.toString(inputsSelection));
        }
    }

    public static void changeValue(int[] inputs, int index1, int index2){
        int changeValue = inputs[index1];
        inputs[index1] = inputs[index2];
        inputs[index2] = changeValue;
    }
}
