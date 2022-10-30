package com.example.project.utils;

import org.thymeleaf.util.Validate;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Utils {
    public static String generateRoleString(List<String> roles) {
        // Convert List => Array
        String[] arrNames = roles.toArray(new String[0]);

        // Convert Array => String
        return String.join(", ", arrNames);
    }

}
