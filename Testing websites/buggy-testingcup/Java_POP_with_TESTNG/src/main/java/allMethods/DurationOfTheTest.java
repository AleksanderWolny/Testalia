package allMethods;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DurationOfTheTest {
    public static String convertSecondToHours(long ms){
        return (new SimpleDateFormat("mm:ss:SSS")).format(new Date(ms));
    }
}