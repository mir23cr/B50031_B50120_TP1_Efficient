package file;

import javafx.util.Pair;
import parser.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.util.List;

/**
 * Class that charge the file in memory and create the list of TreeMaps
 * @author Vladimir Aguilar
 * @author Mariana Abellan
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
    /**
     * Charge all the data in the Data Structures
     * @param address of the .csv file
     * @return false if the file doesn't exist
     * @return true if the file it's correct
     * */
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
                    p = this.getMapAndParseColumn(type);
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
    /**
     * Get the next row of the file
     * @return an array with the fields of the row
     * */
    private String[] getRow(){
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

    /**
     * Get a Pair with the TreeMap with the correct Parsing and a parser to set data
     * @return a Pair with the TreeMap with the correct Parsing and a parser to set data
     * */
    public Pair<TreeMap<Object,LinkedList<Integer>>,Parser> getMapAndParseColumn(String typeColumn){
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

    /**
     * Get a value parsed
     * @return a parsed value
     * */
    private Object getParsedValue(int index, String value){
        return parsers.get(index).parse(value);
    }

    /**
     * Return a table with all the data
     * @return all the data of the .csv file
     * */
    public ArrayList<String[]> getAllData() {
        return allData;
    }

    /**
     * Return the Arraylist with all the TreeMaps
     * @return an Arraylist with treeMaps
     * */
    public ArrayList<TreeMap<Object, LinkedList<Integer>>> getSortedData() {
        return sortedData;
    }

    /**
     * Get an arrayList with all the columns types of the file
     * @return ArrayList<String>
     * */
    public ArrayList<String> getColumnsTypes() {
        return columnsTypes;
    }

    /**
     * Get an arrayList with all the columns names of the file
     * @return ArrayList<String>
     * */
    public ArrayList<String> getGetColumnsNames() {
        return columnsNames;
    }
}
