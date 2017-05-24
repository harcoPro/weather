package com.example.kirill.weather.ui.misc;

import android.support.annotation.NonNull;

import java.util.List;

import rx.functions.Func1;

public class Strings {

    public static final String EMPTY = "";
    public static final String COMMA = ",";
    public static final String POINT = ".";
    public static final String SPACE = " ";
    public static final String QUESTION = "?";
    public static final String EQUALLY = "=";
    public static final String NOT_EQUAL = "<>";
    public static final String CELSIUS = "C";
    public static final int LENGTH = 1024;
//    private static final char[] BUF = new char[1024];

    private Strings() {
        // No instances.
    }

    public static boolean isBlank(CharSequence string) {
        return string == null || string.toString().trim().length() == 0;
    }

    public static String valueOrEmpty(String string) {
        return isBlank(string) ? EMPTY : string;
    }

    public static String valueOrDefault(String string, String defaultString) {
        return isBlank(string) ? defaultString : string;
    }

    public static String truncateAt(String string, int length) {
        return string.length() > length ? string.substring(0, length) : string;
    }

    public static String concatenate(String... strings) {
        StringBuilder sb = new StringBuilder(EMPTY);
        for (String string : strings) {
            sb.append(string);
        }
        return sb.toString();
    }

    public static String createSelectionWithAndEqual(String... fields) {
        return selectionWithAnd(EQUALLY, fields);
    }

    public static String createSelectionWithAndNotEquals(String... fields) {
        return selectionWithAnd(NOT_EQUAL, fields);
    }

    public static String selectionWithAnd(String sign, String... fields) {
        StringBuilder sb = new StringBuilder(EMPTY);
        for (String field : fields) {
            sb.append(field);
            sb.append(sign);
            sb.append(QUESTION);
            sb.append(" and ");
        }
        sb.setLength(sb.length() - 5);
        return sb.toString();
    }

    public static String createSelectionWithIn(String field, String... values) {
        if (isBlank(field)) {
            throw new IllegalArgumentException("Argument field can not be empty!!");
        }

        StringBuilder sb = new StringBuilder(field);
        for (String value : values) {
            sb.append(" in (");
            sb.append(value);
            sb.append(COMMA);
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }

    public static String questionViaComma(int countQuestion) {
        StringBuilder sb = new StringBuilder(Strings.EMPTY);
        if (countQuestion > 0) {
            for (int i = 0; i < countQuestion; i++) {
                sb.append(QUESTION);
                sb.append(COMMA);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String[] listObjectsToStringArr(@NonNull List<?> list) {
        String[] strings = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strings[i] = list.get(i).toString();
        }
        return strings;
    }

    public static String[] listNumbToStringArr(@NonNull List<? extends Number> list) {
        String[] strings = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strings[i] = String.valueOf(list.get(i));
        }
        return strings;
    }

    public static String[] listIdsToStringArr(@NonNull List<Long> list) {
        String[] strings = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strings[i] = String.valueOf(list.get(i));
        }
        return strings;
    }

    public static String[] arrayLongToStringArr(@NonNull long... array) {
        String[] list = new String[array.length];
        for (int i = 0; i < list.length; i++) {
            list[i] = String.valueOf(list[i]);
        }
        return list;
    }

    public static <T> String[] listObjectsToStringArr(@NonNull List<T> list, Func1<T, String> func) {
        String[] strings = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strings[i] = func.call(list.get(i));
        }
        return strings;
    }

    /**
     * Method cleared string of non-printable characters
     *
     * @param s dirty string
     * @return cleared string
     */
    public static String clearString(String s) {

        int length = s.length();
        char[] oldChars = new char[length < LENGTH ? LENGTH : length];
        s.getChars(0, length, oldChars, 0);
        int newLen = 0;
        for (int j = 0; j < length; j++) {
            char ch = oldChars[j];
            if (ch >= ' ') {
                oldChars[newLen] = ch;
                newLen++;
            }
        }
        if (newLen != length)
            s = new String(oldChars, 0, newLen);
        return s;
    }


}
