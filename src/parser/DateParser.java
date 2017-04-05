package parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class that parse the Date
 * @author Vladimir Aguilar
 * @author Mariana Abellan
 * Creation Date: 28/3/2017
 */
public class DateParser extends Parser<Date> {

    /**
     * Return the parsed data
     * @param  data String
     * @return Date
     * */
    @Override
    public Date parse(String data) {
        try {
            DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
            return df.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
