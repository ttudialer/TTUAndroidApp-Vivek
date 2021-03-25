package com.kabaladigital.tingtingu.util;

import java.text.DecimalFormat;

public class NumberFormat {

    public static String decimalFormat(double value){
        DecimalFormat precision = new DecimalFormat("0.00");
        return precision.format(value);
    }
}
