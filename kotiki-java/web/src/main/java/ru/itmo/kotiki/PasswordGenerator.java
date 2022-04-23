package ru.itmo.kotiki;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Scanner;

public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Scanner console = new Scanner(System.in);
        String rawPassword = console.nextLine();
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
}
