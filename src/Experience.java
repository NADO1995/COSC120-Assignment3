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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public double getDurationHours() {
        return durationHours;
    }

    public float getRating() {
        return rating;
    }

    public boolean isEquipmentIncluded() {
        return equipmentIncluded;
    }

    public String getDescription() {
        return description;
    }

    public String getSpecialNote() {
        return specialNote;
    }

    public DreamExperience getProperties() {
        return properties;
    }

    public boolean matches(DreamExperience dream) {
        return properties.matches(dream);
    }

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

    @Override
    public String toString() {
        return name;
    }
}