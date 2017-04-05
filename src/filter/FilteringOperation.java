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
package filter;

import java.util.*;

public class FilteringOperation <T>{
    public LinkedList<Integer> makeSingleOperation(int opID, List<T> comparator, TreeMap<T,LinkedList<Integer>> toCompare){
        LinkedList<Integer> result;
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

    private LinkedList<Integer> getEqualIndex(T comparator, TreeMap<T, LinkedList<Integer>> toCompare){
        return toCompare.get(comparator);
    }

    private LinkedList<Integer> getDifferentIndex(T comparator, TreeMap<T, LinkedList<Integer>> toCompare){
        SortedMap<T,LinkedList<Integer>> less = toCompare.headMap(comparator);
        SortedMap<T,LinkedList<Integer>> greatest = toCompare.tailMap(comparator,false);
        LinkedList<Integer> result = new LinkedList<>();
        Collection<LinkedList<Integer>> l1 = greatest.values();
        Collection<LinkedList<Integer>> l2 = less.values();
        for(LinkedList<Integer> li : l1){
            result.addAll(li);
        }
        for(LinkedList<Integer> li : l2){
            result.addAll(li);
        }
        return result;
    }

    private LinkedList<Integer> getGreaterIndex(T comparator, TreeMap<T, LinkedList<Integer>> toCompare){
        SortedMap<T,LinkedList<Integer>> greatest = toCompare.tailMap(comparator,false);
        Collection<LinkedList<Integer>> l1 = greatest.values();
        LinkedList<Integer> result = new LinkedList<>();
        for(LinkedList<Integer> li : l1){
            result.addAll(li);
        }
        return result;
    }

    private LinkedList<Integer> getSmallerIndex(T comparator, TreeMap<T, LinkedList<Integer>> toCompare){
        SortedMap<T,LinkedList<Integer>> greatest = toCompare.headMap(comparator,false);
        Collection<LinkedList<Integer>> l1 = greatest.values();
        LinkedList<Integer> result = new LinkedList<>();
        for(LinkedList<Integer> li : l1){
            result.addAll(li);
        }
        return result;
    }

    private LinkedList<Integer> getGreaterEqualIndex(T comparator, TreeMap<T, LinkedList<Integer>> toCompare){
        LinkedList<Integer> result = this.getGreaterIndex(comparator,toCompare);
        result.addAll(this.getEqualIndex(comparator,toCompare));
        return result;
    }

    private LinkedList<Integer> getSmallerEqualIndex(T comparator, TreeMap<T, LinkedList<Integer>> toCompare){
        LinkedList<Integer> result = this.getSmallerIndex(comparator,toCompare);
        result.addAll(this.getEqualIndex(comparator,toCompare));
        return result;

    }

    private LinkedList<Integer> getInRankIndex(List<T> comparator, TreeMap<T, LinkedList<Integer>> toCompare){
        SortedMap<T,LinkedList<Integer>> rank = toCompare.subMap(comparator.get(0),true,
                                                                     comparator.get(1),true);
        Collection<LinkedList<Integer>> l1 = rank.values();
        LinkedList<Integer> result = new LinkedList<>();
        for(LinkedList<Integer> li : l1){
            result.addAll(li);
        }
        return result;
    }
}
