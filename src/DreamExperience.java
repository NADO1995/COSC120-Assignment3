import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public boolean matches(DreamExperience dream) {

        for (Map.Entry<Filter, Object> entry
                : dream.criteria.entrySet()) {

            Filter filter = entry.getKey();
            Object wanted = entry.getValue();
            Object actual = criteria.get(filter);

            if (actual == null) {
                return false;
            }

            if (filter == Filter.MAX_PRICE) {

                double actualPrice =
                        ((Number) actual).doubleValue();

                double maximumPrice =
                        ((Number) wanted).doubleValue();

                if (actualPrice > maximumPrice) {
                    return false;
                }

            } else if (filter == Filter.FEATURES) {

                @SuppressWarnings("unchecked")
                Set<String> actualFeatures =
                        (Set<String>) actual;

                @SuppressWarnings("unchecked")
                Set<String> wantedFeatures =
                        (Set<String>) wanted;

                if (!actualFeatures.containsAll(wantedFeatures)) {
                    return false;
                }

            } else if (!actual.equals(wanted)) {
                return false;
            }
        }

        return true;
    }

    public String getInfo() {

        StringBuilder information = new StringBuilder();

        for (Map.Entry<Filter, Object> entry
                : criteria.entrySet()) {

            information.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\n");
        }

        return information.toString();
    }
}