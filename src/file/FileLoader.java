package file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Created by vladimir.aguilar on 24/3/2017 .
 */
public class FileLoader {
    private String address;
    private ArrayList<String> columnsTypes;
    private ArrayList<String> columnsNames;
    private BufferedReader currentLine;

    public FileLoader(String address) throws IOException {
        try{
            String[] values;
            this.address = address;
            this.columnsNames = new ArrayList<String>();
            this.columnsTypes = new ArrayList<String>();
            BufferedReader read = new BufferedReader(new FileReader(address));
            String line = read.readLine();
            String line2 = read.readLine();

            values = line.split("\\s*,\\s*");
            columnsNames.addAll(Arrays.asList(values));

            values = line2.split("\\s*,\\s*");
            columnsTypes.addAll(Arrays.asList(values));

            this.currentLine = read;

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
