package query;

import java.util.ArrayList;

/**
 * Class that returns the result of the query
 * @author Vladimir Aguilar
 * @author Mariana Abellan
 * Creation Date: 24/3/2017
 */
public class QueryResult {
    private ArrayList<ArrayList<String>> rows;

    public QueryResult() {
        this.rows = new ArrayList<>();
    }

    /**
     * Adds a row
     * @param row String[]
     * @return void
     * */
    public void addRow(ArrayList<String> row){
        this.rows.add(row);
    }

    /**
     * set the result
     * @param result ArrayList<ArrayList<String>>
     * @return void
     * */
    public void setResult(ArrayList<ArrayList<String>> result){
        this.rows = result;
    }

    /**
     * Prints the result with all the columns
     * @param
     * @return void
     * */
    public void printAllColumns(){
        for(ArrayList<String> row : rows){
            for(String s : row){
                System.out.print(s + " ");
            }
            System.out.println();
        }
        System.out.println("Number of results: " + rows.size());
    }

    /**
     * Print the selected columns by the user
     * @param cols ArrayList<Integer>
     * @return void
     * */
    public void printSelectedColumns(ArrayList<Integer> cols){
        for(ArrayList<String> row : rows){
            for(Integer i : cols){
                System.out.print(row.get(i) + " ");
            }
            System.out.println();
        }
        System.out.println("Number of results: " + rows.size());
    }
}
