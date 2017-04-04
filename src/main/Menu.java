/*
* ONLY FOR THE MENU
* 1: Equal
* 2: Different
* 3: Greater
* 4: Smaller
* 5: Greater or Equal
* 6: Smaller or Equal
* 7: Rank
* Identification for Compound operations:
* 1: AND
* 2: OR
* */
package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import file.FileLoader;
import query.QueryExecutor;
import query.QueryParameters;
import query.QueryResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by vladimir.aguilar on 24/3/2017 .
 */
public class Menu {

    FileLoader fl;
    Scanner sc;
    public Menu() throws IOException {
        this.runMenu();
    }
    public void runMenu() throws IOException {
        sc = new Scanner(System.in);
        System.out.println("Insert the location of the file: ");
        String address = sc.next();
        System.out.println(address);
        fl = new FileLoader(address);
        BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
        boolean continue1 = true;
        boolean continue2 = true;
        int columnSelection;
        int columnSelection2;
        String option;
        Integer operation;
        Integer operation2;
        Integer operation3;
        Integer logicalOperation;
        //String compare;
        QueryParameters qP;
        QueryParameters qP2;
        QueryResult qR;
        QueryExecutor qE;
        //List<String> parameters;
        //List<String> parameters2;
        List<QueryParameters> qPList;
        qE = new QueryExecutor(fl);
        ArrayList<String> columnsNames = fl.getGetColumnsNames();
        while(continue1) {
            continue2 = true;
            System.out.println("Choose an option: ");
            System.out.println("1) Single Query");
            System.out.println("2) Compound Query");
            System.out.println("3) Exit");
            System.out.print("Option: ");
            option = consola.readLine();
            if (option.equals("1")) {
                columnSelection = this.getColumnSelection(columnsNames);
                while(continue2) {
                    operation = this.getOperationSeleccion();
                        qP = getQueryParameters(columnSelection,operation);
                        if(qP != null) {
                            qR = qE.getSingleQuery(qP);
                            if (qR != null) {
                                System.out.println("Result: ");
                                qR.print();
                            } else {
                                System.out.println("Error.");
                            }
                        }
                        else {
                            continue2 = false;
                        }
                }//End while
            }else if(option.equals("2")){
                    System.out.println("Choose the logical Operation for the compound queries");
                    System.out.println("1) AND");
                    System.out.println("2) OR");
                    logicalOperation = sc.nextInt();
                    columnSelection = this.getColumnSelection(columnsNames);
                    operation2 = this.getOperationSeleccion();
                    qP = getQueryParameters(columnSelection, operation2);
                    columnSelection2 = this.getColumnSelection(columnsNames);
                    operation3 = this.getOperationSeleccion();
                    qP2 = getQueryParameters(columnSelection2, operation3);
                    if (qP != null && qP2 != null) {
                        qPList = new LinkedList<>();
                        qPList.add(qP);
                        qPList.add(qP2);
                        qR = qE.getCompoundQuery(qPList, logicalOperation);
                        if (qR != null) {
                            System.out.println("Result: ");
                            qR.print();
                        } else {
                            System.out.println("Error.");
                        }
                    }
                    else {
                        continue2 = false;
                    }
            }else if(option.equals("3")){
                continue1 = false;
            }else{
            System.out.println("Error: invalid option");
            }
        }
    }
    private QueryParameters getQueryParameters(int columnSelection, int operation){
        List<String> parameters = new LinkedList<>();
        if(operation > 0 && operation < 7) {
            System.out.println("Which is the value you want to compare");
            String compare = sc.next();
            parameters.add(compare);
            return (new QueryParameters(columnSelection, operation, parameters));
        }
        else if(operation == 7){
            System.out.println("Which is the floor");
            String compare = sc.next();
            parameters.add(compare);
            System.out.println("Which is the top");
            compare = sc.next();
            parameters.add(compare);
            return (new QueryParameters(columnSelection, operation, parameters));
        }
        else {
            System.out.println("ERROR");
            return null;
        }
    }
    private int getOperationSeleccion(){
        int op;
        System.out.println("Choose the operation: ");
        System.out.println("1) Equal");
        System.out.println("2) Different");
        System.out.println("3) Greater");
        System.out.println("4) Less");
        System.out.println("5) Greater or equal");
        System.out.println("6) Less or equal");
        System.out.println("7) Rank");
        System.out.println("8) Exit");
        op = sc.nextInt();
        return op;
    }
    private int getColumnSelection(ArrayList<String> columnsNames){
        int index;
        System.out.println("Select the column you want to search:");
        index=1;
        for(String s: columnsNames){
            System.out.println(index + ") " + s);
            index++;
        }
        index = sc.nextInt();
        return index;
    }
}
