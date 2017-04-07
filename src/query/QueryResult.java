package query;

import java.util.ArrayList;

/**
 *
 * @author Vladimir Aguilar
 * @author Mariana Abellan
 * Creation Date: 28/03/17
 */
public class QueryResult {
    private ArrayList<ArrayList<String>> rows;

    public QueryResult() {
        this.rows = new ArrayList<>();
    }

    public void addRow(ArrayList<String> row){
        this.rows.add(row);
    }

    public void setResult(ArrayList<ArrayList<String>> result){
        this.rows = result;
    }

    public void print(){
        for(ArrayList<String> row : rows){
            for(String s : row){
                System.out.print(s + " ");
            }
            System.out.println();
        }
        System.out.println("Number of results: " + rows.size());
    }
}
