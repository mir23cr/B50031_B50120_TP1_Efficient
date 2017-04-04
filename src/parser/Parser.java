package parser;

/**
 * Created by Vladimir Aguilar on 28/3/2017.
 */
public abstract class Parser<T> {
    public abstract T parse(String data);
}
