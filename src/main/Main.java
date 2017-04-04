
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
        FileLoader fl = new FileLoader(System.getProperty("user.dir")+"/aes-2015-csv.csv");
        /*List<String> list = new LinkedList<>(); //List of the comparator of the first QueryParameters
        list.add("22");
        QueryParameters qP = new QueryParameters(4,1,list);
        List<String> list2 = new LinkedList<>();//List of the comparator of the second QueryParameters
        list2.add("1.60");
        QueryParameters qP2 = new QueryParameters(6,5,list2);
        List<QueryParameters> lp = new LinkedList<>();
        lp.add(qP);
        lp.add(qP2);

        QueryExecutor qE = new QueryExecutor(fl);
        QueryResult qR = qE.getCompoundQuery(lp,2);
        qR.print();*/

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
