package query;

import java.util.ArrayList;

/**
 *
 * @author Vladimir Aguilar
 * @author Mariana Abellan
 * Creation Date: 28/03/17
 */
public class QueryResult {
    private ArrayList<String[]> rows;

    public QueryResult() {
        this.rows = new ArrayList<>();
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
