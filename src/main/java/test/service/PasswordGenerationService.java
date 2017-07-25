package test.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class PasswordGenerationService {

    private static final int PASSWORD_LENGTH = 8;

    private char[] possibleCharacters = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789").toCharArray();

    public String generatePassword() {
        Random random = new SecureRandom();
        char[] result = new char[PASSWORD_LENGTH];
        for (int i = 0; i < result.length; i++) {
            int randomCharIndex = random.nextInt(possibleCharacters.length);
            result[i] = possibleCharacters[randomCharIndex];
        }
        return new String(result);
    }
}
