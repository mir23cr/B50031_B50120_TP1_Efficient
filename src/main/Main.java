
package main;

import file.FileLoader;
import query.QueryExecutor;
import query.QueryParameters;
import query.QueryResult;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        /*FileLoader fl = new FileLoader(System.getProperty("user.dir")+"/PADRON.csv");

        List<String> list = new LinkedList<>(); //List of the comparator of the first QueryParameters
        list.add("VLADIMIR");
        QueryParameters qP = new QueryParameters(5,1,list);

        List<String> list2 = new LinkedList<>(); //List of the comparator of the first QueryParameters
        list2.add("AGUILAR");
        QueryParameters qP2 = new QueryParameters(6,1,list2);

        List<QueryParameters> qParams = new LinkedList<>();
        qParams.add(qP);
        qParams.add(qP2);

        QueryExecutor qE = new QueryExecutor(fl);
        QueryResult qR = qE.getCompoundQuery(qParams,2);
        qR.print();
        */

        new Menu();


    }
}
