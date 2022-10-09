package com.wtf.javabase.regexp;

import java.util.Arrays;

public class RegexpUtils {

    //至少0个空格
    private final String SPACE_0_OR_MORE_="\\s*";
    //至少1个空格
    private final String SPACE_1_OR_MORE_="\\s+";
    //至少2个空格
    private final String SPACE_2_OR_MORE_="\\s{2,}";



    public static void main(String[] args) {
        String s = " 1 2  3   456";
        String[] s1 = s.split("\\s+");
        String[] s2 = s.split("\\s*");
        String[] s3 = s.split("\\s{2,}");

        Arrays.stream(s1).forEach(System.out::println);
        System.out.println("--");
        Arrays.stream(s2).forEach(System.out::println);
        System.out.println("--");
        Arrays.stream(s3).forEach(System.out::println);
    }

}
