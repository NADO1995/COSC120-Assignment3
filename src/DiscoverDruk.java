import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
                            Filter.MINIMUM_AGE,
                            minimumAge);

                    properties.addCriterion(
                            Filter.DURATION,
                            durationHours);

                    properties.addCriterion(
                            Filter.PRICE,
                            price);

                    properties.addCriterion(
                            Filter.RATING,
                            rating);

                    properties.addCriterion(
                            Filter.GUIDE_INCLUDED,
                            guideIncluded);

                    properties.addCriterion(
                            Filter.FAMILY_FRIENDLY,
                            familyFriendly);

                    properties.addCriterion(
                            Filter.SEASON,
                            seasons);

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

    public static DreamExperience getDreamExperience() {

        Object[] typeOptions =
                createOptions(
                        ExperienceType.values());

        Object[] dzongkhagOptions =
                createOptions(
                        Dzongkhag.values());

        Object[] difficultyOptions =
                createOptions(
                        Difficulty.values());

        String[] yesNoOptions = {
                "Yes",
                "No",
                "I don't mind"
        };

        String[] seasonOptions = {
                "Spring",
                "Summer",
                "Autumn",
                "Winter",
                "I don't mind"
        };

        JComboBox<Object> typeBox =
                new JComboBox<>(typeOptions);

        JComboBox<Object> dzongkhagBox =
                new JComboBox<>(dzongkhagOptions);

        JComboBox<Object> difficultyBox =
                new JComboBox<>(difficultyOptions);

        JTextField ageField =
                new JTextField();

        JTextField durationField =
                new JTextField();

        JTextField priceField =
                new JTextField();

        JTextField ratingField =
                new JTextField();

        JComboBox<String> guideBox =
                new JComboBox<>(yesNoOptions);

        JComboBox<String> familyBox =
                new JComboBox<>(yesNoOptions);

        JComboBox<String> seasonBox =
                new JComboBox<>(seasonOptions);

        typeBox.setSelectedItem("I don't mind");
        dzongkhagBox.setSelectedItem("I don't mind");
        difficultyBox.setSelectedItem("I don't mind");
        guideBox.setSelectedItem("I don't mind");
        familyBox.setSelectedItem("I don't mind");
        seasonBox.setSelectedItem("I don't mind");

        JTextField featuresField =
                new JTextField();

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                11,
                                2,
                                8,
                                8));

        panel.add(
                new JLabel(
                        "Experience Type:"));

        panel.add(typeBox);

        panel.add(
                new JLabel(
                        "Dzongkhag:"));

        panel.add(dzongkhagBox);

        panel.add(
                new JLabel(
                        "Difficulty:"));

        panel.add(difficultyBox);

        panel.add(
                new JLabel(
                        "Your Age:"));

        panel.add(ageField);

        panel.add(
                new JLabel(
                        "Maximum Duration (hours):"));

        panel.add(durationField);

        panel.add(
                new JLabel(
                        "Maximum Price (Nu.):"));

        panel.add(priceField);

        panel.add(
                new JLabel(
                        "Minimum Rating (0 - 5):"));

        panel.add(ratingField);

        panel.add(
                new JLabel(
                        "Guide Included:"));

        panel.add(guideBox);

        panel.add(
                new JLabel(
                        "Family Friendly:"));

        panel.add(familyBox);

        panel.add(
                new JLabel(
                        "Season:"));

        panel.add(seasonBox);

        panel.add(
                new JLabel(
                        "Features (comma separated):"));

        panel.add(featuresField);

        while (true) {

            int result =
                    JOptionPane.showConfirmDialog(
                            null,
                            panel,
                            "DiscoverDruk - Find Your Bhutan Experience",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE);

            if (result != JOptionPane.OK_OPTION) {
                return null;
            }

            DreamExperience dream =
                    new DreamExperience();

            Object typeChoice =
                    typeBox.getSelectedItem();

            if (typeChoice
                    instanceof ExperienceType) {

                dream.addCriterion(
                        Filter.TYPE,
                        typeChoice);
            }

            Object dzongkhagChoice =
                    dzongkhagBox.getSelectedItem();

            if (dzongkhagChoice
                    instanceof Dzongkhag) {

                dream.addCriterion(
                        Filter.DZONGKHAG,
                        dzongkhagChoice);
            }

            Object difficultyChoice =
                    difficultyBox.getSelectedItem();

            if (difficultyChoice
                    instanceof Difficulty) {

                dream.addCriterion(
                        Filter.DIFFICULTY,
                        difficultyChoice);
            }

            String ageText =
                    ageField.getText().trim();

            if (ageText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Please enter your age.",
                        "Invalid Age",
                        JOptionPane.ERROR_MESSAGE);

                continue;
            }

            try {

                int age =
                        Integer.parseInt(ageText);

                if (age <= 0 || age > 120) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter a valid age.",
                            "Invalid Age",
                            JOptionPane.ERROR_MESSAGE);

                    continue;
                }

                dream.addCriterion(
                        Filter.MINIMUM_AGE,
                        age);

            } catch (NumberFormatException exception) {

                JOptionPane.showMessageDialog(
                        null,
                        "Age must be a whole number.",
                        "Invalid Age",
                        JOptionPane.ERROR_MESSAGE);

                continue;
            }

            String durationText =
                    durationField
                            .getText()
                            .trim();

            if (!durationText.isEmpty()) {

                try {

                    double duration =
                            Double.parseDouble(
                                    durationText);

                    if (duration <= 0) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Duration must be greater than 0.",
                                "Invalid Duration",
                                JOptionPane.ERROR_MESSAGE);

                        continue;
                    }

                    dream.addCriterion(
                            Filter.DURATION,
                            duration);

                } catch (NumberFormatException exception) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter a valid duration.",
                            "Invalid Duration",
                            JOptionPane.ERROR_MESSAGE);

                    continue;
                }
            }

            String priceText =
                    priceField
                            .getText()
                            .trim();

            if (!priceText.isEmpty()) {

                try {

                    double price =
                            Double.parseDouble(
                                    priceText);

                    if (price <= 0) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Price must be greater than 0.",
                                "Invalid Price",
                                JOptionPane.ERROR_MESSAGE);

                        continue;
                    }

                    dream.addCriterion(
                            Filter.PRICE,
                            price);

                } catch (NumberFormatException exception) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter a valid price.",
                            "Invalid Price",
                            JOptionPane.ERROR_MESSAGE);

                    continue;
                }
            }

            String ratingText =
                    ratingField
                            .getText()
                            .trim();

            if (!ratingText.isEmpty()) {

                try {

                    float rating =
                            Float.parseFloat(
                                    ratingText);

                    if (rating < 0
                            || rating > 5) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Rating must be between 0 and 5.",
                                "Invalid Rating",
                                JOptionPane.ERROR_MESSAGE);

                        continue;
                    }

                    dream.addCriterion(
                            Filter.RATING,
                            rating);

                } catch (NumberFormatException exception) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter a valid rating.",
                            "Invalid Rating",
                            JOptionPane.ERROR_MESSAGE);

                    continue;
                }
            }

            String guideChoice =
                    (String)
                            guideBox
                                    .getSelectedItem();

            if (!guideChoice.equals(
                    "I don't mind")) {

                dream.addCriterion(
                        Filter.GUIDE_INCLUDED,
                        guideChoice.equals("Yes"));
            }

            String familyChoice =
                    (String)
                            familyBox
                                    .getSelectedItem();

            if (!familyChoice.equals(
                    "I don't mind")) {

                dream.addCriterion(
                        Filter.FAMILY_FRIENDLY,
                        familyChoice.equals("Yes"));
            }

            String seasonChoice =
                    (String)
                            seasonBox
                                    .getSelectedItem();

            if (!seasonChoice.equals(
                    "I don't mind")) {

                dream.addCriterion(
                        Filter.SEASON,
                        seasonChoice);
            }

            String featuresText =
                    featuresField
                            .getText()
                            .trim();

            if (!featuresText.isEmpty()) {

                Set<String> features =
                        new LinkedHashSet<>();

                String[] featureParts =
                        featuresText.split(",");

                for (String feature :
                        featureParts) {

                    if (!feature.isBlank()) {

                        features.add(
                                feature.trim()
                                        .toLowerCase());
                    }
                }

                if (!features.isEmpty()) {

                    dream.addCriterion(
                            Filter.FEATURES,
                            features);
                }
            }

            return dream;
        }
    }

    private static Object[] createOptions(
            Object[] values) {

        Object[] options =
                new Object[
                        values.length + 1];

        for (int i = 0;
             i < values.length;
             i++) {

            options[i] = values[i];
        }

        options[options.length - 1] =
                "I don't mind";

        return options;
    }

    public static Experience findExperience(
            ExperienceRegistry registry,
            DreamExperience dream) {

        List<Experience> matches =
                registry.findMatchingExperiences(
                        dream);

        if (matches.isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Sorry, no experiences matched your preferences.",
                    "No Matches",
                    JOptionPane.INFORMATION_MESSAGE);

            return null;
        }

        Experience selected =
                (Experience)
                        JOptionPane.showInputDialog(
                                null,
                                "We found "
                                        + matches.size()
                                        + " matching experience(s).\n"
                                        + "Please select one:",
                                "DiscoverDruk",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                matches.toArray(),
                                matches.get(0));

        if (selected == null) {
            return null;
        }

        JOptionPane.showMessageDialog(
                null,
                selected.getExperienceInformation(),
                "Experience Details",
                JOptionPane.INFORMATION_MESSAGE);

        return selected;
    }

    public static Traveller getTraveller() {

        String name =
                getName();

        if (name == null) {
            return null;
        }

        String email =
                getEmail();

        if (email == null) {
            return null;
        }

        String phone =
                getPhone();

        if (phone == null) {
            return null;
        }

        return new Traveller(
                name,
                email,
                phone);
    }

    private static String getName() {

        while (true) {

            String name =
                    JOptionPane.showInputDialog(
                            null,
                            "Enter your name:",
                            "Traveller Details",
                            JOptionPane.QUESTION_MESSAGE);

            if (name == null) {
                return null;
            }

            name =
                    name.trim();

            if (!name.isEmpty()) {
                return name;
            }

            JOptionPane.showMessageDialog(
                    null,
                    "Name cannot be empty.",
                    "Invalid Name",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String getEmail() {

        while (true) {

            String email =
                    JOptionPane.showInputDialog(
                            null,
                            "Enter your email:",
                            "Traveller Details",
                            JOptionPane.QUESTION_MESSAGE);

            if (email == null) {
                return null;
            }

            email =
                    email.trim();

            if (email.contains("@")
                    && email.contains(".")
                    && !email.contains(" ")) {

                return email;
            }

            JOptionPane.showMessageDialog(
                    null,
                    "Please enter a valid email address.",
                    "Invalid Email",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String getPhone() {

        while (true) {

            String phone =
                    JOptionPane.showInputDialog(
                            null,
                            "Enter your phone number:",
                            "Traveller Details",
                            JOptionPane.QUESTION_MESSAGE);

            if (phone == null) {
                return null;
            }

            phone =
                    phone.trim();

            if (phone.matches(
                    "[0-9 +()-]{8,20}")) {

                return phone;
            }

            JOptionPane.showMessageDialog(
                    null,
                    "Please enter a valid phone number.",
                    "Invalid Phone",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void writeRequest(
            Traveller traveller,
            Experience selected)
            throws IOException {

        String baseName =
                "request_" + selected.getId();

        String filename =
                baseName + ".txt";

        int number = 1;

        while (new java.io.File(filename).exists()) {
            filename =
                    baseName + "_" + number + ".txt";
            number++;
        }
        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(
                                     filename))) {

            writer.write(
                    "DISCOVERDRUK EXPERIENCE REQUEST");
            writer.newLine();

            writer.write(
                    "================================");
            writer.newLine();
            writer.newLine();

            writer.write(
                    "Traveller Details");
            writer.newLine();

            writer.write(
                    "----------------");
            writer.newLine();

            writer.write(
                    traveller
                            .getTravellerInformation());

            writer.newLine();
            writer.newLine();

            writer.write(
                    "Selected Experience");
            writer.newLine();

            writer.write(
                    "-------------------");
            writer.newLine();

            writer.write(
                    selected
                            .getExperienceInformation());

            writer.newLine();
            writer.newLine();

            writer.write(
                    "Thank you for using DiscoverDruk.");
        }
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

            DreamExperience dream =
                    getDreamExperience();

            if (dream == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "Search cancelled.",
                        "DiscoverDruk",
                        JOptionPane.INFORMATION_MESSAGE);

                return;
            }

            Experience selected =
                    findExperience(
                            registry,
                            dream);

            if (selected == null) {
                return;
            }

            Traveller traveller =
                    getTraveller();

            if (traveller == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "Request cancelled.",
                        "DiscoverDruk",
                        JOptionPane.INFORMATION_MESSAGE);

                return;
            }

            JOptionPane.showMessageDialog(
                    null,
                    "Traveller details:\n\n"
                            + traveller
                            .getTravellerInformation(),
                    "DiscoverDruk",
                    JOptionPane.INFORMATION_MESSAGE);

            int choice =
                    JOptionPane.showConfirmDialog(
                            null,
                            "Would you like to save this experience request?",
                            "DiscoverDruk",
                            JOptionPane.YES_NO_OPTION);

            if (choice
                    != JOptionPane.YES_OPTION) {
                return;
            }

            writeRequest(
                    traveller,
                    selected);

            JOptionPane.showMessageDialog(
                    null,
                    "Your experience request has been saved successfully.",
                    "DiscoverDruk",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException exception) {

            JOptionPane.showMessageDialog(
                    null,
                    "File error:\n"
                            + exception.getMessage(),
                    "File Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}