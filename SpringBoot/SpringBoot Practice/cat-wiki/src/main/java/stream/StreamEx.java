package stream;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

public class StreamEx {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "String", "API");

        list.stream().forEach(System.out::println);

        list.stream()
                .filter(s -> s.startsWith("J"))
                .forEach(System.out::println);

        list.stream()
//                .map(s -> s.toUpperCase())
                .map(String::toUpperCase)//map은 모든 요소에 적용하는 메서드이기에 가능
                .forEach(System.out::println);

        int totalLength = list.stream()
                .mapToInt(String::length)
                .sum();
        System.out.println("list의 전체 길이 : " + totalLength);
    }
}
