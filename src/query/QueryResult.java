package query;

import java.util.ArrayList;

/**
 * Created by vladimir on 28/03/17 .
 */
public class QueryResult {
    private ArrayList<String[]> rows;

    public QueryResult() {
        this.rows = new ArrayList<String[]>();
    }

    public ArrayList<String[]> getRows() {
        return rows;
    }

    public int getNumberOfResults() {
        return this.rows.size();
    }


    public void addRow(String[] row){
        this.rows.add(row);
    }

    public void print(){
        for(String[] row : rows){
            for(String s : row){
                System.out.print(s + " ");
            }
            System.out.println();
        }
        System.out.println("Number of results: " + rows.size());
    }
}
