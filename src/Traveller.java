public class Traveller {

    private final String name;
    private final String email;
    private final String phone;

    public Traveller(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

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