package com.elegidocodes.demo.utility;

public class NumberUtil {

    public static Long getIdFromUrl(String url) {
        String[] segments = url.split("/");
        String id = segments[segments.length - 1].isEmpty()
                ? segments[segments.length - 2]
                : segments[segments.length - 1];
        return Long.parseLong(id);
    }

}
