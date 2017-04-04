package parser;

/**
 * Created by Vladimir Aguilar on 28/3/2017.
 */
public class IntegerParser extends Parser<Integer> {
    @Override
    public Integer parse(String data) {
        return Integer.parseInt(data);
    }
}
