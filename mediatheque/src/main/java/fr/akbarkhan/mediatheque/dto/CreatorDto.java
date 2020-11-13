package fr.akbarkhan.mediatheque.dto;

public class CreatorDto {

    private Integer id;
    private String firstName;
    private String lastName;

    public CreatorDto(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CreatorDto() {
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


}
