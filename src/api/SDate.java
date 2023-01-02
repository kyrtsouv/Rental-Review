package api;

import java.util.Date;
import java.text.SimpleDateFormat;

public class SDate {

    public static String dateToString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(new Date());
    }

}
