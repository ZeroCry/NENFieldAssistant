package nicknestor.nenfieldassistant;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nick on 1/17/2016.
 */
public class UsefulClasses {
    public static String getCurrentTimeStamp(){
    try {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

        return currentTimeStamp;
    } catch (Exception e) {
        e.printStackTrace();

        return null;
    }
}

}
