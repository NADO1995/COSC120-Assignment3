public class Experience {

    private int id;
    private String name;
    private ExperienceType type;
    private Dzongkhag dzongkhag;
    private Difficulty difficulty;
    private double price;
    private float rating;
    private boolean guideIncluded;
    private boolean familyFriendly;

    public Experience(
            int id,
            String name,
            ExperienceType type,
            Dzongkhag dzongkhag,
            Difficulty difficulty,
            double price,
            float rating,
            boolean guideIncluded,
            boolean familyFriendly) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.dzongkhag = dzongkhag;
        this.difficulty = difficulty;
        this.price = price;
        this.rating = rating;
        this.guideIncluded = guideIncluded;
        this.familyFriendly = familyFriendly;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ExperienceType getType() {
        return type;
    }

    public Dzongkhag getDzongkhag() {
        return dzongkhag;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public double getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }

    public boolean isGuideIncluded() {
        return guideIncluded;
    }

    public boolean isFamilyFriendly() {
        return familyFriendly;
    }

    public String getExperienceInformation() {
        return "Experience: " + name
                + "\nLocation: " + dzongkhag
                + "\nType: " + type
                + "\nDifficulty: " + difficulty
                + "\nPrice: Nu. " + price
                + "\nRating: " + rating + "/5"
                + "\nGuide included: " + (guideIncluded ? "Yes" : "No")
                + "\nFamily friendly: " + (familyFriendly ? "Yes" : "No");
    }

    @Override
    public String toString() {
        return name;
    }
}