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

import java.util.List;

public abstract class FilteringOperation <T>{
    public Boolean makeSingleOperation(int opID, List<T> comparator, T toCompare){
        Boolean canMakeOperation;
        switch (opID){
            case 1:
                canMakeOperation = this.isEqual(comparator.get(0), toCompare);
                break;
            case 2:
                canMakeOperation = this.isDifferent(comparator.get(0), toCompare);
                break;
            case 3:
                canMakeOperation = this.isGreater(comparator.get(0), toCompare);
                break;
            case 4:
                canMakeOperation = this.isSmaller(comparator.get(0), toCompare);
                break;
            case 5:
                canMakeOperation = this.isGreaterEqual(comparator.get(0), toCompare);
                break;
            case 6:
                canMakeOperation = this.isSmallerEqual(comparator.get(0), toCompare);
                break;
            case 7:
                canMakeOperation = this.isInRank(comparator, toCompare);
                break;
            default:
                canMakeOperation = false;
                break;
        }
        return canMakeOperation;
    }

    protected Boolean isEqual(T comparator, T toCompare){
        return comparator.equals(toCompare);
    }

    protected Boolean isDifferent(T comparator, T toCompare){
        return !comparator.equals(toCompare);
    }

    abstract protected Boolean isGreater(T comparator, T toCompare);

    abstract protected Boolean isSmaller(T comparator, T toCompare);

    abstract protected Boolean isGreaterEqual(T comparator, T toCompare);

    abstract protected Boolean isSmallerEqual(T comparator, T toCompare);

    abstract protected Boolean isInRank(List<T> comparator, T toCompare);

}
