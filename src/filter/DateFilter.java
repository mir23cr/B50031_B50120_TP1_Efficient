package filter;

import java.util.Date;
import java.util.List;

/**
 * Created by Vladimir Aguilar on 24/3/2017.
 */
public class DateFilter extends FilteringOperation<Date> {
    @Override
    protected Boolean isGreater(Date comparator, Date toCompare) {
        return toCompare.after(comparator);
    }

    @Override
    protected Boolean isSmaller(Date comparator, Date toCompare) {
        return toCompare.before(comparator);
    }

    @Override
    protected Boolean isGreaterEqual(Date comparator, Date toCompare) {
        return toCompare.after(comparator) || this.isEqual(comparator,toCompare);
    }

    @Override
    protected Boolean isSmallerEqual(Date comparator, Date toCompare) {
        return toCompare.before(comparator) || this.isEqual(comparator,toCompare);
    }

    @Override
    protected Boolean isInRank(List<Date> comparator, Date toCompare) {

        return this.isGreaterEqual(comparator.get(0), toCompare) && this.isSmallerEqual(comparator.get(1),toCompare);
    }

}
