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
import parser.*;
import validation.ValidationService;

import java.util.*;

/**
 * Class that executes the queries
 * @author Vladimir Aguilar
 * @author Mariana Abellan
 * Creation Date: 24/3/2017
 */
public class QueryExecutor {
    private FileLoader fileLoader;
    private ValidationService validation;
    private SingleOperation singleOperation;
    private Parser parser;

    public QueryExecutor(FileLoader fileLoader) {
        this.fileLoader = fileLoader;
        this.validation = new ValidationService();
    }

    /**
     * Returns the results of a Single Query
     * @param queryParameters QueryParameters
     * @return QueryResult
     * */
    public QueryResult getSingleQuery(QueryParameters queryParameters){
        int columnToFilter = queryParameters.getColumn()-1;
        boolean correctParams = this.isParameterDataType(fileLoader.getColumnsTypes().get(columnToFilter),
                                queryParameters.getParameters());
        boolean correct;
        String[] rowData;
        QueryResult result = null;
        List parameters;
        LinkedList<Integer> indexResult;
        this.setSingleOpParser(fileLoader.getColumnsTypes().get(columnToFilter));
        if(correctParams){
            parameters = new LinkedList();
            result = new QueryResult();
            for(Object s : queryParameters.getParameters()){
                parameters.add(parser.parse((String)s));
            }
            indexResult = singleOperation.makeSingleOperation(queryParameters.getOperation(),parameters,
                          this.fileLoader.getSortedData().get(columnToFilter));
            this.fillResult(indexResult,result);
        }
        return result;
    }

    /**
     * Returns the results of a compound Query
     * @param queryParameters List<QueryParameters>
     * @param logicalOp int
     * @return QueryResult
     * */
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
        Iterator<SingleOperation> itFilters;
        List<SingleOperation> filters = new ArrayList<>();
        List<Parser> parsers = new ArrayList<>();
        do{
            queryParameter = itParams.next();
            columnToFilter = queryParameter.getColumn()-1;
            correctParams = this.isParameterDataType(fileLoader.getColumnsTypes().get(columnToFilter),
                    queryParameter.getParameters());
            this.setSingleOpParser(fileLoader.getColumnsTypes().get(columnToFilter));
            filters.add(this.singleOperation);
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
            result1 = filters.get(0).makeSingleOperation(queryParameter.getOperation(),
                    queryParameter.getParameters(),fileLoader.getSortedData().get(queryParameter.getColumn()-1));
            queryParameter = queryParameters.get(1);
            result2 = filters.get(1).makeSingleOperation(queryParameter.getOperation(),
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

    /**
     * Fill the result parameter
     * @param indexResult LinkedList<Integer>
     * @param result QueryResult
     * */
    private void fillResult(LinkedList<Integer> indexResult, QueryResult result){
        if(indexResult!=null){
            for(Integer i : indexResult){
                result.addRow(fileLoader.getAllData().get(i));
            }
        }
    }

    /**
     * Validates that the parameters inserted by the user are right
     * @param dataType String
     * @param parameters List<String
     * @return boolean
     * */
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

    /**
     * Set the values for the parser and the type of singleOperation
     * @param dataType String
     * */
    private void setSingleOpParser(String dataType){
        switch (dataType){
            case "String":
                singleOperation = new SingleOperation<String>();
                parser = new StringParser();
                break;
            case "int":
                singleOperation = new SingleOperation<Integer>();
                parser = new IntegerParser();
                break;
            case "double":
                singleOperation = new SingleOperation<Double>();
                parser = new DoubleParser();
                break;
            case "date":
                singleOperation = new SingleOperation<Date>();
                parser = new DateParser();
                break;
            case "boolean":
                singleOperation = new SingleOperation<Boolean>();
                parser = new BooleanParser();
                break;
            default:
                singleOperation = null;
                parser = null;
                break;
        }
    }

}
