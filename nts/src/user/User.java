package user;

public class User {
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Accessor for the first name attribute
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Accessor for the last name attribute
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
