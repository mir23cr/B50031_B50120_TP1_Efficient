package parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vladimir Aguilar on 28/3/2017.
 */
public class DateParser extends Parser<Date> {

    @Override
    public Date parse(String data) {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date result =  df.parse(data);
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
