package java_intermediate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringChange {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        reverseString(input);
        changeCase(input);
    }

    public static void reverseString(String input){

        StringBuilder reverseString = new StringBuilder();
        char[] reverseWords = new char[input.length()];

        for(int index = 0; index < input.length(); index++){
            reverseWords[input.length() - (index + 1)] = input.charAt(index);
        }

        for(char word : reverseWords){
            reverseString.append(word);
        }

        System.out.println(reverseString);
    }

    public static void changeCase(String input){

        StringBuilder changeString = new StringBuilder();

        for(int index = 0; index < input.length(); index++){
            char word = input.charAt(index);
            if(Character.isLowerCase(word)){
                changeString.append(Character.toUpperCase(word));
            } else{
                changeString.append(Character.toLowerCase(word));
            }
        }
        System.out.println(changeString);
    }
}
