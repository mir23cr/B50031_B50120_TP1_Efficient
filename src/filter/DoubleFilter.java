package filter;

import java.util.List;

/**
 * Created by vladimir.aguilar on 24/3/2017.
 */
public class DoubleFilter extends FilteringOperation<Double> {

    @Override
    protected Boolean isGreater(Double comparator, Double toCompare) {
        return toCompare > comparator;
    }

    @Override
    protected Boolean isSmaller(Double comparator, Double toCompare) {
        return toCompare < comparator;
    }

    @Override
    protected Boolean isGreaterEqual(Double comparator, Double toCompare) {
        return toCompare >= comparator;
    }

    @Override
    protected Boolean isSmallerEqual(Double comparator, Double toCompare) {
        return toCompare <= comparator;
    }

    @Override
    protected Boolean isInRank(List<Double> comparator, Double toCompare) {
        return this.isGreaterEqual(comparator.get(0), toCompare) && this.isSmallerEqual(comparator.get(1), toCompare);
    }

}
