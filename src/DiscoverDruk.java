import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DiscoverDruk {

    public static ExperienceRegistry loadExperiences(String filename)
            throws IOException {

        ExperienceRegistry registry = new ExperienceRegistry();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filename))) {

            String line = reader.readLine();

            if (line == null) {
                throw new IOException("The experience file is empty.");
            }

            while ((line = reader.readLine()) != null) {

                if (line.isBlank()) {
                    continue;
                }

                String[] data = splitLine(line);

                if (data.length != 16) {
                    System.out.println(
                            "Skipping invalid line: " + line);
                    continue;
                }

                try {

                    int id =
                            Integer.parseInt(data[0].trim());

                    String name =
                            data[1].trim();

                    ExperienceType type =
                            ExperienceType.valueOf(
                                    data[2].trim());

                    Dzongkhag dzongkhag =
                            Dzongkhag.valueOf(
                                    data[3].trim());

                    Difficulty difficulty =
                            Difficulty.valueOf(
                                    data[4].trim());

                    int minimumAge =
                            Integer.parseInt(
                                    data[5].trim());

                    double durationHours =
                            Double.parseDouble(
                                    data[6].trim());

                    double price =
                            Double.parseDouble(
                                    data[7].trim());

                    float rating =
                            Float.parseFloat(
                                    data[8].trim());

                    boolean guideIncluded =
                            parseYesNo(data[9]);

                    boolean equipmentIncluded =
                            parseYesNo(data[10]);

                    boolean familyFriendly =
                            parseYesNo(data[11]);

                    Set<String> seasons =
                            parseList(data[12]);

                    Set<String> features =
                            parseList(data[13]);

                    String description =
                            removeBrackets(data[14]);

                    String specialNote =
                            removeBrackets(data[15]);

                    DreamExperience properties =
                            new DreamExperience();

                    properties.addCriterion(
                            Filter.TYPE,
                            type);

                    properties.addCriterion(
                            Filter.DZONGKHAG,
                            dzongkhag);

                    properties.addCriterion(
                            Filter.DIFFICULTY,
                            difficulty);

                    properties.addCriterion(
                            Filter.MAX_PRICE,
                            price);

                    properties.addCriterion(
                            Filter.GUIDE_INCLUDED,
                            guideIncluded);

                    properties.addCriterion(
                            Filter.FAMILY_FRIENDLY,
                            familyFriendly);

                    properties.addCriterion(
                            Filter.FEATURES,
                            features);

                    Experience experience =
                            new Experience(
                                    id,
                                    name,
                                    minimumAge,
                                    durationHours,
                                    rating,
                                    equipmentIncluded,
                                    description,
                                    specialNote,
                                    properties);

                    registry.addExperience(experience);

                } catch (IllegalArgumentException exception) {

                    System.out.println(
                            "Could not load line: "
                                    + exception.getMessage());
                }
            }
        }

        return registry;
    }

    private static boolean parseYesNo(String text) {

        String value = text.trim();

        if (value.equalsIgnoreCase("yes")) {
            return true;
        }

        if (value.equalsIgnoreCase("no")) {
            return false;
        }

        throw new IllegalArgumentException(
                "Expected yes or no but found: "
                        + text);
    }

    private static Set<String> parseList(String text) {

        Set<String> items =
                new LinkedHashSet<>();

        String value =
                removeBrackets(text);

        if (value.equalsIgnoreCase("NA")
                || value.isBlank()) {

            return items;
        }

        String[] parts =
                value.split(";");

        for (String part : parts) {

            items.add(
                    part.trim().toLowerCase());
        }

        return items;
    }

    private static String removeBrackets(String text) {

        String value =
                text.trim();

        if (value.startsWith("[")
                && value.endsWith("]")) {

            return value.substring(
                    1,
                    value.length() - 1);
        }

        return value;
    }

    private static String[] splitLine(String line) {

        List<String> parts =
                new ArrayList<>();

        StringBuilder currentPart =
                new StringBuilder();

        boolean insideBrackets = false;

        for (char character :
                line.toCharArray()) {

            if (character == '[') {
                insideBrackets = true;
            }

            if (character == ']') {
                insideBrackets = false;
            }

            if (character == ','
                    && !insideBrackets) {

                parts.add(
                        currentPart.toString());

                currentPart.setLength(0);

            } else {

                currentPart.append(character);
            }
        }

        parts.add(
                currentPart.toString());

        return parts.toArray(
                new String[0]);
    }

    public static void main(String[] args) {

        try {

            ExperienceRegistry registry =
                    loadExperiences(
                            "experiences.txt");

            System.out.println(
                    "Experiences loaded: "
                            + registry
                            .getExperiences()
                            .size());

        } catch (IOException exception) {

            System.out.println(
                    "Error loading file: "
                            + exception.getMessage());
        }
    }
}