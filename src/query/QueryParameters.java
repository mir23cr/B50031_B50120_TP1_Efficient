package query;

import java.util.List;

/**
 * Created by vladimir.aguilar on 24/3/2017 .
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

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List parameters) {
        this.parameters = parameters;
    }

}
