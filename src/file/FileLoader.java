package file;

import javafx.util.Pair;
import parser.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.util.List;

/**
 * Created by vladimir.aguilar on 24/3/2017 .
 */
public class FileLoader {
    private String address;
    private ArrayList<String> columnsTypes;
    private ArrayList<String> columnsNames;
    private BufferedReader currentLine;
    private ArrayList<String[]> allData;
    private ArrayList<TreeMap<Object,LinkedList<Integer>>> sortedData;
    private List<Parser> parsers;

    public FileLoader() throws IOException {

    }

    public boolean init(String address){
        try{
            String[] values;
            this.address = address;
            this.columnsNames = new ArrayList<>();
            this.columnsTypes = new ArrayList<>();
            this.allData = new ArrayList<>();
            this.sortedData = new ArrayList<>();
            this.parsers = new ArrayList<>();
            BufferedReader read = new BufferedReader(new FileReader(address));
            if(read.ready()){
                String line = read.readLine();
                String line2 = read.readLine();
                TreeMap<Object,LinkedList<Integer>> currentMap;
                LinkedList<Integer> newList;
                int rowIndex;
                Pair<TreeMap<Object,LinkedList<Integer>>,Parser> p;


                values = line.split("\\s*,\\s*");
                columnsNames.addAll(Arrays.asList(values));

                values = line2.split("\\s*,\\s*");
                columnsTypes.addAll(Arrays.asList(values));

                for(String type : columnsTypes){
                    p = this.getMapColumn(type);
                    sortedData.add(p.getKey());
                    parsers.add(p.getValue());
                }

                System.out.println("Charging data...");

                this.currentLine = read;
                values = this.getRow();
                rowIndex = 0;
                while(values != null){
                    allData.add(values);
                    for(int i =0; i < values.length; i++){
                        currentMap = this.sortedData.get(i);
                        this.getParsedValue(i,values[i]);
                        if(!currentMap.containsKey(parsers.get(i).parse(values[i]))){
                            newList = new LinkedList<Integer>();
                            newList.add(rowIndex);
                            currentMap.put(this.getParsedValue(i,values[i]),newList);
                        }else{
                            currentMap.get(this.getParsedValue(i,values[i])).add(rowIndex);
                        }
                    }
                    rowIndex++;
                    values = this.getRow();
                }
                System.out.println("Complete");
                return true;

            }else {
                System.out.println("Bad path of directory.");
                return false;
            }

        }catch (IOException e){
            System.out.println("Bad path of directory.");
            return false;
        }
    }

    public String[] getRow(){
        try {
            String line = currentLine.readLine();
            if(line != null){
                return line.split("\\s*,\\s*");
            }else{
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Pair<TreeMap<Object,LinkedList<Integer>>,Parser> getMapColumn(String typeColumn){
        TreeMap newMap = null;
        Parser p = null;
        Pair<TreeMap<Object,LinkedList<Integer>>,Parser> pair;
        switch (typeColumn){
            case "String":
                newMap = new TreeMap<String,LinkedList<Integer>>();
                p = new StringParser();
                break;
            case "int":
                newMap = new TreeMap<Integer,LinkedList<Integer>>();
                p = new IntegerParser();
                break;
            case "double":
                newMap = new TreeMap<Double,LinkedList<Integer>>();
                p = new DoubleParser();
                break;
            case "date":
                newMap = new TreeMap<Date,LinkedList<Integer>>();
                p = new DateParser();
                break;
            case "boolean":
                newMap = new TreeMap<Boolean,LinkedList<Integer>>();
                p = new BooleanParser();
                break;
        }
        pair = new Pair<>(newMap,p);
        return pair;
    }

    public Object getParsedValue(int index, String value){
        return parsers.get(index).parse(value);
    }

    public ArrayList<String[]> getAllData() {
        return allData;
    }

    public void setAllData(ArrayList<String[]> allData) {
        this.allData = allData;
    }

    public ArrayList<TreeMap<Object, LinkedList<Integer>>> getSortedData() {
        return sortedData;
    }

    public void setSortedData(ArrayList<TreeMap<Object, LinkedList<Integer>>> sortedData) {
        this.sortedData = sortedData;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getColumnsTypes() {
        return columnsTypes;
    }

    public void setColumnsTypes(ArrayList<String> columnsTypes) {
        this.columnsTypes = columnsTypes;
    }

    public ArrayList<String> getGetColumnsNames() {
        return columnsNames;
    }

    public void setGetColumnsNames(ArrayList<String> getColumnsNames) {
        this.columnsNames = columnsNames;
    }
}
