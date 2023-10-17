package com.api.jukeboxd.core.util;

import com.api.jukeboxd.core.exception.ValidationException;
import lombok.NoArgsConstructor;

import static com.api.jukeboxd.core.exception.model.ErrorMessage.INVALID_EMAIL_PATTERN;
@NoArgsConstructor
public class ValidationUtil {
    public static void verifyEmailPattern(String email) {
        var match = email.matches("^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$");

        if(!match) {
            throw new ValidationException(INVALID_EMAIL_PATTERN.getCode(),
                    INVALID_EMAIL_PATTERN.getMessage());
        }
    }
}
