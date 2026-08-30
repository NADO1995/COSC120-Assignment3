public enum Difficulty {
    EASY,
    MODERATE,
    CHALLENGING;

    @Override
    public String toString() {
        return switch (this) {
            case EASY -> "Easy";
            case MODERATE -> "Moderate";
            case CHALLENGING -> "Challenging";
        };
    }
}