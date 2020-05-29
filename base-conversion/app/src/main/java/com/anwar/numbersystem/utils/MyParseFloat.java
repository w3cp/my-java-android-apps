package com.anwar.numbersystem.utils;

public class MyParseFloat {
    public double parseFloat(String stringNum, int radix) {
        String[] parts = stringNum.split(".");
        if ( parts.length > 1 ) {
            return Integer.parseInt(parts[0], radix) + Integer.parseInt(parts[1], radix) / Math.pow(radix, parts[1].length());
        }
        return Integer.parseInt(parts[0], radix);
    }
}