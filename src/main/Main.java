
package main;

import file.FileLoader;
import query.QueryExecutor;
import query.QueryParameters;
import query.QueryResult;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that contains the main method that call the menu
 * @author Vladimir Aguilar
 * @author Mariana Abellan
 */
public class Main {
    public static void main(String[] args) throws IOException {
        new Menu();
    }
}
