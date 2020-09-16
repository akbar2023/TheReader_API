package fr.akbarkhan.mediatheque.dto;

public class CreatorDto {

    private String firstName;
    private String lastName;
    private String email;

    public CreatorDto(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public CreatorDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
