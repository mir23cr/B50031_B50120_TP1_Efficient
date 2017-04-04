package parser;

/**
 * Created by Vladimir Aguilar on 28/3/2017.
 */
public class BooleanParser extends Parser<Boolean>{
    @Override
    public Boolean parse(String data) {
        if(data.equals("true")){
            return true;
        }else{
            return false;
        }
    }
}
