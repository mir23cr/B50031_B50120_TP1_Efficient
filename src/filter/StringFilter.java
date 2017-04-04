package filter;

import java.util.List;
import java.lang.String;
/**
 * Created by Vladimir Aguilar on 24/3/2017.
 */
public class StringFilter extends FilteringOperation<String> {

    @Override
    protected Boolean isGreater(String comparator, String toCompare) {
        return null;
    }

    @Override
    protected Boolean isSmaller(String comparator, String toCompare) {
        return null;
    }

    @Override
    protected Boolean isGreaterEqual(String comparator, String toCompare) {
        return null;
    }

    @Override
    protected Boolean isSmallerEqual(String comparator, String toCompare) {
        return null;
    }

    @Override
    protected Boolean isInRank(List<String> comparator, String toCompare) {
        return null;
    }

}
