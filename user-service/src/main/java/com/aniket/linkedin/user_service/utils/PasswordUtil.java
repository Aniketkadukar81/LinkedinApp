package com.aniket.linkedin.user_service.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public static String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword,BCrypt.gensalt());
    }

    public static Boolean checkPassword(String plainTextPassword, String hashedPassword){
        return BCrypt.checkpw(plainTextPassword,hashedPassword);
    }

}
