import java.util.HashMap;
import java.util.Map;

public class DreamExperience {

    private final Map<Filter, Object> criteria;

    public DreamExperience() {
        criteria = new HashMap<>();
    }

    public void addCriterion(Filter filter, Object value) {
        criteria.put(filter, value);
    }

    public Object getCriterion(Filter filter) {
        return criteria.get(filter);
    }

    public boolean hasCriterion(Filter filter) {
        return criteria.containsKey(filter);
    }

    public Map<Filter, Object> getCriteria() {
        return new HashMap<>(criteria);
    }

    public void removeCriterion(Filter filter) {
        criteria.remove(filter);
    }

    public void clearCriteria() {
        criteria.clear();
    }

    public boolean isEmpty() {
        return criteria.isEmpty();
    }
}