package filter;

import java.util.List;
import java.util.Objects;

/**
 * Created by Vladimir Aguilar on 24/3/2017.
 */
public class IntegerFilter extends FilteringOperation<Integer> {

    @Override
    protected Boolean isGreater(Integer comparator, Integer toCompare) {
        return toCompare > comparator;
    }

    @Override
    protected Boolean isSmaller(Integer comparator, Integer toCompare) {
        return toCompare < comparator;
    }

    @Override
    protected Boolean isGreaterEqual(Integer comparator, Integer toCompare) {
        return toCompare >= comparator;
    }

    @Override
    protected Boolean isSmallerEqual(Integer comparator, Integer toCompare) {
        return toCompare <= comparator;
    }

    @Override
    protected Boolean isInRank(List<Integer> comparator, Integer toCompare) {
        return this.isGreaterEqual(comparator.get(0),toCompare) && this.isSmallerEqual(comparator.get(1),toCompare);
    }

}
