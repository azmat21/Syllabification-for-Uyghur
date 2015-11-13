package com.azim.syllabification;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String uly = "mektep";
        List<String> sybList = new ArrayList<>();
        UyghurSyllabification.getInstance().Syllabication(uly,sybList);
        for (String s:sybList){
            System.out.println(s);
        }
    }
}
