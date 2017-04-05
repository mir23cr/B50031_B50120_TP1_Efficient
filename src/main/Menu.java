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

    private FileLoader fl;
    private Scanner consoleNumbers;
    private Scanner consoleStrings;
    public Menu() throws IOException {
        this.runMenu();
    }
    public void runMenu() throws IOException {
        consoleNumbers = new Scanner(System.in);
        consoleStrings = new Scanner(System.in);
        System.out.println("Insert the location of the file: ");
        String address = consoleStrings.nextLine();
        fl = new FileLoader();
        while (!fl.init(address)){
            System.out.println("Insert the location of the file: ");
            address = consoleStrings.nextLine();
        }
        boolean continue1 = true;
        int columnSelection;
        int columnSelection2;
        String option;
        Integer operation;
        Integer operation2;
        Integer operation3;
        Integer logicalOperation;
        QueryParameters qP;
        QueryParameters qP2;
        QueryResult qR;
        QueryExecutor qE;
        List<QueryParameters> qPList;
        qE = new QueryExecutor(fl);
        ArrayList<String> columnsNames = fl.getGetColumnsNames();
        while(continue1) {
            System.out.println("Choose an option: ");
            System.out.println("1) Single Query");
            System.out.println("2) Compound Query");
            System.out.println("3) Exit");
            System.out.print("Option: ");
            option = consoleStrings.nextLine();
            if (option.equals("1")) {
                columnSelection = this.getColumnSelection(columnsNames);
                if(columnSelection != -1){
                    operation = this.getOperationSelection(columnSelection);
                    if(operation >= 1 && operation <=7){
                        qP = getQueryParameters(columnSelection,operation);
                        if(qP != null) {
                            qR = qE.getSingleQuery(qP);
                            if (qR != null) {
                                System.out.println("Result: ");
                                qR.print();
                            } else {
                                System.out.println("You enter a invalid value.");
                            }
                        }
                    }
                }
            }else if(option.equals("2")){
                    System.out.println("Choose the logical Operation for the compound queries");
                    System.out.println("1) AND");
                    System.out.println("2) OR");
                    System.out.println("3) Exit");
                    System.out.print("Option: ");
                    logicalOperation = this.consoleNumbers.nextInt();
                    if(logicalOperation == 1 || logicalOperation == 2){
                        columnSelection = this.getColumnSelection(columnsNames);
                        if(columnSelection != -1){
                            operation2 = this.getOperationSelection(columnSelection);
                            if(operation2 >= 1 && operation2 <=7){
                                qP = getQueryParameters(columnSelection, operation2);
                                columnSelection2 = this.getColumnSelection(columnsNames);
                                if(columnSelection2 != -1){
                                    operation3 = this.getOperationSelection(columnSelection2);
                                    if(operation3 >= 1 && operation3 <=7){
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
                                    }
                                }

                            }
                        }
                    }
            }else if(option.equals("3")){
                continue1 = false;
            }else{
            System.out.println("Error: invalid option");
            }
        }
    }
    private QueryParameters getQueryParameters(int columnSelection, int operation) throws IOException {
        List<String> parameters = new LinkedList<>();
        if(operation > 0 && operation < 7) {
            System.out.println("Which is the value you want to compare");
            String compare = consoleStrings.nextLine();
            parameters.add(compare);
            return (new QueryParameters(columnSelection, operation, parameters));
        }
        else if(operation == 7){
            System.out.println("Which is the floor");
            String compare = consoleStrings.nextLine();
            parameters.add(compare);
            System.out.println("Which is the top");
            compare = consoleStrings.nextLine();
            parameters.add(compare);
            return (new QueryParameters(columnSelection, operation, parameters));
        }
        else {
            return null;
        }
    }
    private int getOperationSelection(int columSelection) throws IOException {
        int op;
        String typeColumn = fl.getColumnsTypes().get(columSelection-1);
        System.out.println("Choose the operation: ");
        System.out.println("1) Equal");
        System.out.println("2) Different");
        if(!typeColumn.equals("String") && !typeColumn.equals("boolean")){
            System.out.println("3) Greater");
            System.out.println("4) Less");
            System.out.println("5) Greater or equal");
            System.out.println("6) Less or equal");
            System.out.println("7) Rank");
            System.out.println("8) Exit");
            System.out.print("Option: ");
            op = consoleNumbers.nextInt();
        }else{
            System.out.println("3) Exit");
            System.out.print("Option: ");
            op = consoleNumbers.nextInt();
            if(op==3) {
                op = 8;
            }
        }
        return op;
    }
    private int getColumnSelection(ArrayList<String> columnsNames) throws IOException {
        int index;
        System.out.println("Select the column you want to search:");
        index=1;
        for(String s: columnsNames){
            System.out.println(index + ") " + s);
            index++;
        }
        System.out.println(index + ") Exit");
        System.out.println("Option:");
        index = consoleNumbers.nextInt();
        if(index > columnsNames.size()){
            index = -1;
        }
        return index;
    }

}
