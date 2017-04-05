package validation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vladimir Aguilar on 24/3/2017 .
 */
public class ValidationService {
    private Pattern pattern;
    private Matcher matcher;

    public Boolean isString(String data){
        String regex = "^([a-z]|[A-Z]|[0-9]|\\ |\\s)*$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(data);
        return matcher.find();
    }

    public Boolean isInt(String data){
        String regex = "^([0-9])+$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(data);
        return matcher.find();
    }

    public Boolean isDouble(String data){
        String regex = "^([0-9]+\\.?[0-9]*)$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(data);
        return matcher.find();
    }

    public Boolean isDate(String data){
        String regex = "^((([1|3|5|7])|(1(1|2)))\\/([1-9]|(1[0-9])|(2[0-9])|3([0-1])))|(([4|6|8|9|11])\\/([1-9]|(1[0-9])|(2[0-9])|(30)))|(2\\/([1-9]|(1[0-9])|(2[0-9])))\\/([0-9]*)$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(data);
        return matcher.find();
    }

    public Boolean isBool(String data){
        String regex = "^(true|false)$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(data);
        return matcher.find();
    }
}
