package parser;

/**
 * Class that parse the Double
 * @author Vladimir Aguilar
 * @author Mariana Abellan
 * {28/3/2017}
 */
public class DoubleParser extends Parser<Double> {
    /**
     * Return the parsed data
     * @param  data String
     * @return Double
     * */
    @Override
    public Double parse(String data) {
        return Double.parseDouble(data);
    }
}
