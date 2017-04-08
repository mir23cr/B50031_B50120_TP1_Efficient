package query;

import java.util.List;

/**
 * Class that contains and manages the parameters of the queries
 * @author Vladimir Aguilar
 * @author Mariana Abellan
 * Creation Date: 24/3/2017
 */
public class QueryParameters {
    private int column;
    private int operation;
    private List parameters;

    public QueryParameters(int column, int operation, List<String> parameters) {
        this.column = column;
        this.operation = operation;
        this.parameters = parameters;
    }

    /**
     * Returns the column used for the queries
     * @param
     * @return column
     * */
    public int getColumn() {
        return column;
    }

    /**
     * Returns the operation used for the queries
     * @param
     * @return operation
     * */
    public int getOperation() {
        return operation;
    }

    /**
     * Returns the parameters of the queries
     * @param
     * @return parameters
     * */
    public List getParameters() {
        return parameters;
    }

    /**
     * Set the parameters of the queries
     * @param parameters List
     * @return void
     * */
    public void setParameters(List parameters) {
        this.parameters = parameters;
    }

}
