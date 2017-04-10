package com.ankoma88.app.moviegrid.util;


import java.text.SimpleDateFormat;
import java.util.Locale;

public class Utils {

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
    public static SimpleDateFormat dateFormatDefault = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static <T> T checkNotNull(T reference, Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return reference;
    }

}
