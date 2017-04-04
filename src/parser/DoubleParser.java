package parser;

/**
 * Created by Vladimir Aguilar on 28/3/2017.
 */
public class DoubleParser extends Parser<Double> {
    @Override
    public Double parse(String data) {
        return Double.parseDouble(data);
    }
}
