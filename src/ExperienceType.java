/**
 * File: ExperienceType.java
 * Author: Tshering Dorji
 * Unit: COSC120
 * Project: DiscoverDruk - Bhutan Experience Finder
 * GitHub: https://github.com/NADO1995/COSC120-Assignment3
 *
 * This enum stores the different categories of experiences
 * available in the DiscoverDruk application.
 *
 * AI Assistance:
 * ChatGPT was used to assist with selecting suitable experience
 * categories and understanding the use of enums and switch expressions.
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
     * Returns a user-friendly name for each experience type.
     *
     * @return formatted name of the experience type
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