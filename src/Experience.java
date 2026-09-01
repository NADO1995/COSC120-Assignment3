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
 * review and debug code, and improve code clarity and documentation.
 */
public class Experience {

    private final int id;
    private final String name;
    private final int minimumAge;
    private final double durationHours;
    private final float rating;
    private final boolean equipmentIncluded;
    private final String description;
    private final String specialNote;

    private final DreamExperience properties;

    /**
     * Creates an Experience object with its unique information
     * and searchable properties.
     *
     * @param id unique experience ID
     * @param name name of the experience
     * @param minimumAge minimum age required
     * @param durationHours duration in hours
     * @param rating experience rating
     * @param equipmentIncluded whether equipment is included
     * @param description description of the experience
     * @param specialNote additional information
     * @param properties searchable properties of the experience
     */
    public Experience(
            int id,
            String name,
            int minimumAge,
            double durationHours,
            float rating,
            boolean equipmentIncluded,
            String description,
            String specialNote,
            DreamExperience properties) {

        this.id = id;
        this.name = name;
        this.minimumAge = minimumAge;
        this.durationHours = durationHours;
        this.rating = rating;
        this.equipmentIncluded = equipmentIncluded;
        this.description = description;
        this.specialNote = specialNote;
        this.properties = properties;
    }

    /**
     * Returns the experience ID.
     *
     * @return experience ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the experience name.
     *
     * @return experience name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the minimum age required.
     *
     * @return minimum age
     */
    public int getMinimumAge() {
        return minimumAge;
    }

    /**
     * Returns the experience duration.
     *
     * @return duration in hours
     */
    public double getDurationHours() {
        return durationHours;
    }

    /**
     * Returns the experience rating.
     *
     * @return rating
     */
    public float getRating() {
        return rating;
    }

    /**
     * Returns whether equipment is included.
     *
     * @return true if equipment is included
     */
    public boolean isEquipmentIncluded() {
        return equipmentIncluded;
    }

    /**
     * Returns the experience description.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the special note.
     *
     * @return special note
     */
    public String getSpecialNote() {
        return specialNote;
    }

    /**
     * Returns the searchable properties of this experience.
     *
     * @return DreamExperience containing the searchable properties
     */
    public DreamExperience getProperties() {
        return properties;
    }

    /**
     * Checks whether this experience matches the user's dream experience.
     *
     * @param dream user's search requirements
     * @return true if the experience matches
     */
    public boolean matches(DreamExperience dream) {
        return properties.matches(dream);
    }

    /**
     * Returns the full experience information in a readable format.
     *
     * @return formatted experience information
     */
    public String getExperienceInformation() {
        return "Experience: " + name
                + "\nID: " + id
                + "\nMinimum age: " + minimumAge
                + "\nDuration: " + durationHours + " hours"
                + "\nRating: " + rating + "/5"
                + "\nEquipment included: "
                + (equipmentIncluded ? "Yes" : "No")
                + "\n" + properties.getInfo()
                + "\nDescription: " + description
                + "\nSpecial note: " + specialNote;
    }

    /**
     * Returns the name of the experience.
     *
     * @return experience name
     */
    @Override
    public String toString() {
        return name;
    }
}