package org.API.Grupp4.Security.ApiKey;

import java.util.UUID;

import org.API.Grupp4.User;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "keys")
public class Key {

    private UUID owner;
    @Id
    private String key;

    public Key(User owner) {
        this.owner = owner.getUuid();
        this.key = generateKey(owner);
    }

    public Key() {
    }

    public String generateKey(User user) {     
        String key = "Group4_is_the_best!" + user.getUserName() + user.getFirstName();
        String encryptedKey = BCrypt.hashpw(key, BCrypt.gensalt());

        return encryptedKey;

    }

    public UUID getOwner() {
        return owner;
    }

    public String getKey() {
        return key;
    }

}
