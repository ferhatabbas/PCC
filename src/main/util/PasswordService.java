package main.util;
import jdk.nashorn.internal.ir.annotations.Ignore;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class PasswordService {
    private static PasswordService instance;

    public PasswordService() {
    }

    public synchronized String encrypt(String plaintext) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = null;
            md = MessageDigest.getInstance("SHA"); //step 2


            md.update(plaintext.getBytes("UTF-8")); //step 3


        byte raw[] = md.digest(); //step 4
        String hash = (new BASE64Encoder()).encode(raw); //step 5
        return hash; //step 6
    }

    public static synchronized PasswordService getInstance() //step 1
    {
        if (instance == null) {
            instance = new PasswordService();
        }
        return instance;
    }
}
