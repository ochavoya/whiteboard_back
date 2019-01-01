package com.ochavoya.whiteboard.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    private static final String ENCRYPTION_ALGORITHM = "SHA-256";
    public static final String createToken() {
        StringBuilder builder = new StringBuilder();
        while (builder.length() < 32) {
            builder.append(Integer.toHexString(0xff & (int) (Math.random() * 256)));
        }
        return builder.toString().substring(0, 32);
    }

    private static final String cryptWithSHA256(String salt, String password) {
        byte[] passBytes =( salt + password).getBytes();
        byte[] digested;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
            messageDigest.reset();
            digested = messageDigest.digest(passBytes);
        }catch(NoSuchAlgorithmException nse) {
            throw new RuntimeException(String.format("Invalid encryption algorithm: %s", ENCRYPTION_ALGORITHM));
        }
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i< digested.length; ++i) {
            buffer.append(Integer.toHexString(0xff & digested[i]));
        }

        return String.format("%s:%s", salt, buffer.toString());
    }

    public static final String encrypt(String password) {
        return cryptWithSHA256(createToken(), password);
    }

    public static final boolean validate(String password, String storedPassword) {
        String salt = storedPassword.substring(0, storedPassword.indexOf(':'));
        return storedPassword.equals(cryptWithSHA256(salt, password));
    }
}
