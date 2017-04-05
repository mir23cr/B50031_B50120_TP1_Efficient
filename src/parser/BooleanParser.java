package parser;

/**
 * Class that parse the boolean
 * @author Vladimir Aguilar
 * @author Mariana Abellan
 * {28/3/2017}
 */
public class BooleanParser extends Parser<Boolean> {
    /**
     * Return the parsed data
     * @param  data String
     * @return Boolean
     * */
    @Override
    public Boolean parse(String data) {
        return data.equals("true");
    }
}
