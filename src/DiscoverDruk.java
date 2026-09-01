import javax.swing.JOptionPane;

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

    public static DreamExperience getDreamExperience() {

        DreamExperience dream =
                new DreamExperience();

        Object[] typeOptions =
                new Object[
                        ExperienceType.values().length + 1];

        for (int i = 0;
             i < ExperienceType.values().length;
             i++) {

            typeOptions[i] =
                    ExperienceType.values()[i];
        }

        typeOptions[typeOptions.length - 1] =
                "I don't mind";

        Object typeChoice =
                JOptionPane.showInputDialog(
                        null,
                        "What type of experience would you like?",
                        "DiscoverDruk",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        typeOptions,
                        typeOptions[0]);

        if (typeChoice == null) {
            return null;
        }

        if (typeChoice instanceof ExperienceType) {

            dream.addCriterion(
                    Filter.TYPE,
                    typeChoice);
        }

        Object[] dzongkhagOptions =
                new Object[
                        Dzongkhag.values().length + 1];

        for (int i = 0;
             i < Dzongkhag.values().length;
             i++) {

            dzongkhagOptions[i] =
                    Dzongkhag.values()[i];
        }

        dzongkhagOptions[
                dzongkhagOptions.length - 1] =
                "I don't mind";

        Object dzongkhagChoice =
                JOptionPane.showInputDialog(
                        null,
                        "Which Dzongkhag would you prefer?",
                        "DiscoverDruk",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        dzongkhagOptions,
                        dzongkhagOptions[0]);

        if (dzongkhagChoice == null) {
            return null;
        }

        if (dzongkhagChoice instanceof Dzongkhag) {

            dream.addCriterion(
                    Filter.DZONGKHAG,
                    dzongkhagChoice);
        }

        Object[] difficultyOptions =
                new Object[
                        Difficulty.values().length + 1];

        for (int i = 0;
             i < Difficulty.values().length;
             i++) {

            difficultyOptions[i] =
                    Difficulty.values()[i];
        }

        difficultyOptions[
                difficultyOptions.length - 1] =
                "I don't mind";

        Object difficultyChoice =
                JOptionPane.showInputDialog(
                        null,
                        "What difficulty level would you prefer?",
                        "DiscoverDruk",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        difficultyOptions,
                        difficultyOptions[0]);

        if (difficultyChoice == null) {
            return null;
        }

        if (difficultyChoice instanceof Difficulty) {

            dream.addCriterion(
                    Filter.DIFFICULTY,
                    difficultyChoice);
        }

        while (true) {

            String priceInput =
                    JOptionPane.showInputDialog(
                            null,
                            "Enter your maximum price in Ngultrum.\n"
                                    + "Leave blank if you don't mind.",
                            "DiscoverDruk",
                            JOptionPane.QUESTION_MESSAGE);

            if (priceInput == null) {
                return null;
            }

            if (priceInput.isBlank()) {
                break;
            }

            try {

                double maximumPrice =
                        Double.parseDouble(
                                priceInput.trim());

                if (maximumPrice <= 0) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter a price greater than 0.",
                            "Invalid Price",
                            JOptionPane.ERROR_MESSAGE);

                    continue;
                }

                dream.addCriterion(
                        Filter.MAX_PRICE,
                        maximumPrice);

                break;

            } catch (NumberFormatException exception) {

                JOptionPane.showMessageDialog(
                        null,
                        "Please enter a valid number.",
                        "Invalid Price",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        return dream;
    }

    public static Experience findExperience(
            ExperienceRegistry registry,
            DreamExperience dream) {

        List<Experience> matches =
                registry.findMatchingExperiences(dream);

        if (matches.isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Sorry, no experiences matched your preferences.",
                    "No Matches",
                    JOptionPane.INFORMATION_MESSAGE);

            return null;
        }

        Experience selected =
                (Experience) JOptionPane.showInputDialog(
                        null,
                        "We found " + matches.size()
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

        String name = getName();

        if (name == null) {
            return null;
        }

        String email = getEmail();

        if (email == null) {
            return null;
        }

        String phone = getPhone();

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

            name = name.trim();

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

            email = email.trim();

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

            phone = phone.trim();

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

        String filename =
                "request_" + selected.getId() + ".txt";

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(filename))) {

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
                    traveller.getTravellerInformation());
            writer.newLine();
            writer.newLine();

            writer.write(
                    "Selected Experience");
            writer.newLine();

            writer.write(
                    "-------------------");
            writer.newLine();

            writer.write(
                    selected.getExperienceInformation());
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

            if (choice != JOptionPane.YES_OPTION) {
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