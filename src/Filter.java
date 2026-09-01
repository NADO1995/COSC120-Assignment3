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
 * review the enum structure, and improve code clarity and documentation.
 */
public enum Filter {

    TYPE,
    DZONGKHAG,
    DIFFICULTY,
    MINIMUM_AGE,
    DURATION,
    PRICE,
    RATING,
    GUIDE_INCLUDED,
    FAMILY_FRIENDLY,
    SEASON,
    FEATURES;

    /**
     * Returns a readable name for each filter.
     *
     * @return the readable filter name
     */
    @Override
    public String toString() {

        return switch (this) {
            case TYPE -> "Experience Type";
            case DZONGKHAG -> "Dzongkhag";
            case DIFFICULTY -> "Difficulty";
            case MINIMUM_AGE -> "Minimum Age";
            case DURATION -> "Duration";
            case PRICE -> "Price";
            case RATING -> "Rating";
            case GUIDE_INCLUDED -> "Guide Included";
            case FAMILY_FRIENDLY -> "Family Friendly";
            case SEASON -> "Season";
            case FEATURES -> "Features";
        };
    }
}