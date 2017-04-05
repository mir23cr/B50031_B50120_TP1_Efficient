package parser;

/**
 * Class that parse the Integer
 * @author Vladimir Aguilar
 * @author Mariana Abellan
 * Creation Date: 28/3/2017
 */
public class IntegerParser extends Parser<Integer> {
    /**
     * Return the parsed data
     * @param  data String
     * @return Integer
     * */
    @Override
    public Integer parse(String data) {
        return Integer.parseInt(data);
    }
}
