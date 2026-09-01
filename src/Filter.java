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