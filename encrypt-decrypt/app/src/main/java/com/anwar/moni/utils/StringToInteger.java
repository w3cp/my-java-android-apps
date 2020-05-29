package com.anwar.moni.utils;

public class StringToInteger {
    public int parseInt(String stringNum) {
        int num = 0;
        try {
            num = Integer.parseInt(stringNum);
        } catch (NumberFormatException e) {
            return -1;
        }
        return num;
    } // end method parseInt
} // end clas StringToInteger
