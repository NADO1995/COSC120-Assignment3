import java.util.ArrayList;
import java.util.List;

public class ExperienceRegistry {

    private final List<Experience> experiences;

    public ExperienceRegistry() {
        experiences = new ArrayList<>();
    }

    public void addExperience(Experience experience) {
        experiences.add(experience);
    }

    public List<Experience> getExperiences() {
        return new ArrayList<>(experiences);
    }
}