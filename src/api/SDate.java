package api;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Class that represents a date
 * It has a static method that returns the current date in the format dd/MM/yyyy
 */
public class SDate {

    /**
     * Returns the current date in the format dd/MM/yyyy
     * 
     * @return date
     */
    public static String dateToString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(new Date());
    }

}
