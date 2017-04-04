package filter;

import java.util.List;

/**
 * Created by Vladimir Aguilar on 24/3/2017.
 */
public class BoolFilter extends FilteringOperation<Boolean> {
    @Override
    protected Boolean isGreater(Boolean comparator, Boolean toCompare) {
        return null;
    }

    @Override
    protected Boolean isSmaller(Boolean comparator, Boolean toCompare) {
        return null;
    }

    @Override
    protected Boolean isGreaterEqual(Boolean comparator, Boolean toCompare) {
        return null;
    }

    @Override
    protected Boolean isSmallerEqual(Boolean comparator, Boolean toCompare) {
        return null;
    }

    @Override
    protected Boolean isInRank(List<Boolean> comparator, Boolean toCompare) {
        return null;
    }

}
