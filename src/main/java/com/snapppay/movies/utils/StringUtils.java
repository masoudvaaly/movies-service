package com.snapppay.movies.utils;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StringUtils {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static Set<String> generateNames(int minLength, int maxLength, int count) {
        Set<String> names = new HashSet<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int nameLength = random.nextInt(maxLength - minLength + 1) + minLength; // Random length between min and max
            StringBuilder name = new StringBuilder();

            for (int j = 0; j < nameLength; j++) {
                char randomChar = ALPHABET.charAt(random.nextInt(ALPHABET.length()));
                if (j == 0) {
                    // Capitalize the first letter
                    randomChar = Character.toUpperCase(randomChar);
                }
                name.append(randomChar);
            }
            names.add(name.toString());
        }
        return names;
    }
}
