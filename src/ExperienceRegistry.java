import java.util.ArrayList;
import java.util.List;

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
 * review the collection and search logic, and improve code clarity
 * and documentation.
 */
public class ExperienceRegistry {

    private final List<Experience> experiences;

    /**
     * Creates an empty registry for storing experiences.
     */
    public ExperienceRegistry() {
        experiences = new ArrayList<>();
    }

    /**
     * Adds an experience to the registry.
     *
     * @param experience the experience to add
     */
    public void addExperience(Experience experience) {
        experiences.add(experience);
    }

    /**
     * Returns a copy of all experiences stored in the registry.
     * A copy is returned so the original list cannot be changed
     * from outside this class.
     *
     * @return a copy of the experience list
     */
    public List<Experience> getExperiences() {
        return new ArrayList<>(experiences);
    }

    /**
     * Finds all experiences that match the user's dream experience.
     *
     * @param dream the user's search requirements
     * @return a list of matching experiences
     */
    public List<Experience> findMatchingExperiences(
            DreamExperience dream) {

        List<Experience> matches =
                new ArrayList<>();

        for (Experience experience : experiences) {

            if (experience.matches(dream)) {
                matches.add(experience);
            }
        }

        return matches;
    }

    /**
     * Finds an experience using its unique ID.
     *
     * @param id the experience ID
     * @return the matching experience, or null if it is not found
     */
    public Experience findById(int id) {

        for (Experience experience : experiences) {

            if (experience.getId() == id) {
                return experience;
            }
        }

        return null;
    }
}