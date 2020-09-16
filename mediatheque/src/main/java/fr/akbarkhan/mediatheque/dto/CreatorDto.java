package fr.akbarkhan.mediatheque.dto;

public class CreatorDto {

    private final String firstName;
    private final String lastName;
    private final String username;

    public CreatorDto(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }
}
