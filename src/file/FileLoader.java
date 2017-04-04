package file;

import javafx.util.Pair;
import parser.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;

/**
 * Created by vladimir.aguilar on 24/3/2017 .
 */
public class FileLoader {
    private String address;
    private ArrayList<String> columnsTypes;
    private ArrayList<String> columnsNames;
    private BufferedReader currentLine;
    private ArrayList<String[]> allData;
    private ArrayList<Map<Object,LinkedList<Integer>>> sortedData;
    private List<Parser> parsers;

    public FileLoader(String address) throws IOException {
        try{
            String[] values;
            this.address = address;
            this.columnsNames = new ArrayList<>();
            this.columnsTypes = new ArrayList<>();
            this.allData = new ArrayList<>();
            this.sortedData = new ArrayList<>();
            this.parsers = new ArrayList<>();
            BufferedReader read = new BufferedReader(new FileReader(address));
            String line = read.readLine();
            String line2 = read.readLine();
            Map<Object,LinkedList<Integer>> currentMap;
            LinkedList<Integer> newList;
            LinkedList<Integer> currentList;
            int rowIndex;
            Pair<Map<Object,LinkedList<Integer>>,Parser> p;


            values = line.split("\\s*,\\s*");
            columnsNames.addAll(Arrays.asList(values));

            values = line2.split("\\s*,\\s*");
            columnsTypes.addAll(Arrays.asList(values));

            for(String type : columnsTypes){
                p = this.getMapColumn(type);
                sortedData.add(p.getKey());
                parsers.add(p.getValue());
            }
            System.out.println("TAMAÑO:" + sortedData.size());

            this.currentLine = read;
            values = this.getRow();
            rowIndex = 0;
            while(values != null){
                allData.add(values);
                for(int i =0; i < values.length; i++){
                    currentMap = this.sortedData.get(i);
                    if(!currentMap.containsKey(parsers.get(i).parse(values[i]))){
                        System.out.println(columnsNames.get(i));
                        newList = new LinkedList<Integer>();
                        newList.add(rowIndex);
                        currentMap.put(this.getParsedValue(i,values[i]),newList);
                    }else{
                        System.out.println(columnsNames.get(i));
                        currentMap.get(this.getParsedValue(i,values[i])).add(rowIndex);
                    }
                }
                rowIndex++;
                values = this.getRow();
            }

            Iterator<Map.Entry<Object, LinkedList<Integer>>> iter;
            Map.Entry<Object, LinkedList<Integer>> entry;
            for(Map m : this.sortedData){
                iter = m.entrySet().iterator();
                while (iter.hasNext()) {
                    entry = iter.next();
                    System.out.print(entry.getKey().toString() + " ");
                    for(Integer l : entry.getValue()){
                        System.out.println(l + " ");
                    }
                    System.out.println();
                }
            }


            /*for(String[] s : allData){
                for(String s2 : s){
                    System.out.print(s2 + " ");
                }
                System.out.println();
            }*/
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void restartCurrent(){
        try {
            this.currentLine.close();
            this.currentLine = new BufferedReader(new FileReader(this.address));
            this.currentLine.readLine();
            this.currentLine.readLine();
        } catch (Exception e) {
            e.printStackTrace();
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

    public Pair<Map<Object,LinkedList<Integer>>,Parser> getMapColumn(String typeColumn){
        Map newMap = null;
        Parser p = null;
        Pair<Map<Object,LinkedList<Integer>>,Parser> pair;
        switch (typeColumn){
            case "String":
                newMap = new HashMap<String,LinkedList<Integer>>();
                p = new StringParser();
                break;
            case "int":
                newMap = new HashMap<Integer,LinkedList<Integer>>();
                p = new IntegerParser();
                break;
            case "double":
                newMap = new HashMap<Double,LinkedList<Integer>>();
                p = new DoubleParser();
                break;
            case "date":
                newMap = new HashMap<Date,LinkedList<Integer>>();
                p = new DateParser();
                break;
            case "bool":
                newMap = new HashMap<Boolean,LinkedList<Integer>>();
                p = new BooleanParser();
                break;
        }
        pair = new Pair<>(newMap,p);
        return pair;
    }

    public Object getParsedValue(int index, String value){
        return parsers.get(index).parse(value);
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
