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

    public List<Experience> findMatchingExperiences(DreamExperience dream) {

        List<Experience> matches = new ArrayList<>();

        for (Experience experience : experiences) {
            if (experience.matches(dream)) {
                matches.add(experience);
            }
        }

        return matches;
    }

    public Experience findById(int id) {

        for (Experience experience : experiences) {
            if (experience.getId() == id) {
                return experience;
            }
        }

        return null;
    }
}