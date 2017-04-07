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
    private ArrayList<String> columnsTypes;
    private ArrayList<String> columnsNames;
    private BufferedReader currentLine;
    private ArrayList<TreeMap<Object,ArrayList<ArrayList<String>>>> sortedData;
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
            this.columnsNames = new ArrayList<>();
            this.columnsTypes = new ArrayList<>();
            this.sortedData = new ArrayList<>();
            this.parsers = new ArrayList<>();
            BufferedReader read = new BufferedReader(new FileReader(address));
            if(read.ready()){
                String line = read.readLine();
                String line2 = read.readLine();
                TreeMap<Object,ArrayList<ArrayList<String>>> currentMap;
                ArrayList<String> row;
                ArrayList<ArrayList<String>> newList;
                Pair<TreeMap<Object,ArrayList<ArrayList<String>>>,Parser> p;


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

                while(values != null){
                    row = new ArrayList<>();
                    for(String s : values){
                        row.add(s);
                    }
                    for(int i =0; i < row.size(); i++){
                        currentMap = this.sortedData.get(i);
                        this.getParsedValue(i,row.get(i));
                        if(!currentMap.containsKey(parsers.get(i).parse(row.get(i)))){
                            newList = new ArrayList<>();
                            newList.add(row);
                            currentMap.put(this.getParsedValue(i,row.get(i)),newList);
                        }else{
                            currentMap.get(this.getParsedValue(i,row.get(i))).add(row);
                        }
                    }
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
    public Pair<TreeMap<Object,ArrayList<ArrayList<String>>>,Parser> getMapAndParseColumn(String typeColumn){
        TreeMap newMap = null;
        Parser p = null;
        Pair<TreeMap<Object,ArrayList<ArrayList<String>>>,Parser> pair;
        switch (typeColumn){
            case "String":
                newMap = new TreeMap<String,ArrayList<ArrayList<String>>>();
                p = new StringParser();
                break;
            case "int":
                newMap = new TreeMap<Integer,ArrayList<ArrayList<String>>>();
                p = new IntegerParser();
                break;
            case "double":
                newMap = new TreeMap<Double,ArrayList<ArrayList<String>>>();
                p = new DoubleParser();
                break;
            case "date":
                newMap = new TreeMap<Date,ArrayList<ArrayList<String>>>();
                p = new DateParser();
                break;
            case "boolean":
                newMap = new TreeMap<Boolean,ArrayList<ArrayList<String>>>();
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
     * Return the Arraylist with all the TreeMaps
     * @return an Arraylist with treeMaps
     * */
    public ArrayList<TreeMap<Object, ArrayList<ArrayList<String>>>> getSortedData() {
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
