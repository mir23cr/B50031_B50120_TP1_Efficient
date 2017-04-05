
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
        FileLoader fl = new FileLoader(System.getProperty("user.dir")+"/PADRON.csv");

        List<String> list = new LinkedList<>(); //List of the comparator of the first QueryParameters
        list.add("601097");
        list.add("601107");
        QueryParameters qP = new QueryParameters(2,7,list);

        QueryExecutor qE = new QueryExecutor(fl);
        QueryResult qR = qE.getSingleQuery(qP);
        qR.print();

        //lp.add(qP3);
        //QueryResult qR /*= q.getSingleQuery(qP)*/;
        //qR = q.getCompoundQuery(lp,2);
        //qR.print();

        /*FileLoader fl = new FileLoader(System.getProperty("user.dir")+"\\info.csv");
        QueryExecutor q = new QueryExecutor(fl);
        List<String> list = new LinkedList<>();
        list.add("Vladimir");
        QueryParameters qP = new QueryParameters(1,1,list);
        QueryResult qR;
        qR = q.getSingleQuery(qP);
        qR.print();*/

        //new Menu();


    }
}
