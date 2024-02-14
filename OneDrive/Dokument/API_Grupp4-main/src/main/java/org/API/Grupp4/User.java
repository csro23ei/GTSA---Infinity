package org.API.Grupp4;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private UUID uuid;
    @NotEmpty(message = "Du måste ange ett Förnamn")
    private String firstName;
    @NotEmpty(message = "Du måste ange ett Efternamn")
    private String lastName;
    @NotEmpty(message = "Du måste ange ett Använarnamn")
    private String userName;
    // @Size(min = 6, max = 20, message = "Lösenordet måste vara mellan 6-20 karaktärer")

    private String password;

    public User(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        uuid = UUID.randomUUID();
    }

    public User() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getUuid() {
        return uuid;
    }

}
