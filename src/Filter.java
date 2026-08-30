public enum Filter {
    TYPE,
    DZONGKHAG,
    DIFFICULTY,
    MAX_PRICE,
    FAMILY_FRIENDLY,
    GUIDE_INCLUDED,
    FEATURES;

    @Override
    public String toString() {
        return switch (this) {
            case TYPE -> "Experience Type";
            case DZONGKHAG -> "Dzongkhag";
            case DIFFICULTY -> "Difficulty";
            case MAX_PRICE -> "Maximum Price";
            case FAMILY_FRIENDLY -> "Family Friendly";
            case GUIDE_INCLUDED -> "Guide Included";
            case FEATURES -> "Features";
        };
    }
}