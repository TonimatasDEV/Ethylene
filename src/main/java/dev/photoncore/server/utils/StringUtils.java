package dev.photoncore.server.utils;

public class StringUtils {
    public static String inverseBoolean(String value) {
        if (value.equals("true")) {
            return "false";
        } else {
            return "true";
        }
    }
}
