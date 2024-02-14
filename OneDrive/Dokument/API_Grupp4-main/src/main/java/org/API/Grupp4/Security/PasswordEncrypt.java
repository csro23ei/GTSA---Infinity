package org.API.Grupp4.Security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncrypt {

    public static String encryptPassword(String password) {
        System.out.println(password);
        String encrypted = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(encrypted);
        return encrypted;
    }

    public static boolean verifyPassword(String password, String encryptedPassword) {
        return BCrypt.checkpw(password, encryptedPassword);

    }

}
