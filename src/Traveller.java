/**
 * File: Traveller.java
 * Author: Tshering Dorji
 * Unit: COSC120
 * Project: DiscoverDruk - Bhutan Experience Finder
 *
 * This project was developed with reference to the COSC120 lecture notes,
 * tutorial materials, and sample code provided for the assignment.
 *
 * AI assistance:
 * ChatGPT was used to help understand the assignment requirements,
 * review and debug code, and improve code clarity and documentation.
 *
 * GitHub: <https://github.com/NADO1995/COSC120-Assignment3.git>
 */

/**
 * Stores the name, email and phone number of a traveller.
 * A record is used because the traveller details do not need to change
 * after the Traveller object is created.
 */
public record Traveller(
        String name,
        String email,
        String phone
) {

    /**
     * Returns the traveller's details in a readable format.
     *
     * @return the traveller's name, email and phone number
     */
    public String getTravellerInformation() {
        return "Name: " + name
                + "\nEmail: " + email
                + "\nPhone: " + phone;
    }

    /**
     * Returns the traveller's name.
     *
     * @return the traveller's name
     */
    @Override
    public String toString() {
        return name;
    }
}