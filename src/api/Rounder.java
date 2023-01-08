package api;

import java.text.DecimalFormat;
//Αυτή είναι μία βοηθητική κλάση για μετατροπή και επιστροφή πραγματικού αριθμού με δύο δεκαδικά στοιχεία
public class Rounder {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static String round(float number) {
        return df.format(number);
    }

}
