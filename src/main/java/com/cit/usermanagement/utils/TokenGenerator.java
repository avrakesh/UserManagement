package com.cit.usermanagement.utils;
import java.util.UUID;

public class TokenGenerator {
    public static String generateToken() {
        return UUID.randomUUID().toString();
    }
    
    public static void main(String[] args) {
		System.out.println(generateToken());
	}
}
