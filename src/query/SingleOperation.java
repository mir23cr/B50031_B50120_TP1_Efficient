/*
* Comparator: Is the parameter of the user
* toCompare: Is the data in the csv File* Identification of the operations:
* 1: Equal
* 2: Different
* 3: Greater
* 4: Smaller
* 5: Greater or Equal
* 6: Smaller or Equal
* 7: Rank
* */
package query;

import java.util.*;

/**
 * Class that make all the single operations
 * @author Vladimir Aguilar
 * @author Mariana Abellan
 */
public class SingleOperation<T>{

    /**
     * Return a list of the index that match with a single operation
     * @param opID int
     * @param comparator List<T> The params that the user inserts
     * @param toCompare TreeMap Contains the elements that we want to match
     * @return ArrayList<ArrayList<String>>
     * */
    public ArrayList<ArrayList<String>> makeSingleOperation(int opID, List<T> comparator, TreeMap<T,
            ArrayList<ArrayList<String>>> toCompare){

        ArrayList<ArrayList<String>> result;
        switch (opID){
            case 1:
                result = this.getEqualIndex(comparator.get(0), toCompare);
                break;
            case 2:
                result = this.getDifferentIndex(comparator.get(0), toCompare);
                break;
            case 3:
                result = this.getGreaterIndex(comparator.get(0), toCompare);
                break;
            case 4:
                result = this.getSmallerIndex(comparator.get(0), toCompare);
                break;
            case 5:
                result = this.getGreaterEqualIndex(comparator.get(0), toCompare);
                break;
            case 6:
                result = this.getSmallerEqualIndex(comparator.get(0), toCompare);
                break;
            case 7:
                result = this.getInRankIndex(comparator, toCompare);
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    /**
     * Makes the indexes that match with the equal operation
     * @param comparator
     * @param toCompare
     * @return ArrayList<ArrayList<String>>
     * */
    private ArrayList<ArrayList<String>> getEqualIndex(T comparator, TreeMap<T, ArrayList<ArrayList<String >>> toCompare){
        return toCompare.get(comparator);
    }

    /**
     * Makes the indexes that match with the different operation
     * @param comparator
     * @param toCompare
     * @return ArrayList<ArrayList<String>>
     * */
    private ArrayList<ArrayList<String>> getDifferentIndex(T comparator, TreeMap<T, ArrayList<ArrayList<String>>> toCompare){
        SortedMap<T,ArrayList<ArrayList<String>>> less = toCompare.headMap(comparator);
        SortedMap<T,ArrayList<ArrayList<String>>> greatest = toCompare.tailMap(comparator,false);
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        Collection<ArrayList<ArrayList<String>>> l1 = greatest.values();
        Collection<ArrayList<ArrayList<String>>> l2 = less.values();
        for(ArrayList<ArrayList<String>> li : l1){
            result.addAll(li);
        }
        for(ArrayList<ArrayList<String>> li : l2){
            result.addAll(li);
        }
        return result;
    }

    /**
     * Makes the indexes that match with the greater operation
     * @param comparator
     * @param toCompare
     * @return ArrayList<ArrayList<String>>
     * */
    private ArrayList<ArrayList<String>> getGreaterIndex(T comparator, TreeMap<T, ArrayList<ArrayList<String>>> toCompare){
        SortedMap<T,ArrayList<ArrayList<String>>> greatest = toCompare.tailMap(comparator,false);
        Collection<ArrayList<ArrayList<String>>> l1 = greatest.values();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for(ArrayList<ArrayList<String>> li : l1){
            result.addAll(li);
        }
        return result;
    }

    /**
     * Makes the indexes that match with the smaller operation
     * @param comparator
     * @param toCompare
     * @return ArrayList<ArrayList<String>>
     * */
    private ArrayList<ArrayList<String>> getSmallerIndex(T comparator, TreeMap<T, ArrayList<ArrayList<String>>> toCompare){
        SortedMap<T,ArrayList<ArrayList<String>>> greatest = toCompare.headMap(comparator,false);
        Collection<ArrayList<ArrayList<String>>> l1 = greatest.values();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for(ArrayList<ArrayList<String>> li : l1){
            result.addAll(li);
        }
        return result;
    }

    /**
     * Makes the indexes that match with the greater or equal operation
     * @param comparator
     * @param toCompare
     * @return ArrayList<ArrayList<String>>
     * */
    private ArrayList<ArrayList<String>> getGreaterEqualIndex(T comparator,
                                          TreeMap<T, ArrayList<ArrayList<String>>> toCompare){
        ArrayList<ArrayList<String>> result = this.getGreaterIndex(comparator,toCompare);
        result.addAll(this.getEqualIndex(comparator,toCompare));
        return result;
    }

    /**
     * Makes the indexes that match with the smaller or equal operation
     * @param comparator
     * @param toCompare
     * @return ArrayList<ArrayList<String>>
     * */
    private ArrayList<ArrayList<String>> getSmallerEqualIndex(T comparator,
                                                   TreeMap<T, ArrayList<ArrayList<String>>> toCompare){
        ArrayList<ArrayList<String>> result = this.getSmallerIndex(comparator,toCompare);
        result.addAll(this.getEqualIndex(comparator,toCompare));
        return result;

    }

    /**
     * Makes the indexes that match with the rank operation
     * @param comparator List<T> includes the floor and the top
     * @param toCompare
     * @return ArrayList<ArrayList<String>>
     * */
    private ArrayList<ArrayList<String>> getInRankIndex(List<T> comparator,
                                                         TreeMap<T, ArrayList<ArrayList<String>>> toCompare){
        SortedMap<T,ArrayList<ArrayList<String>>> rank = toCompare.subMap(comparator.get(0),true,
                                                                     comparator.get(1),true);
        Collection<ArrayList<ArrayList<String>>> l1 = rank.values();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for(ArrayList<ArrayList<String>> li : l1){
            result.addAll(li);
        }
        return result;
    }
}
