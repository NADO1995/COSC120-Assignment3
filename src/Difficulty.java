/**
 * Author: Tshering Dorji
 * Unit: COSC120
 * Project: DiscoverDruk - Bhutan Experience Finder
 * GitHub: https://github.com/NADO1995/COSC120-Assignment3.git
 *
 * This enum was developed with reference to the COSC120 lecture notes,
 * tutorial materials, and sample code provided for the assignment.
 *
 * AI assistance:
 * ChatGPT was used to help understand the assignment requirements,
 * review the enum structure, and improve code clarity and documentation.
 */
public enum Difficulty {

    EASY,
    MODERATE,
    CHALLENGING;

    /**
     * Returns a readable name for each difficulty level.
     *
     * @return the readable difficulty level
     */
    @Override
    public String toString() {

        return switch (this) {
            case EASY -> "Easy";
            case MODERATE -> "Moderate";
            case CHALLENGING -> "Challenging";
        };
    }
}