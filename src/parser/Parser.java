package parser;

/**
 * Abstract class that implements a method to parse by type of data
 * @author Vladimir Aguilar
 * @author Mariana Abellan
 * Creation Date: 28/3/2017
 */
public abstract class Parser<T> {
    /**
     * Return the parsed data
     * @param  data String
     * @return T
     * */
    public abstract T parse(String data);
}
