import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author: Tshering Dorji
 * Unit: COSC120
 * Project: DiscoverDruk - Bhutan Experience Finder
 * GitHub: https://github.com/NADO1995/COSC120-Assignment3.git
 *
 * This class was developed with reference to the COSC120 lecture notes,
 * tutorial materials, and sample code provided for the assignment.
 *
 * AI assistance:
 * ChatGPT was used to help understand the assignment requirements,
 * review and debug the matching logic, and improve code clarity
 * and documentation.
 */
public class DreamExperience {

    private final Map<Filter, Object> criteria;

    /**
     * Creates an empty DreamExperience.
     */
    public DreamExperience() {
        criteria = new HashMap<>();
    }

    /**
     * Adds a search criterion.
     *
     * @param filter the filter being used
     * @param value the value required for the filter
     */
    public void addCriterion(Filter filter, Object value) {
        criteria.put(filter, value);
    }

    /**
     * Returns the value stored for a filter.
     *
     * @param filter the filter to look for
     * @return the stored value
     */
    public Object getCriterion(Filter filter) {
        return criteria.get(filter);
    }

    /**
     * Checks whether a filter has been added.
     *
     * @param filter the filter to check
     * @return true if the filter is present
     */
    public boolean hasCriterion(Filter filter) {
        return criteria.containsKey(filter);
    }

    /**
     * Returns a copy of the search criteria.
     *
     * @return a copy of the criteria map
     */
    public Map<Filter, Object> getCriteria() {
        return new HashMap<>(criteria);
    }

    /**
     * Removes one criterion.
     *
     * @param filter the filter to remove
     */
    public void removeCriterion(Filter filter) {
        criteria.remove(filter);
    }

    /**
     * Removes all search criteria.
     */
    public void clearCriteria() {
        criteria.clear();
    }

    /**
     * Checks whether there are no search criteria.
     *
     * @return true if the criteria map is empty
     */
    public boolean isEmpty() {
        return criteria.isEmpty();
    }

    /**
     * Compares the stored experience properties with the
     * user's dream experience.
     *
     * @param dream the user's search requirements
     * @return true if all selected criteria match
     */
    public boolean matches(DreamExperience dream) {

        for (Map.Entry<Filter, Object> entry
                : dream.criteria.entrySet()) {

            Filter filter = entry.getKey();
            Object wanted = entry.getValue();
            Object actual = criteria.get(filter);

            if (actual == null) {
                return false;
            }

            if (filter == Filter.PRICE) {

                double actualPrice =
                        ((Number) actual).doubleValue();

                double maximumPrice =
                        ((Number) wanted).doubleValue();

                if (actualPrice > maximumPrice) {
                    return false;
                }

            } else if (filter == Filter.MINIMUM_AGE) {

                int minimumAge =
                        ((Number) actual).intValue();

                int travellerAge =
                        ((Number) wanted).intValue();

                if (travellerAge < minimumAge) {
                    return false;
                }

            } else if (filter == Filter.DURATION) {

                double actualDuration =
                        ((Number) actual).doubleValue();

                double maximumDuration =
                        ((Number) wanted).doubleValue();

                if (actualDuration > maximumDuration) {
                    return false;
                }

            } else if (filter == Filter.RATING) {

                float actualRating =
                        ((Number) actual).floatValue();

                float minimumRating =
                        ((Number) wanted).floatValue();

                if (actualRating < minimumRating) {
                    return false;
                }

            } else if (filter == Filter.SEASON) {

                @SuppressWarnings("unchecked")
                Set<String> actualSeasons =
                        (Set<String>) actual;

                String wantedSeason =
                        wanted.toString().toLowerCase();

                if (!actualSeasons.contains(wantedSeason)) {
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

    /**
     * Returns the stored criteria in a readable format.
     *
     * @return formatted criteria information
     */
    public String getInfo() {

        StringBuilder information =
                new StringBuilder();

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