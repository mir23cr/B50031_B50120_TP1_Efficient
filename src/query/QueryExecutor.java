/*
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
package query;

import file.FileLoader;
import filter.*;
import parser.*;
import validation.ValidationService;

import java.util.*;

/**
 * Created by vladimir.aguilar on 24/3/2017 .
 */
public class QueryExecutor {
    private FileLoader fileLoader;
    private ValidationService validation;
    private FilteringOperation filter;
    private Parser parser;
    private List<Parser> parsers;
    private List<FilteringOperation> filters;

    public QueryExecutor(FileLoader fileLoader) {
        this.fileLoader = fileLoader;
        this.validation = new ValidationService();
    }

    public QueryResult getSingleQuery(QueryParameters queryParameters){
        int columnToFilter = queryParameters.getColumn()-1;
        boolean correctParams = this.isParameterDataType(fileLoader.getColumnsTypes().get(columnToFilter),
                                queryParameters.getParameters());
        boolean correct;
        String[] rowData;
        QueryResult result = null;
        List parameters;
        LinkedList<Integer> indexResult;
        this.setFilterParser(fileLoader.getColumnsTypes().get(columnToFilter));
        if(correctParams){
            parameters = new LinkedList();
            result = new QueryResult();
            for(String s : queryParameters.getParameters()){
                parameters.add(parser.parse(s));
            }
            indexResult = filter.makeSingleOperation(queryParameters.getOperation(),parameters,
                          this.fileLoader.getSortedData().get(columnToFilter));
            for(Integer i : indexResult){
                result.addRow(fileLoader.getAllData().get(i));
            }
        }
        return result;
    }

    /*public QueryResult getCompoundQuery(List<QueryParameters> queryParameters, int logicalOp){
        int columnToFilter;
        boolean correctParams;
        boolean correct;
        String[] rowData;
        QueryResult result = null;
        List parameters;
        QueryParameters queryParameter;
        Iterator<QueryParameters> itParams = queryParameters.iterator();

        Iterator<Parser> itParser;
        Iterator<FilteringOperation> itFilters;
        filters = new LinkedList<>();
        parsers = new LinkedList<>();
        do{
            queryParameter = itParams.next();
            columnToFilter = queryParameter.getColumn()-1;
            correctParams = this.isParameterDataType(fileLoader.getColumnsTypes().get(columnToFilter),
                    queryParameter.getParameters());
            this.setFilterParser(fileLoader.getColumnsTypes().get(columnToFilter));
            filters.add(this.filter);
            parsers.add(this.parser);
            if(correctParams){
                parameters = new LinkedList();
                result = new QueryResult();
                for(String s : queryParameter.getParameters()){
                    parameters.add(parser.parse(s));
                }
                queryParameter.setParameters(parameters);
            }
        }while(itParams.hasNext() && correctParams);

        if(correctParams){
            fileLoader.restartCurrent();
            rowData = fileLoader.getRow();
            result = new QueryResult();
            switch(logicalOp){
                case 1:
                    while(rowData != null){
                        correct = true;
                        itParams = queryParameters.iterator();
                        itFilters = filters.iterator();
                        itParser = parsers.iterator();
                        while (correct && itParams.hasNext()){
                            queryParameter = itParams.next();
                            columnToFilter = queryParameter.getColumn()-1;
                            filter = itFilters.next();
                            parser = itParser.next();
                            correct = correct && filter.makeSingleOperation(queryParameter.getOperation(),
                                    queryParameter.getParameters(),this.parser.parse(rowData[columnToFilter]));
                        }
                        if(correct){
                            result.addRow(rowData);
                        }
                        rowData = fileLoader.getRow();
                    }
                    break;
                case 2:
                    while(rowData != null){
                        correct = false;
                        itParams = queryParameters.iterator();
                        while (!correct && itParams.hasNext()){
                            queryParameter = itParams.next();
                            columnToFilter = queryParameter.getColumn()-1;
                            this.setFilterParser(fileLoader.getColumnsTypes().get(columnToFilter));
                            correct = correct || filter.makeSingleOperation(queryParameter.getOperation(),
                                    queryParameter.getParameters(),this.parser.parse(rowData[columnToFilter]));
                        }
                        if(correct){
                            result.addRow(rowData);
                        }
                        rowData = fileLoader.getRow();
                    }
                    break;
            }
        }

        return result;
    }*/


    private boolean isParameterDataType(String dataType, List<String> parameters){
        boolean result = true;
        Iterator<String> it = parameters.iterator();
        String parameter;

        switch (dataType){
            case "String":
                while(it.hasNext() && result){
                    parameter = it.next();
                    result = validation.isString(parameter);
                }
                break;
            case "int":
                while( it.hasNext() && result){
                    parameter = it.next();
                    result = validation.isInt(parameter);
                }
                break;
            case "double":
                while( it.hasNext() && result){
                    parameter = it.next();
                    result = validation.isDouble(parameter);
                }
                break;
            case "date":
                while( it.hasNext() && result){
                    parameter = it.next();
                    result = validation.isDate(parameter);
                }
                break;
            case "boolean":
                while( it.hasNext() && result){
                    parameter = it.next();
                    result = validation.isBool(parameter);
                }
                break;
            default:
                result = false;
                break;
        }
        return result;
    }

    private void setFilterParser(String dataType){
        switch (dataType){
            case "String":
                filter = new FilteringOperation<String>();
                parser = new StringParser();
                break;
            case "int":
                filter = new FilteringOperation<Integer>();
                parser = new IntegerParser();
                break;
            case "double":
                filter = new FilteringOperation<Double>();
                parser = new DoubleParser();
                break;
            case "date":
                filter = new FilteringOperation<Date>();
                parser = new DateParser();
                break;
            case "boolean":
                filter = new FilteringOperation<Boolean>();
                parser = new BooleanParser();
                break;
            default:
                filter = null;
                parser = null;
                break;
        }
    }

}
