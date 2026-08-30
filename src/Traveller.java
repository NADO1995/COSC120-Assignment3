public record Traveller(
        String name,
        String email,
        String phone
) {

    public String getTravellerInformation() {
        return "Name: " + name
                + "\nEmail: " + email
                + "\nPhone: " + phone;
    }

    @Override
    public String toString() {
        return name;
    }
}