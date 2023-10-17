package com.api.jukeboxd.core.util;

import com.api.jukeboxd.core.exception.PasswordEncryptionException;
import com.api.jukeboxd.core.exception.ValidationException;
import lombok.NoArgsConstructor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.api.jukeboxd.core.exception.model.ErrorMessage.*;
@NoArgsConstructor
public class PasswordUtil {
    public static String encrypt(String decryptedPassword) throws PasswordEncryptionException {
        try {
            MessageDigest message = MessageDigest.getInstance("SHA-512");
            message.update(decryptedPassword.getBytes());

            byte[] bytes = message.digest();

            StringBuilder builder = new StringBuilder();
            for (byte aByte : bytes) {
                builder.append(Integer.toString((aByte & 0xff) + 0x100, 16)
                        .substring(1));
            }

            return builder.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new PasswordEncryptionException (e.getMessage());
        }
    }

    public static void verifyValidPassword(String password) {
        var match = password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");

        if(!match) {
            throw new ValidationException(INVALID_PASSWORD_PATTERN.getCode(),
                    INVALID_PASSWORD_PATTERN.getMessage());
        }
    }
}