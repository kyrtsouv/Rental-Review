package api;

import java.text.DecimalFormat;

public class Rounder {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static String round(float number) {
        return df.format(number);
    }

}
