package com.example.project.utils;

import java.util.List;

public class Utils {
    public static String generateRoleString(List<String> roles) {
        // Convert List => Array
        String[] arrNames = roles.toArray(new String[0]);

        // Convert Array => String
        return String.join(",", arrNames);
    }
}
