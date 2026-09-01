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
public enum ExperienceType {

    TREKKING,
    RAFTING,
    CAMPING,
    CYCLING,
    ARCHERY,
    FESTIVAL,
    HOMESTAY,
    WILDLIFE,
    WELLNESS,
    PHOTOGRAPHY;

    /**
     * Returns a readable name for each experience type.
     *
     * @return the readable experience type
     */
    @Override
    public String toString() {

        return switch (this) {
            case TREKKING -> "Trekking";
            case RAFTING -> "River Rafting";
            case CAMPING -> "Camping";
            case CYCLING -> "Mountain Cycling";
            case ARCHERY -> "Traditional Archery";
            case FESTIVAL -> "Cultural Festival";
            case HOMESTAY -> "Village Homestay";
            case WILDLIFE -> "Wildlife Experience";
            case WELLNESS -> "Wellness Retreat";
            case PHOTOGRAPHY -> "Photography Tour";
        };
    }
}