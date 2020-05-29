package com.anwar.numbersystem.utils;

public class StringToInteger {
    public int parseInt(String stringNum,int radix) {
        int num = 0;
        try {
            num = Integer.parseInt(stringNum, radix);
        } catch (NumberFormatException e) {
            return -1;
        }
        return num;
    } // end method parseInt
} // end clas StringToInteger
