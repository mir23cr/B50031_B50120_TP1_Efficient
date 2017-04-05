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
            for(Object s : queryParameters.getParameters()){
                parameters.add(parser.parse((String)s));
            }
            indexResult = filter.makeSingleOperation(queryParameters.getOperation(),parameters,
                          this.fileLoader.getSortedData().get(columnToFilter));
            this.fillResult(indexResult,result);
        }
        return result;
    }

    public QueryResult getCompoundQuery(List<QueryParameters> queryParameters, int logicalOp){
        int columnToFilter;
        boolean correctParams;
        QueryResult result = null;
        List parameters;
        QueryParameters queryParameter;
        Iterator<QueryParameters> itParams = queryParameters.iterator();
        LinkedList<Integer> result1;
        LinkedList<Integer> result2;
        LinkedList<Integer> indexResult = new LinkedList<>();

        Iterator<Parser> itParser;
        Iterator<FilteringOperation> itFilters;
        filters = new ArrayList<>();
        parsers = new ArrayList<>();
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
                for(Object s : queryParameter.getParameters()){
                    parameters.add(parser.parse((String) s));
                }
                queryParameter.setParameters(parameters);
            }
        }while(itParams.hasNext() && correctParams);

        if(correctParams){
            result = new QueryResult();
            queryParameter = queryParameters.get(0);
            result1 = this.filters.get(0).makeSingleOperation(queryParameter.getOperation(),
                    queryParameter.getParameters(),fileLoader.getSortedData().get(queryParameter.getColumn()-1));
            queryParameter = queryParameters.get(1);
            result2 = this.filters.get(1).makeSingleOperation(queryParameter.getOperation(),
                    queryParameter.getParameters(),fileLoader.getSortedData().get(queryParameter.getColumn()-1));
            switch(logicalOp){
                case 1:
                    if(result2!=null && result1!=null){
                        for(Integer i : result1){
                            if(result2.contains(i)){
                                indexResult.add(i);
                            }
                        }
                    }
                    break;
                case 2:
                    indexResult = result1;
                    if(result1 != null && result2 != null){
                        for (Integer i : result2){
                            if(!indexResult.contains(i)){
                                indexResult.add(i);
                            }
                        }
                    }
                    break;
            }
            this.fillResult(indexResult,result);
        }
        return result;
    }

    private void fillResult(LinkedList<Integer> indexResult, QueryResult result){
        if(indexResult!=null){
            for(Integer i : indexResult){
                result.addRow(fileLoader.getAllData().get(i));
            }
        }
    }

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
