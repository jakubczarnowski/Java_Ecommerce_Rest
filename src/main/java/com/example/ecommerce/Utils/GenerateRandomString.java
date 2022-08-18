package com.example.ecommerce.Utils;

import java.util.Random;

public class GenerateRandomString {
    public static String generateRandomString(Integer length){
        Random rand = new Random();

        String str = rand.ints(48, 123)
                .filter(num -> (num<58 || num>64) && (num<91 || num>96))
                .limit(length)
                .mapToObj(c -> (char)c).collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)
                .toString();
        return str;
    }
}
